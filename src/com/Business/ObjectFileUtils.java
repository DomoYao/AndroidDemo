package com.Business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;


/**
 * �����ļ��ࡣ�������ڽ��������л�д���ļ��������ļ���ȡ�����л��Ķ���
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class ObjectFileUtils {
	/**
	 * д�����
	 * 
	 * @param object
	 *            Ҫд��Ķ���
	 * @param path
	 *            �洢д�������ļ���·��
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void writeObject(final Serializable object, final String path)
			throws FileNotFoundException, IOException {
		writeObject(object, new FileOutputStream(path));
	}

	/**
	 * д������ļ���
	 * 
	 * @param object
	 *            Ҫд��Ķ���
	 * @param os
	 *            д������ļ��������
	 * @throws IOException
	 */
	public static void writeObject(final Serializable object,
			final OutputStream os) throws IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(os);
			oos.writeObject(object);
		} finally {
			IOUtils.closeQuietly(oos);
			IOUtils.closeQuietly(os);
		}
	}

	/**
	 * ��������
	 * 
	 * @param path
	 *            Ҫ��ȡ�Ķ����ļ���·��
	 * @return ���ض����Ķ���
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws StreamCorruptedException
	 * @throws ClassNotFoundException
	 */
	public static Object readObject(String path)
			throws StreamCorruptedException, FileNotFoundException,
			IOException, ClassNotFoundException {
		return readObject(new FileInputStream(path));
	}

	/**
	 * ��������
	 * 
	 * @param is
	 *            �����ļ���������
	 * @return �����Ķ���
	 * @throws StreamCorruptedException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object readObject(InputStream is)
			throws StreamCorruptedException, IOException, ClassNotFoundException {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(is);
			return ois.readObject();
		} finally {
			IOUtils.closeQuietly(ois);
			IOUtils.closeQuietly(is);
		}
	}
}