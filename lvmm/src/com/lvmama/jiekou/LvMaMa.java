package com.lvmama.jiekou;

import java.io.Serializable;

/**
 * @author yao
 * @date 创建时间：2017年3月17日下午2:53:07
 * @version 1.0
 */
public class LvMaMa implements Serializable{
	
	private static final long serialVersionUID = 985184791869632449L;
	private String uid ; //驴妈妈给供应商的ID，用来标记，哪些订单来自驴妈妈,，必存
	private String password ; //驴妈妈给供应商的密码，来校验uid合法性，必存
	private String timestamp ; //接口调用时间：yyyyMMddHHmmss，必存
	private String visitTime ; //客户游玩时间：yyyyMMddHHmmss
	private String supplierGoodsId ; //供应商商品ID，只有唯一商品,固定值，必存
	private String settlePrice ; //驴妈妈的结算价
	private String num ; //用户购买数量(你们返回对应数量的码)(最大50)，必存
	private String serialNo ; //驴妈妈订单号(方便你们要和你们对应的订单做关联)，必存
	private String sign ; //加密规则见上文“签名算法”，必存
	private String idNum ; //身份证号，或者其他类型的证件号，必存
	private String idType ; //证证件类型，，一般为身份证（ID_CARD），必存
	private String mobile ; //用户的手机号
	private String name ; //用户姓名
	
//	private String contacts ; //Json格式字符串，描述如下，详情见下面例子
//	private String travellerList ; //Json格式字符串，描述如下，详情见下面例子
	
	@Override
	public String toString() {
		return "LvMaMa [uid=" + uid + ", password=" + password + ", timestamp=" + timestamp + ", supplierGoodsId="
				+ supplierGoodsId + ", num=" + num + ", serialNo=" + serialNo + ", sign=" + sign + ", idNum=" + idNum
				+ ", idType=" + idType + "]";
	}
	
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getSupplierGoodsId() {
		return supplierGoodsId;
	}
	public void setSupplierGoodsId(String supplierGoodsId) {
		this.supplierGoodsId = supplierGoodsId;
	}
	public String getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(String settlePrice) {
		this.settlePrice = settlePrice;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
