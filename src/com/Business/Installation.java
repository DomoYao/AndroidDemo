package com.Business;
import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

/**
 * ��װ�����࣬�����������ɳ����ڸ��豸��Ψһ��ʶ����
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class Installation {

	private static String sID = null;
	private static final String INSTALLATION = "INSTALLATION-"
			+ UUID.nameUUIDFromBytes("androidkit".getBytes());

	/**
	 * ���ظ��豸�ڴ˳����ϵ�Ψһ��ʶ����
	 * 
	 * @param context
	 *            Context����
	 * @return ��ʾ���豸�ڴ˳����ϵ�Ψһ��ʶ����
	 */
	public static String getID(Context context) {
		if (sID == null) {
			synchronized (Installation.class) {
				if (sID == null) {
					File installation = new File(context.getFilesDir(), INSTALLATION);
					try {
						if (!installation.exists()) {
							writeInstallationFile(context, installation);
						}
						sID = readInstallationFile(installation);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sID;
	}

	/**
	 * ����ʾ���豸�ڸó����ϵ�Ψһ��ʶ��д������ļ�ϵͳ�С�
	 * 
	 * @param installation
	 *            ����Ψһ��ʶ����File����
	 * @return Ψһ��ʶ����
	 * @throws IOException
	 *             IO�쳣��
	 */
	private static String readInstallationFile(File installation) throws IOException {
		RandomAccessFile accessFile = new RandomAccessFile(installation, "r");
		byte[] bs = new byte[(int) accessFile.length()];
		accessFile.readFully(bs);
		accessFile.close();
		return new String(bs);
	}

	/**
	 * ���������ڳ����ļ�ϵͳ�еı�ʾ���豸�ڴ˳����ϵ�Ψһ��ʶ����
	 *
	 * @param context
	 *            Context����
	 * @param installation
	 *            ����Ψһ��ʶ����File����
	 * @throws IOException
	 *             IO�쳣��
	 */
	private static void writeInstallationFile(Context context, File installation)
			throws IOException {
		FileOutputStream out = new FileOutputStream(installation);
		String uuid = UUID.nameUUIDFromBytes(
				Secure.getString(context.getContentResolver(), Secure.ANDROID_ID).getBytes())
				.toString();
		Log.i("cfuture09-androidkit", uuid);
		out.write(uuid.getBytes());
		out.close();
	}
}
