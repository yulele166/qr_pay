package com.exam.demo.model.wechat;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信退款请求
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
public class WechatRefund {
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String transaction_id;//微信流水号
    private String out_trade_no;//商户流水号
    private String out_refund_no;//商户退款号
    private Integer total_fee;//总金额
    private Integer refund_fee;//退款金额
    private String op_user_id;//操作员
    private String sign;//签名
    private String refund_account;//REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款
    private String refund_desc;//退款原因
    private String refund_fee_type;//退款货币类型
    private String notify_url;//退款结果通知url


    @Getter
    @Setter
    @XmlRootElement(name = "xml")
    public static class Response {
        private String return_code; //SUCCESS/FAIL , SUCCESS退款申请接收成功, FAIL 提交业务失败
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
        private String out_refund_no;//商户退款单号
        private String refund_id;//微信退款单号
        private String refund_fee;//退款总金额,单位为分,可以做部分退款
        private String settlement_refund_fee;//去掉非充值代金券退款金额后的退款金额
        private String total_fee;//订单金额
        private String settlement_total_fee;//去掉非充值代金券后订单金额
        private String fee_type;
        private String cash_fee;//现金支付金额
        private String cash_refund_fee;//现金退款金额
        private String coupon_type_$n;
        private String coupon_refund_count_$n;
        private String coupon_refund_batch_id_$n_$m;
        private String coupon_refund_id_$n_$m;
        private String coupon_refund_fee_$n_$m;
        private String refund_channel;

        public Response(String return_code) {
            this.return_code = return_code;
        }

        public Response() {}

    }
}
