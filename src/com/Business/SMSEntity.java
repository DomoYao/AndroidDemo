package com.Business;

public class SMSEntity {

	/**
	 * 短消息序号
	 */
	private int _id;
	/**
	 * 对话的序号
	 */
	private int thread_id;
	/**
	 * 发送人的手机号
	 */
	private String address;
	/**
	 * 发件人，返回在联系列表中的序号
	 */
	private int person;
	/**
	 * 日期
	 */
	private long date;
	/**
	 * 协议
	 */
	private int protocol;
	/**
	 * 是否已读
	 */
	private int read;
	/**
	 * 状态
	 */
	private int status;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 消息内容
	 */
	private String body;
	/**
	 * 短信服务中心号码编号。
	 */
	private String service_center;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getThread_id() {
		return thread_id;
	}

	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getService_center() {
		return service_center;
	}

	public void setService_center(String service_center) {
		this.service_center = service_center;
	}
}