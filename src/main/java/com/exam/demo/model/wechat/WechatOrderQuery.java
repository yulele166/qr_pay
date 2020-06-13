package com.exam.demo.model.wechat;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 订单查询
 **/
@Getter
@Setter
@XmlRootElement(name = "xml")
public class WechatOrderQuery implements Serializable {

    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    //二选一
    private String transaction_id;//微信订单号
    private String out_trade_no;//商户系统内部的订单号

    @Getter
    @Setter
    @XmlRootElement(name = "xml")
    public static class Response implements Serializable {
        private String return_code;
        private String return_msg;
        private String result_code;
        private String err_code;//错误代码
        private String err_code_des;//错误代码描述
        private String appid;
        private String mch_id;
        private String device_info;//设备信息
        private String nonce_str;
        private String sign;
        private String transaction_id;//微信订单号
        private String out_trade_no;//商户系统内部的订单号
        private Integer total_fee;//标价金额
        private Integer settlement_total_fee;//应结订单金额
        private String bank_type;//付款银行

        //交易状态 SUCCESS—支付成功 REFUND—转入退款 NOTPAY—未支付 CLOSED—已关闭
        // REVOKED—已撤销（刷卡支付）USERPAYING--用户支付中 PAYERROR--支付失败(其他原因，如银行返回失败)
        private String trade_state ;
        private String trade_state_desc ;//交易状态描述
        private String trade_type;// 值 JSAPI，NATIVE，APP，MICROPAY
        private String fee_type;//标价币种
        private Integer cash_fee;//现金支付金额
        private String cash_fee_type;//现金支付币种
        private Integer coupon_fee;//代金券金额
        private Integer coupon_count;//代金券使用数量
        private String coupon_type_$n;//代金券类型
        private String coupon_id_$n;//代金券ID
        private String coupon_fee_$n;//单个代金券支付金额
        private String attach;//附加数据
        private String time_end;//支付完成时间

        public Response() {}

        public Response(String return_code) {
            this.return_code = return_code;
        }

    }
}
