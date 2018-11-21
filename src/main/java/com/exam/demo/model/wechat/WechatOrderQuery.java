package com.exam.demo.model.wechat;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 订单查询
 **/
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

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getErr_code() {
            return err_code;
        }

        public void setErr_code(String err_code) {
            this.err_code = err_code;
        }

        public String getErr_code_des() {
            return err_code_des;
        }

        public void setErr_code_des(String err_code_des) {
            this.err_code_des = err_code_des;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getDevice_info() {
            return device_info;
        }

        public void setDevice_info(String device_info) {
            this.device_info = device_info;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }

        public String getOut_trade_no() {
            return out_trade_no;
        }

        public void setOut_trade_no(String out_trade_no) {
            this.out_trade_no = out_trade_no;
        }

        public Integer getTotal_fee() {
            return total_fee;
        }

        public void setTotal_fee(Integer total_fee) {
            this.total_fee = total_fee;
        }

        public Integer getSettlement_total_fee() {
            return settlement_total_fee;
        }

        public void setSettlement_total_fee(Integer settlement_total_fee) {
            this.settlement_total_fee = settlement_total_fee;
        }

        public String getBank_type() {
            return bank_type;
        }

        public void setBank_type(String bank_type) {
            this.bank_type = bank_type;
        }

        public String getTrade_state() {
            return trade_state;
        }

        public void setTrade_state(String trade_state) {
            this.trade_state = trade_state;
        }

        public String getTrade_state_desc() {
            return trade_state_desc;
        }

        public void setTrade_state_desc(String trade_state_desc) {
            this.trade_state_desc = trade_state_desc;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getFee_type() {
            return fee_type;
        }

        public void setFee_type(String fee_type) {
            this.fee_type = fee_type;
        }

        public Integer getCash_fee() {
            return cash_fee;
        }

        public void setCash_fee(Integer cash_fee) {
            this.cash_fee = cash_fee;
        }

        public String getCash_fee_type() {
            return cash_fee_type;
        }

        public void setCash_fee_type(String cash_fee_type) {
            this.cash_fee_type = cash_fee_type;
        }

        public Integer getCoupon_fee() {
            return coupon_fee;
        }

        public void setCoupon_fee(Integer coupon_fee) {
            this.coupon_fee = coupon_fee;
        }

        public Integer getCoupon_count() {
            return coupon_count;
        }

        public void setCoupon_count(Integer coupon_count) {
            this.coupon_count = coupon_count;
        }

        public String getCoupon_type_$n() {
            return coupon_type_$n;
        }

        public void setCoupon_type_$n(String coupon_type_$n) {
            this.coupon_type_$n = coupon_type_$n;
        }

        public String getCoupon_id_$n() {
            return coupon_id_$n;
        }

        public void setCoupon_id_$n(String coupon_id_$n) {
            this.coupon_id_$n = coupon_id_$n;
        }

        public String getCoupon_fee_$n() {
            return coupon_fee_$n;
        }

        public void setCoupon_fee_$n(String coupon_fee_$n) {
            this.coupon_fee_$n = coupon_fee_$n;
        }

        public String getAttach() {
            return attach;
        }

        public void setAttach(String attach) {
            this.attach = attach;
        }

        public String getTime_end() {
            return time_end;
        }

        public void setTime_end(String time_end) {
            this.time_end = time_end;
        }
    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
