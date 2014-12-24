package com.Business;

import android.os.StatFs;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class IOUtils {
	private static final Charset UTF_8 = Charset.forName("UTF-8");

	/**
	 * Unconditionally close a <code>Closeable</code>.
	 * <p>
	 * Equivalent to {@link Closeable#close()}, except any exceptions will be
	 * ignored. This is typically used in finally blocks.
	 * <p>
	 * Example code:
	 *
	 * <pre>
	 * Closeable closeable = null;
	 * try {
	 * 	closeable = new FileReader(&quot;foo.txt&quot;);
	 * 	// process closeable
	 * 	closeable.close();
	 * } catch (Exception e) {
	 * 	// error handling
	 * } finally {
	 * 	IOUtils.closeQuietly(closeable);
	 * }
	 * </pre>
	 *
	 * @param closeable
	 *            the object to close, may be null or already closed
	 * @since Commons IO 2.0
	 */
	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException ioe) {
				// ignore
			}
		}
	}

	/**
	 * ��������ת��Ϊ�ַ�����Ĭ�ϲ���UTF-8���롣
	 *
	 * @param in
	 *            ������
	 * @return ת��֮����ַ�����
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream in) throws IOException {
		return readFully(new InputStreamReader(in, UTF_8));
	}

	/**
	 * ��������ת��Ϊ�ַ�����
	 *
	 * @param in
	 *            ������
	 * @param charset
	 *            �ַ����롣
	 * @return ת��֮����ַ�����
	 * @throws IOException
	 */
	public static String inputStreamToString(InputStream in, Charset charset) throws IOException {
		return readFully(new InputStreamReader(in, charset));
	}

	/**
	 * ���ַ������ͷ���{@code reader}��ʣ�µ����ݡ�
	 *
	 * @param reader
	 * @return ����Reader����ʣ�µ����ݡ�
	 * @throws IOException
	 */
	public static String readFully(Reader reader) throws IOException {
		try {
			StringWriter writer = new StringWriter();
			char[] buffer = new char[1024];
			int count;
			while ((count = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, count);
			}
			return writer.toString();
		} finally {
			reader.close();
		}
	}

	/**
	 * ��ȡ�ļ�·���ռ��С
	 * 
	 * @param path
	 * @return �����ļ�·���Ŀռ��С
	 */
	public static long getUsableSpace(File path) {
		final StatFs sf = new StatFs(path.getPath());
		return (long) sf.getBlockSize() * (long) sf.getAvailableBlocks();
	}
}