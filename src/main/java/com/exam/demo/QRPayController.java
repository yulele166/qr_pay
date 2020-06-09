package com.exam.demo;

import com.exam.demo.model.Constants;
import com.exam.demo.model.OrderInfo;
import com.exam.demo.model.alipay.AlipayTrade;
import com.exam.demo.model.wechat.WechatClient;
import com.exam.demo.model.wechat.WechatConfig;
import com.exam.demo.model.wechat.WechatUnifiedOrder;
import com.exam.demo.utils.Http_Utils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Jersey on 2018/11/9 14:40.
 */
@Controller
@Slf4j
public class QRPayController {

    private String wechatPayNotifyUrl = "http://zexige.com/notify/wechat.html";
    private String aliPayNotifyUrl = "http://zexige.com/notify/alipay.html";
    private String aliPayReturn = "http://zexige.com/return/alipay.html";

    private OrderInfo orderInfo;
    private String oid;//预订单id

    @RequestMapping("/hello")
    public String index(HashMap<String, Object> map){
        map.put("greeting", "欢迎体验聚合支付");
        map.put("sayHi", "hello world!");
        log.info("欢迎进入HTML页面");
        return "/index";
    }


    /**
     * 生成预订单
     * @return
     */
    @RequestMapping("/pay/gen")
    @ResponseBody
    public Map<String, Object> preOrder(){
        Map result = new HashMap();
        oid = new Random().nextLong()+"";
        result.put("oid",oid);
        return result;
    }


    @RequestMapping("/pay/qr")//微信公众号支付路径需要添加授权
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map){
        String orderId = request.getParameter("oid");
        String userAgent = request.getHeader("User-Agent");

        ModelAndView mav = new ModelAndView();
        orderInfo = new OrderInfo();//取预订单信息
        orderInfo.setOrderNo(orderId);
        orderInfo.setGoodsId("1111");
        orderInfo.setPayPrice(100L);
        orderInfo.setClientIp("0.0.0.0");
        orderInfo.setGoodsName("衣服");

        if (userAgent.toLowerCase().contains("MicroMessenger".toLowerCase())){
            log.info(Constants.PAY_MODE_WECHAT+"扫码进入...");
            orderInfo.setPayType(Constants.PAY_MODE_WECHAT);

            String openid =  handleWechatBrow(request,response,"http://zexige.com/pay/qr");
            if (StringUtils.isNotBlank(openid)){
                map = this.morderConsume(orderInfo,openid);
//                map.put("appId","wx9727e9dac06871e1");
//                map.put("timeStamp","1542000747");
//                map.put("nonceStr","OvAA3321SvquCG4X");
//                map.put("paySign","E65DF396FC5E50199A754AE91DF3CD52");
//                map.put("prepay_id","wx121332272602420f29f1216f3908175858");
//                map.put("orderNo","480815961883225869");
                mav.addAllObjects(map);
                log.info(new Gson().toJson(map));
            }
        }else if (userAgent.toLowerCase().contains("AlipayClient".toLowerCase())){
            log.info(Constants.PAY_MODE_ALI+"扫码进入...");
            orderInfo.setPayType(Constants.PAY_MODE_ALI);
            String form = mAlipayOrder(orderInfo);
//            form ="<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipay.com/gateway.do?charset=utf-8&method=alipay.trade.wap.pay&sign=C64z%2BnDAhQH7AIVNgvv%2BvOPHh%2FRBJpbavyIuEtQEQ%2Buvrkt5RCyG3vr3NDyQaM7psRW%2BfHrgDqLKI0mtoVQpAL1SeMzFxys71VihQmSP%2B66OT8VqOO3CfmowqNEoBBXAO7uHshD3v14IX6zTSKgH0CtzO1a4wqrFplStLIc5HKg%3D&version=1.0&app_id=2017122501199943&sign_type=RSA&timestamp=2018-11-15+10%3A24%3A54&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json\">\n" +
//                    "<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;out_trade_no&quot;:&quot;169584538918912&quot;,&quot;total_amount&quot;:&quot;0.01&quot;,&quot;subject&quot;:&quot;1个月&quot;,&quot;timeout_express&quot;:&quot;120&quot;,&quot;body&quot;:&quot;14058365&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_WAY&quot;}\">\n" +
//                    "<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n" +
//                    "</form>\n" +
//                    "<script></script>";
            map.put("form",form);
            mav.addAllObjects(map);
            log.info(form);
        }
        mav.setViewName("temp");
        return mav;
    }

    /**
     * web支付宝处理
     * @return
     */
    @RequestMapping("/pay/web")
    @ResponseBody
    public Map<String, Object> webAlipay(HttpServletRequest request){
        String oid = request.getParameter("oid");//重新下单
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (oid.equals(orderInfo.getOrderNo())&&orderInfo!=null&&orderInfo.getOrderStatus()==0){
                orderInfo.setClientIp("0.0.0.0");//ip
                log.info("web支付宝支付...");
                orderInfo.setPayType(Constants.PAY_MODE_ALI);//payType
                String form = alipayOrder(orderInfo);
                resultMap.put("form",form);
            }else{
                log.error("一码两付订单不存在");
            }
        } catch (Exception e) {
            log.error("web支付宝支付异常",e);
        }
        return resultMap;
    }

    /**
     * m微信下单
     *
     * @param orderInfo
     * @return
     */
    private WechatUnifiedOrder.Response mWechatOrder(OrderInfo orderInfo,String openid) throws Exception {

        WechatUnifiedOrder request = new WechatUnifiedOrder();
        String goodsName = orderInfo.getGoodsName();
        if (goodsName!=null && goodsName.length()>40) {
            goodsName = goodsName.substring(0,40);
        }
        request.setBody(goodsName);
        request.setDetail(orderInfo.getGoodsId());
        request.setOut_trade_no(orderInfo.getOrderNo());
        request.setFee_type("CNY");
        Long w = orderInfo.getPayPrice();
        request.setTotal_fee(w.intValue());
        request.setSpbill_create_ip(orderInfo.getClientIp());
        request.setTime_start(System.currentTimeMillis() + "");
        request.setNotify_url(wechatPayNotifyUrl);
        if (StringUtils.isNotEmpty(openid)) {
            request.setOpenid(openid);
            request.setTrade_type(WechatConfig.WECHAT_TRADE_TYPE);
        } else {
            request.setTrade_type(WechatConfig.M_TRADE_TYPE);
        }
        return WechatConfig.getInstance().unifiedOrder(request);
    }

    /**
     * m版下单
     *
     * @param orderInfo
     * @param openid 公众号支付openid
     * @return
     */
    private Map<String, Object> morderConsume(OrderInfo orderInfo ,String openid) {

        HashMap<String, Object> res = new HashMap<>();
        String orderNo = null;
        try {
            res.put("result", Constants.RESULT_SUCC);
            orderNo = orderInfo.getOrderNo();
            String mode = orderInfo.getPayType();
            //微信支付
            if (StringUtils.equals(mode, Constants.PAY_MODE_WECHAT)) {
                WechatUnifiedOrder.Response resp = this.mWechatOrder(orderInfo,openid);
                if (StringUtils.equals(resp.getResult_code(), Constants.WECHAT_RESULT_CODE_SUCC)) {
                    if(StringUtils.isNotEmpty(openid)){
                        res = this.wechatJSAPI(resp, res);
                    }
                } else {
                    res.put("result", Constants.WECHAT_RESULT_CODE_FAIL);
                }
            }
        } catch (Exception e) {
            res.put("result", Constants.RESULT_ERROR);
            res.put("msg", e.getMessage());
        }
        res.put("orderNo", orderNo);
        return res;
    }

    private HashMap<String,Object> wechatJSAPI(WechatUnifiedOrder.Response resp,HashMap<String,Object> map){

        String timeStamp = String.valueOf(System.currentTimeMillis()/1000);

        TreeMap<String,Object> tm = new TreeMap<String,Object>();
        tm.put("appId", resp.getAppid());
        tm.put("timeStamp", timeStamp);
        tm.put("nonceStr", resp.getNonce_str());
        tm.put("package", "prepay_id="+resp.getPrepay_id());
        tm.put("signType", "MD5");

        map.put("appId", resp.getAppid());//公众号名称，由商户传入
        map.put("timeStamp", timeStamp);//时间戳，自1970年以来的秒数
        map.put("nonceStr", resp.getNonce_str());//随机串
        map.put("prepay_id", resp.getPrepay_id());
        map.put("signType", "MD5"); //微信签名方式：
        WechatClient instance = new  WechatClient(WechatConfig.APP_ID,WechatConfig.MCH_ID,WechatConfig.APP_SECRET,WechatConfig.WECHAT_TRADE_TYPE);
        map.put("paySign",  (instance.sign(tm)).toUpperCase()); //微信签名
        return map;
    }

    /**
     * 微信内置浏览器 支付处理
     * @param request
     * @return
     */
    private String handleWechatBrow (HttpServletRequest request,HttpServletResponse response,String redirectUrl){
        try {
            String code = request.getParameter("code");
            boolean wechatBrow = isWechatBrow(request);
            if (wechatBrow && StringUtils.isNotEmpty(code)) {
                return getWxOpenid(request,code);
            } else if (wechatBrow) {
                String queryurl=request.getQueryString();
                if(null!=queryurl){
                    redirectUrl+="?"+queryurl;
                }
                String redirect_uri= URLEncoder.encode(redirectUrl, "UTF-8");
                String redirect = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatConfig.APP_ID+"&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
                response.sendRedirect(redirect);
            }
        }catch (IOException e){

        }
        return null;
    }

    /**
     * 微信浏览器
     * @param request
     * @return
     */
    private boolean isWechatBrow (HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if(userAgent.indexOf("micromessenger")>-1){//微信客户端
            return true;
        }else{
            return false;
        }
    }
    /**
     * 微信内置浏览器 获取openid
     * @param request
     * @param  code
     * @return
     */
    private String  getWxOpenid(HttpServletRequest request,String code) {
        try {
            String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", WechatConfig.APP_ID,WechatConfig.JSAPI_APP_SECRET,code);
            String wxuser = Http_Utils.httpsGet(url);
            Map<String,String> o = new Gson().fromJson(wxuser,Map.class);
            return o.get("openid");
        } catch (Exception e) {
            log.error("授权异常",e);
        }
        return null;
    }
    /**
     * pc 支付宝下单
     * @param orderInfo
     * @return
     */
    public String alipayOrder(OrderInfo orderInfo){
        try {
            AlipayTrade alipayTrade = new AlipayTrade();
            Map<String, String> paraMap = new HashMap<>();
            //商户订单号，商户网站订单系统中唯一订单号，必填
            paraMap.put("out_trade_no", orderInfo.getOrderNo());
            paraMap.put("total_amount", String.valueOf(orderInfo.getPayPrice()));
            String goodsName = orderInfo.getGoodsName();
            if (goodsName!=null && goodsName.length()>40) {
                goodsName = goodsName.substring(0,40);
            }
            paraMap.put("subject", goodsName);
            paraMap.put("body", orderInfo.getGoodsId());
            paraMap.put("product_code", "FAST_INSTANT_TRADE_PAY");
            paraMap.put("notifyUrl", aliPayNotifyUrl);
            paraMap.put("returnUrl", aliPayReturn);
            return alipayTrade.TradeWapPayRequest(paraMap);
        } catch (Exception e) {
            log.error("支付宝下单异常",e);
        }
        return null;
    }

    /**
     * m 支付宝下单
     * @param orderInfo
     * @return
     */
    public String mAlipayOrder(OrderInfo orderInfo){
        try {
            AlipayTrade alipayTrade = new AlipayTrade();
            Map<String, String> paraMap = new HashMap<>();
            //商户订单号，商户网站订单系统中唯一订单号，必填
            paraMap.put("out_trade_no", orderInfo.getOrderNo());
            paraMap.put("total_amount", String.valueOf(100L));
            String goodsName = orderInfo.getGoodsName();
            if (goodsName!=null && goodsName.length()>40) {
                goodsName = goodsName.substring(0,40);
            }
            paraMap.put("subject", goodsName);
            paraMap.put("body", orderInfo.getGoodsId());
            paraMap.put("product_code", "QUICK_WAP_WAY");
            paraMap.put("timeout_express", "120");
            paraMap.put("notifyUrl", aliPayNotifyUrl);
            paraMap.put("returnUrl", aliPayReturn);
            return alipayTrade.mobilePayRequest(paraMap);
        }catch (Exception e){
            log.error("手机端异常",e);
        }
        return null;

    }
}
