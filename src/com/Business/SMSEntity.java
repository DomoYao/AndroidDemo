package com.Business;

public class SMSEntity {

	/**
	 * ����Ϣ���
	 */
	private int _id;
	/**
	 * �Ի������
	 */
	private int thread_id;
	/**
	 * �����˵��ֻ���
	 */
	private String address;
	/**
	 * �����ˣ���������ϵ�б��е����
	 */
	private int person;
	/**
	 * ����
	 */
	private long date;
	/**
	 * Э��
	 */
	private int protocol;
	/**
	 * �Ƿ��Ѷ�
	 */
	private int read;
	/**
	 * ״̬
	 */
	private int status;
	/**
	 * ����
	 */
	private int type;
	/**
	 * ��Ϣ����
	 */
	private String body;
	/**
	 * ���ŷ������ĺ����š�
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