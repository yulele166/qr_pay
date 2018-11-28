package com.exam.demo.model.alipay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.exam.demo.utils.SignUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝交易类
 */
public class AlipayTrade {

    private Logger logger = LoggerFactory.getLogger(AlipayTrade.class);

    /**
     * web支付下单并支付
     * url https://doc.open.alipay.com/doc2/detail.htm?treeId=203&articleId=105463&docType=1#s1
     * @return web支付的表单
     */
    public String TradeWapPayRequest(Map<String, String> sParaTemp){
    	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(sParaTemp.get("returnUrl"));
        //alipayRequest.setNotifyUrl(AliPayConfig.PAY_NOTIFY);
        alipayRequest.setNotifyUrl(sParaTemp.get("notifyUrl"));
        sParaTemp.remove("notifyUrl");
        sParaTemp.remove("returnUrl");
        // 待请求参数数组
      //  sParaTemp.put("seller_id",AliPayConfig.SELLER_ID);
        alipayRequest.setBizContent(new Gson().toJson(sParaTemp));
        String form = "";
        try {
            form = AliPayConfig.getInstance().pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            logger.error("支付宝构造表单失败",e);
        }
        logger.debug("支付宝支付表单构造:"+form);
        return form;
    }
    
    /**
     * 手机网页支付下单并支付(web支付在安卓中是可以直接唤醒支付宝APP的)
     * url https://doc.open.alipay.com/doc2/detail.htm?treeId=203&articleId=105463&docType=1#s1
     * @return 手机网页支付下单并支付
     */
    public String mobilePayRequest(Map<String, String> sParaTemp){
    	AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
     //   alipayRequest.setReturnUrl(AliPayConfig.WAP_RETURN_URL);
        //alipayRequest.setNotifyUrl(AliPayConfig.PAY_NOTIFY);

        alipayRequest.setNotifyUrl(sParaTemp.get("notifyUrl"));
        alipayRequest.setReturnUrl(sParaTemp.get("returnUrl"));
        sParaTemp.remove("notifyUrl");
        sParaTemp.remove("returnUrl");

        //封装请求支付信息
		  AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
		  model.setOutTradeNo(sParaTemp.get("out_trade_no"));
		  model.setSubject(sParaTemp.get("subject"));
		  model.setTotalAmount(sParaTemp.get("total_amount"));
		  model.setBody(sParaTemp.get("body"));
		  model.setTimeoutExpress(sParaTemp.get("timeout_express"));
		  model.setProductCode(sParaTemp.get("product_code"));
		  alipayRequest.setBizModel(model);

        // 待请求参数数组
		  //  sParaTemp.put("seller_id",AliPayConfig.SELLER_ID);
        alipayRequest.setBizContent(new Gson().toJson(sParaTemp));
        // form表单生成
        String form = "";
        try {
    		// 调用SDK生成表单
            form = AliPayConfig.getInstance().pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            logger.error("支付宝构造表单失败",e);
        }
        logger.debug("支付宝支付表单构造:"+form);
        return form;
    }

    /**
     * 申请退款
     * @param sParaTemp 退款参数
     * @return true成功,回调中处理
     * 备注:https://doc.open.alipay.com/docs/api.htm?spm=a219a.7629065.0.0.3RjsEZ&apiId=759&docType=4
     */
    public  AlipayTradeRefundResponse tradeRefundRequest(Map<String, String> sParaTemp) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        // 待请求参数数组
        request.setBizContent(new Gson().toJson(sParaTemp));
        AlipayTradeRefundResponse response =  AliPayConfig.getInstance().execute(request);
        logger.debug("支付宝退货结果:"+response.getBody());
        return response;
    }

    /**
     * 支付宝退款查询
     * @param sParaTemp
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeFastpayRefundQueryResponse tradeRefundQuery(Map<String, String> sParaTemp) throws AlipayApiException{
        AlipayTradeFastpayRefundQueryRequest request= new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(new Gson().toJson(sParaTemp));
        AlipayTradeFastpayRefundQueryResponse response = AliPayConfig.getInstance().execute(request);
        return response;
    }

    /**
     * 支付宝订单查询
     * @param sParaTemp
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse tradeOrderQuery (Map<String, String> sParaTemp)  throws AlipayApiException {
        AlipayTradeQueryRequest request= new AlipayTradeQueryRequest();
        request.setBizContent(new Gson().toJson(sParaTemp));
        AlipayTradeQueryResponse response = AliPayConfig.getInstance().execute(request);
        return response;
    }

    /**
     * 支付宝订单查询
     * @param orderNo 订单号
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse tradeOrderQuery (String orderNo)  throws AlipayApiException {
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("out_trade_no",orderNo);
        return tradeOrderQuery(paraMap);
    }
    /**
     * 支付宝回调验签
     * @param request 回调请求
     * @return true成功
     * 备注:验签成功后，按照支付结果异步通知中的描述(二次验签接口,貌似称为历史接口了)
     */
    public boolean verifyNotify(HttpServletRequest request) throws AlipayApiException {
        Map<String,String> paranMap = SignUtil.request2Map(request);
        return verifyNotify(paranMap);
    }

    /**
     * 支付宝回调验签
     * @param paranMap 回调请求
     * @return true成功
     * 备注:验签成功后，按照支付结果异步通知中的描述(二次验签接口,貌似称为历史接口了)
     */
    public boolean verifyNotify(Map<String,String> paranMap) throws AlipayApiException {
        logger.debug("支付宝回调参数:"+paranMap.toString());
        boolean isVerify = AlipaySignature.rsaCheckV1(paranMap,AliPayConfig.ALIPAY_PUBLIC_KEY, AliPayConfig.CHARSET); //调用SDK验证签名
        logger.debug("支付宝验签结果"+isVerify);
        return isVerify;
    }
}
