package com.Business;


import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;

import java.util.ArrayList;
import java.util.List;

public class SMSUtil {

	// ϵͳ�ж�����ص�Э��
	/**
	 * ȫ������
	 */
	public static final String SMS_ALL = "content://sms";
	/**
	 * �ռ���
	 */
	public static final String SMS_INBOX = "content://sms/inbox";
	/**
	 * �ѷ���
	 */
	public static final String SMS_SEND = "content://sms/send";
	/**
	 * �ݸ���
	 */
	public static final String SMS_DRAFT = "content://sms/draft";
	/**
	 * ������
	 */
	public static final String SMS_OUTBOX = "content://sms/outbox";
	/**
	 * ����ʧ��
	 */
	public static final String SMS_FAILED = "content://sms/failed";
	/**
	 * �����б�
	 */
	public static final String SMS_QUEUED = "content://sms/queued";

	public static final Uri URI_ALL = Uri.parse(SMS_ALL);
	public static final Uri URI_INBOX = Uri.parse(SMS_INBOX);
	public static final Uri URI_SEND = Uri.parse(SMS_SEND);
	public static final Uri URI_DRAFT = Uri.parse(SMS_DRAFT);
	public static final Uri URI_OUTBOX = Uri.parse(SMS_OUTBOX);
	public static final Uri URI_FAILED = Uri.parse(SMS_FAILED);
	public static final Uri URI_QUEUED = Uri.parse(SMS_QUEUED);

	// ������ص�����
	public static final String COL_ID = "_id";
	public static final String COL_THREAD_ID = "thread_id";
	public static final String COL_ADDRESS = "address";
	public static final String COL_PERSON = "person";
	public static final String COL_DATE = "date";
	public static final String COL_PROTOCOL = "protocol";
	public static final String COL_READ = "read";
	public static final String COL_STATUS = "status";
	public static final String COL_TYPE = "type";
	public static final String COL_BODY = "body";
	public static final String COL_SERVICE_CENTER = "service_center";

	// ���ŵ��������ֵ
	public static final int PROTO_SMS = 0;
	public static final int PROTO_MMS = 1;
	public static final int READ_NO = 0;
	public static final int READ_YES = 1;
	public static final int STATUS_RECEIVED = -1;
	public static final int STATUS_COMPLETE = 0;
	public static final int STATUS_PENDING = 64;
	public static final int STATUS_FAILED = 128;
	public static final int TYPE_ALL = 0;
	public static final int TYPE_INBOX = 1;
	public static final int TYPE_SEND = 2;
	public static final int TYPE_DRAFT = 3;
	public static final int TYPE_OUTBOX = 4;
	public static final int TYPE_FAILED = 5;
	public static final int TYPE_QUEUED = 6;

	// ����ʽ
	public static final String ORDER_DATE_DESC = "date desc";
	public static final String ORDER_DATE_ASC = "date asc";

	/**
	 * �����ڵ������򣬻�ȡ�����ݰ������룬�����ˣ��������ݣ����ڣ����ͣ��緢�ͳ�ȥ�ģ����յ��ģ��ݸ���ĵȵȣ�
	 * 
	 * @param context
	 * @param uri
	 *            ��ȡ��ַ
	 * @return
	 */
	public static List<SMSEntity> query(Context context, Uri uri) {
		return query(context, uri, ORDER_DATE_DESC);
	}

	/**
	 * ��ȡ�����ݰ������룬�����ˣ��������ݣ����ڣ����ͣ��緢�ͳ�ȥ�ģ����յ��ģ��ݸ���ĵȵȣ�
	 * 
	 * @param context
	 * @param uri
	 *            ��ȡ���ŵĵ�ַ
	 * @param sortOrder
	 *            ����ʽ
	 * @return
	 */
	public static List<SMSEntity> query(Context context, Uri uri, String sortOrder) {
		ContentResolver cr = context.getContentResolver();
		final String[] projection = new String[] { COL_ID, COL_ADDRESS, COL_PERSON, COL_BODY,
				COL_DATE, COL_TYPE };
		Cursor cur = cr.query(uri, projection, null, null, sortOrder);
		if (cur.moveToFirst()) {
			List<SMSEntity> smsEntities = new ArrayList<SMSEntity>();
			final int idIndex = cur.getColumnIndex(COL_ID);
			final int addressIndex = cur.getColumnIndex(COL_ADDRESS);
			final int personIndex = cur.getColumnIndex(COL_PERSON);
			final int bodyIndex = cur.getColumnIndex(COL_BODY);
			final int dateIndex = cur.getColumnIndex(COL_DATE);
			final int typeIndex = cur.getColumnIndex(COL_TYPE);
			do {
				SMSEntity sms = new SMSEntity();
				sms.set_id(cur.getInt(idIndex));
				sms.setAddress(cur.getString(addressIndex));
				sms.setPerson(cur.getInt(personIndex));
				sms.setBody(cur.getString(bodyIndex));
				sms.setDate(cur.getLong(dateIndex));
				sms.setType(cur.getInt(typeIndex));
				smsEntities.add(sms);
			} while (cur.moveToNext());
			return smsEntities;
		}
		return null;
	}

	/**
	 * ���ݶ���idɾ�����š�
	 * 
	 * @param context
	 * @param uri
	 * @param id
	 *            ����id��
	 * @return ����ɾ����������
	 */
	public static int deleteById(Context context, Uri uri, int id) {
		return context.getContentResolver().delete(uri, "id=?",
				new String[] { Integer.toString(id) });
	}

	/**
	 * ��������ɾ�����š�
	 * 
	 * @param context
	 * @param uri
	 * @param where
	 *            ����
	 * @param selectionArgs
	 *            ������ֵ
	 * @return ����ɾ��������
	 */
	public static int delete(Context context, Uri uri, String where, String[] selectionArgs) {
		return context.getContentResolver().delete(uri, where, selectionArgs);
	}

	/**
	 * ����ĳһ����ɾ����Ϣ��
	 * 
	 * @param context
	 * @param uri
	 * @param colName
	 *            ������
	 * @param colValue
	 *            ����ֵ
	 * @return ����ɾ��������
	 */
	public static int deleteByCol(Context context, Uri uri, String colName, String colValue) {
		return context.getContentResolver().delete(uri, colName + "=?", new String[] { colValue });
	}

	/**
	 * ������š�
	 * 
	 * @param context
	 * @param uri
	 * @param values
	 * @return �����Ķ��ŵ�uri
	 */
	public static Uri insert(Context context, Uri uri, ContentValues values) {
		return context.getContentResolver().insert(uri, values);
	}

	
	/**
	 * ���Ͷ��š�
	 * 
	 * @param context
	 *            Context����
	 * @param number
	 *            �ռ��˺���
	 * @param content
	 *            �������ݡ�
	 */
	public static void sendSMS(Context context, String number, String content) {
		SmsManager smsManager = SmsManager.getDefault();
		PendingIntent sendIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
		smsManager.sendTextMessage(number, null, content, sendIntent, null);
	}
}