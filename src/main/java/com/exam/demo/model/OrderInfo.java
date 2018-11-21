package com.exam.demo.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 */
public class OrderInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 订单号
	private String orderNo;
	// 买家用户id
	private String buyerUserId;
	// 买家用户姓名
	private String buyerUserName;
	// 卖家用户id
	private String sellerUserId;
	// 卖家用户姓名
	private String sellerUserName;
	// 卖家用户等级id
	private Long sellerUserLevelId;
	// 卖家用户类型id
	private Long sellerUserTypeId;
	// 卖家与平台的分成比例
	private Integer sellerMoneyShare = 0;
	// 虚拟物品类型 1-购买资料 2-购买VIP 3-购买下载券
	private Integer goodsType;
	// 商品名称
	private String goodsName;
	// 商品id
	private String goodsId;
	//分类ID
	private String classId;
	// 下载券个数
	private Long voluNum;
	//下载券赠送个数
	private Long giveVoluNum;
	// 订单金额
	private Long payPrice = 0l;
	// 平台分成金额
	private Long platformPrice = 0l;
	// 卖家分成金额
	private Long sellerPrice;
	// 订单状态 0-待支付 1-支付进行中 2-支付成功 3-支付失败 4-订单取消 5-订单异常（回调金额与订单金额不一致）
	private Integer orderStatus = 0;
	// 下单时间
	private Date orderTime;
	// 支付时间
	private Date payTime;
	// 订单取消时间
	private Date cancelTime;
	// 订单删除时间
	private Date deleteTime;
	//用户操作状态  0-正常  9-删除
	private Integer userOpt=0;
	//退款状态  0-未申请退款  1-退款申请中  2-退款审核中  3-退款审核不通过 4-退款成功  5-退款失败 7-退款异常 申请退款金额不等于回调退款金额
	private Integer refundStatus=0;
	//退款金额
	private Long refundPrice;
	//退款申请时间
	private Date refundApplyTime;
	// 退款完成时间
	private Date refundFinishTime;
	// 提现申请时间
	private Date withdrawApplyTime;
	// 提现批次id
	private String withdrawId;
	// 提现状态 0-未提现 1-提现进行中 2-提现成功 3-提现失败
	private Integer withdrawStatus = 0;
	// 支付类型 wechat-微信支付 alipay-支付宝 union-银联
	private String payType;
	private Integer sourceMode;//来源渠道 0-pc 1-m版
	private String clientIp;//客户端id

	private Long[] vipActId;//vip活动id
	private Long sellingPrice;//销售价格
	private Long prefPrice;//优惠总价
	private String prefRemark;//优惠备注（新人首次优惠10元 + 活动优惠20元）
	private List<Map<String,Object>> prefList;//优惠明细信息


	//来源网址
	private Integer ref; //0-正常，1-360，2-小米
	private String referrer; //上级页面
	private String remark;//备注  用作临时记录数据  a-仅在线阅读文档AB测试的A页面，b-仅在线阅读文档AB测试的B页面
	private String fid;		// 资料ID

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	//对账状态   	   0-未对账    1-对账成功   2-对账异常（
	//												a-业务系统订单状态【待支付或者已取消】-->第三方记录中状态为支付成功；
	//												b-业务系统订单状态【已支付】-->第三方支付找不到订单或者支付失败或者已退款
	//												c-业务系统的支付金额!=第三方支付订单的金额）
	private Integer billStatus;
	//对账备注信息     对账成功 ;支付异常具体信息
	private String billRemark;



	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(Integer billStatus) {
		this.billStatus = billStatus;
	}

	public String getBillStatusFormat() {
		if(billStatus == null || billStatus == 0) {
			return "未对账";
		}else if (billStatus == 1){
			return "对账成功";
		}else if (billStatus ==2){
			return "对账异常";
		}else{
			return null;
		}
	}

	public Long getVoluNum() {
		return voluNum;
	}

	public void setVoluNum(Long voluNum) {
		this.voluNum = voluNum;
	}

	public Long getSellerUserLevelId() {
		return sellerUserLevelId;
	}

	public void setSellerUserLevelId(Long sellerUserLevelId) {
		this.sellerUserLevelId = sellerUserLevelId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderNoFormat() {
		return String.valueOf(orderNo) + "\t";
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public String getBuyerUserName() {
		return buyerUserName;
	}

	public void setBuyerUserName(String buyerUserName) {
		this.buyerUserName = buyerUserName;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getSellerUserName() {
		return sellerUserName;
	}

	public void setSellerUserName(String sellerUserName) {
		this.sellerUserName = sellerUserName;
	}

	public Long getSellerUserTypeId() {
		return sellerUserTypeId;
	}

	public void setSellerUserTypeId(Long sellerUserTypeId) {
		this.sellerUserTypeId = sellerUserTypeId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getSellerUserTypeIdFormat() {
		String format = "";
		if(sellerUserTypeId == null || sellerUserTypeId == 0) {
			return format;
		}
		switch (Integer.parseInt(String.valueOf(sellerUserTypeId))) {
		case 1:
			format = "普通用户";
			break;
		case 2:
			format = "个人认证";
			break;
		case 3:
			format = "机构认证";
			break;
		default:
			break;
		}
		return format;
	}

	public Integer getSellerMoneyShare() {
		return sellerMoneyShare;
	}

	public void setSellerMoneyShare(Integer sellerMoneyShare) {
		this.sellerMoneyShare = sellerMoneyShare;
	}

	public String getSellerMoneyShareFormat() {
		return String.valueOf(sellerMoneyShare) + "%";
	}

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsTypeFormat() {
		String format = "";
		switch (goodsType) {
		case 1:
			format = "购买资料";
			break;
		case 2:
			format = "购买VIP";
			break;
		case 3:
			format = "购买下载券";
			break;
		default:
			break;
		}
		return format;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Long getPayPrice() {
		return payPrice;
	}
	
	public String getPayPriceFormat() {
		if(payPrice != null) {
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置
			return decimalFormat.format((double)payPrice / 100) + "\t";
		}
		return "0";
	}

	public void setPayPrice(Long payPrice) {
		this.payPrice = payPrice;
	}

	//平台成本
	public String getPlatformCostFormat() {
		if(payPrice != null && sellerMoneyShare != null) {
			DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");//格式化设置
			return decimalFormat.format((double)payPrice*sellerMoneyShare/ 10000) + "\t";
		}
		return "0";
	}

	public Long getPlatformPrice() {
		return platformPrice;
	}

	public void setPlatformPrice(Long platformPrice) {
		this.platformPrice = platformPrice;
	}

	public Long getSellerPrice() {
		return sellerPrice;
	}

	public void setSellerPrice(Long sellerPrice) {
		this.sellerPrice = sellerPrice;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusFormat() {
		String format = "";
		switch (orderStatus) {
		case 0:
			format = "待支付";
			break;
		case 1:
			format = "支付进行中";
			break;
		case 2:
			format = "支付成功";
			break;
		case 3:
			format = "支付失败";
			break;
		case 4:
			format = "订单取消";
			break;
		default:
			format = "";
			break;
		}
		return format;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderTimeFormat() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderTime) + "\t";
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Integer getUserOpt() {
		return userOpt;
	}

	public void setUserOpt(Integer userOpt) {
		this.userOpt = userOpt;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Long getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(Long refundPrice) {
		this.refundPrice = refundPrice;
	}

	public Date getRefundApplyTime() {
		return refundApplyTime;
	}

	public void setRefundApplyTime(Date refundApplyTime) {
		this.refundApplyTime = refundApplyTime;
	}

	public Date getRefundFinishTime() {
		return refundFinishTime;
	}

	public void setRefundFinishTime(Date refundFinishTime) {
		this.refundFinishTime = refundFinishTime;
	}

	public Date getWithdrawApplyTime() {
		return withdrawApplyTime;
	}

	public void setWithdrawApplyTime(Date withdrawApplyTime) {
		this.withdrawApplyTime = withdrawApplyTime;
	}

	public String getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(String withdrawId) {
		this.withdrawId = withdrawId;
	}

	public Integer getWithdrawStatus() {
		return withdrawStatus;
	}

	public void setWithdrawStatus(Integer withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayTypeFormat() {
		String format = "";
		switch (payType) {
		case "wechat":
			format = "微信支付";
			break;
		case "alipay":
			format = "支付宝支付";
			break;
		case "union":
			format = "银联支付";
			break;
		case "apple":
			format = "苹果内购";
			break;
		default:
			format = "其他支付";
			break;
		}
		return format;
	}

	public Integer getSourceMode() {
		return sourceMode;
	}
	
	public String getSourceModeFormat() {
		String format = "";
		switch (sourceMode) {
		case 0:
			format = "PC";
			break;
		case 1:
			format = "M";
			break;
		case 2:
			format = "android";
			break;
		case 3:
			format = "ios";
			break;
		default:
			format = "其他来源";
			break;
		}
		return format;
	}

	public void setSourceMode(Integer sourceMode) {
		this.sourceMode = sourceMode;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Long getGiveVoluNum() {
		return giveVoluNum;
	}

	public void setGiveVoluNum(Long giveVoluNum) {
		this.giveVoluNum = giveVoluNum;
	}

	public Long[] getVipActId() {
		return vipActId;
	}

	public void setVipActId(Long[] vipActId) {
		this.vipActId = vipActId;
	}

	public Long getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Long sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Long getPrefPrice() {
		return prefPrice;
	}

	public void setPrefPrice(Long prefPrice) {
		this.prefPrice = prefPrice;
	}

	public String getPrefRemark() {
		return prefRemark;
	}

	public void setPrefRemark(String prefRemark) {
		this.prefRemark = prefRemark;
	}

	public List<Map<String, Object>> getPrefList() {
		return prefList;
	}

	public void setPrefList(List<Map<String, Object>> prefList) {
		this.prefList = prefList;
	}

	public Integer getRef() {
		return ref;
	}

	public void setRef(Integer ref) {
		this.ref = ref;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
}
