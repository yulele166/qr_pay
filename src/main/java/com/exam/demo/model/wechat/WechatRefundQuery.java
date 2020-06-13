package com.exam.demo.model.wechat;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 退款查询
 */
@Getter
@Setter
@XmlRootElement(name = "xml")
public class WechatRefundQuery {
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    //四选一
    private String transaction_id;//微信订单号
    private String out_trade_no;//商户系统内部的订单号
    private String out_refund_no;//商户侧传给微信的退款单号
    private String refund_id;//微信生成的退款单号，在申请退款接口有返回

    @Getter
    @Setter
    @XmlRootElement(name = "xml")
    public static class Response {
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
        private String total_fee;//订单金额
        private String settlement_total_fee;//去掉非充值代金券后订单金额
        private String fee_type;
        private String cash_fee;//现金支付金额
        private String refund_count;//退款记录数
        private String out_refund_no_$n;//商户退款单号
        private String refund_id_$n;//微信退款单号
        private String refund_channel_$n;//退款渠道
        private String refund_fee_$n;//申请退款金额
        private String refund_fee_0;//申请退款金额
        private String settlement_refund_fee_$n;//退款金额
        private String refund_account;//退款资金来源
        private String coupon_type_$n;//代金券类型
        private String coupon_refund_fee_$n;
        private String coupon_refund_count_$n;
        private String coupon_refund_batch_id_$n_$m;
        private String coupon_refund_id_$n_$m;
        private String coupon_refund_fee_$n_$m;
        private String refund_status_$n;//退款状态SUCCESS—退款成功FAIL—退款失败PROCESSING—退款处理中CHANGE—转入代发
        private String refund_status_0;//退款状态SUCCESS—退款成功FAIL—退款失败PROCESSING—退款处理中CHANGE—转入代发
        private String refund_recv_accout_$n;

        public Response(String return_code) {
            this.return_code = return_code;
        }

        public Response() {}
    }
}
