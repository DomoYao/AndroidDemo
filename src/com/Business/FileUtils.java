package com.Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


/**
 * ����Ĵ�����ȫ��ȡ��apache��Դ��Ŀcommons�е�commons-io���������ʵ��޸ģ�ʹ����ʺ����ֻ���ִ�С�
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class FileUtils {
	/**
	 * The number of bytes in a kilobyte.
	 */
	public static final long ONE_KB = 1024;

	/**
	 * The number of bytes in a megabyte.
	 */
	public static final long ONE_MB = ONE_KB * ONE_KB;

	/**
	 * The file copy buffer size (10 MB) ��ԭΪ30MB��Ϊ���ʺ����ֻ���ʹ�ã������Ϊ10MB��by
	 * Geek_Soledad)
	 */
	private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 10;

	/**
	 * <p>
	 * ��һ��Ŀ¼�µ��ļ�ȫ����������һ��Ŀ¼���棬���ұ����ļ����ڡ�
	 * </p>
	 * <p>
	 * ���Ŀ��Ŀ¼�����ڣ��򱻴����� ���Ŀ��Ŀ¼�Ѿ����ڣ��򽫻�ϲ������ļ��е����ݣ����г�ͻ���滻��Ŀ��Ŀ¼�е��ļ���
	 * </p>
	 * 
	 * @param srcDir
	 *            ԴĿ¼������Ϊnull�ұ�����ڡ�
	 * @param destDir
	 *            Ŀ��Ŀ¼������Ϊnull��
	 * @throws NullPointerException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼Ϊnull��
	 * @throws IOException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼��Ч��
	 * @throws IOException
	 *             ��������г���IO����
	 */
	public static void copyDirectoryToDirectory(File srcDir, File destDir)
			throws IOException {
		if (srcDir == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (srcDir.exists() && srcDir.isDirectory() == false) {
			throw new IllegalArgumentException("Source '" + destDir
					+ "' is not a directory");
		}
		if (destDir == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (destDir.exists() && destDir.isDirectory() == false) {
			throw new IllegalArgumentException("Destination '" + destDir
					+ "' is not a directory");
		}
		copyDirectory(srcDir, new File(destDir, srcDir.getName()), true);
	}

	/**
	 * <p>
	 * ��Ŀ¼����������Ŀ¼������һ���µ�λ�ã����ұ����ļ����ڡ�
	 * <p>
	 * ���Ŀ��Ŀ¼�����ڣ��򱻴����� ���Ŀ��Ŀ¼�Ѿ����ڣ��򽫻�ϲ������ļ��е����ݣ����г�ͻ���滻��Ŀ��Ŀ¼�е��ļ���
	 * <p>
	 * 
	 * @param srcDir
	 *            һ�����ڵ�ԴĿ¼������Ϊnull��
	 * @param destDir
	 *            �µ�Ŀ¼������Ϊnull��
	 * 
	 * @throws NullPointerException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼Ϊnull��
	 * @throws IOException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼��Ч��
	 * @throws IOException
	 *             ��������г���IO����
	 */
	public static void copyDirectory(File srcDir, File destDir)
			throws IOException {
		copyDirectory(srcDir, destDir, true);
	}

	/**
	 * ����Ŀ¼��һ���µ�λ�á�
	 * <p>
	 * �÷���������ָ����ԴĿ¼���������ݵ�һ���µ�Ŀ¼�С�
	 * </p>
	 * <p>
	 * ���Ŀ��Ŀ¼�����ڣ��򱻴����� ���Ŀ��Ŀ¼�Ѿ����ڣ��򽫻�ϲ������ļ��е����ݣ����г�ͻ���滻��Ŀ��Ŀ¼�е��ļ���
	 * </p>
	 * 
	 * @param srcDir
	 *            һ�����ڵ�ԴĿ¼������Ϊnull��
	 * @param destDir
	 *            �µ�Ŀ¼������Ϊnull��
	 * 
	 * @throws NullPointerException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼Ϊnull��
	 * @throws IOException
	 *             ���ԴĿ¼��Ŀ��Ŀ¼��Ч��
	 * @throws IOException
	 *             ��������г���IO����
	 */
	public static void copyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) throws IOException {
		if (srcDir == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (srcDir.exists() && srcDir.isDirectory() == false) {
			throw new IllegalArgumentException("Source '" + destDir
					+ "' is not a directory");
		}
		if (destDir == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (destDir.exists() && destDir.isDirectory() == false) {
			throw new IllegalArgumentException("Destination '" + destDir
					+ "' is not a directory");
		}
		if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
			throw new IOException("Source '" + srcDir + "' and destination '"
					+ destDir + "' are the same");
		}

		// Ϊ���㵱Ŀ��Ŀ¼��ԴĿ¼����������
		List<String> exclusionList = null;
		if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath())) {
			File[] srcFiles = srcDir.listFiles();
			if (srcFiles != null && srcFiles.length > 0) {
				exclusionList = new ArrayList<String>(srcFiles.length);
				for (File srcFile : srcFiles) {
					File copiedFile = new File(destDir, srcFile.getName());
					exclusionList.add(copiedFile.getCanonicalPath());
				}
			}
		}

		doCopyDirectory(srcDir, destDir, preserveFileDate, exclusionList);
	}

	/**
	 * Internal copy directory method.
	 * 
	 * @param srcDir
	 *            the validated source directory, must not be <code>null</code>
	 * @param destDir
	 *            the validated destination directory, must not be
	 *            <code>null</code>
	 * @param filter
	 *            the filter to apply, null means copy all directories and files
	 * @param preserveFileDate
	 *            whether to preserve the file date
	 * @param exclusionList
	 *            List of files and directories to exclude from the copy, may be
	 *            null
	 * @throws IOException
	 *             if an error occurs
	 * @since Commons IO 1.1
	 */
	private static void doCopyDirectory(File srcDir, File destDir,
			boolean preserveFileDate, List<String> exclusionList)
			throws IOException {
		// recurse
		File[] srcFiles = srcDir.listFiles();
		if (srcFiles == null) { // null if abstract pathname does not denote a
								// directory, or if an I/O error occurs
			throw new IOException("Failed to list contents of " + srcDir);
		}
		if (destDir.exists()) {
			if (destDir.isDirectory() == false) {
				throw new IOException("Destination '" + destDir
						+ "' exists but is not a directory");
			}
		} else {
			if (!destDir.mkdirs() && !destDir.isDirectory()) {
				throw new IOException("Destination '" + destDir
						+ "' directory cannot be created");
			}
		}
		if (destDir.canWrite() == false) {
			throw new IOException("Destination '" + destDir
					+ "' cannot be written to");
		}
		for (File srcFile : srcFiles) {
			File dstFile = new File(destDir, srcFile.getName());
			if (exclusionList == null
					|| !exclusionList.contains(srcFile.getCanonicalPath())) {
				if (srcFile.isDirectory()) {
					doCopyDirectory(srcFile, dstFile, preserveFileDate,
							exclusionList);
				} else {
					doCopyFile(srcFile, dstFile, preserveFileDate);
				}
			}
		}

		// Do this last, as the above has probably affected directory metadata
		if (preserveFileDate) {
			destDir.setLastModified(srcDir.lastModified());
		}
	}

	/**
	 * Internal copy file method.
	 * 
	 * @param srcFile
	 *            the validated source file, must not be <code>null</code>
	 * @param destFile
	 *            the validated destination file, must not be <code>null</code>
	 * @param preserveFileDate
	 *            whether to preserve the file date
	 * @throws IOException
	 *             if an error occurs
	 */
	private static void doCopyFile(File srcFile, File destFile,
			boolean preserveFileDate) throws IOException {
		if (destFile.exists() && destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile
					+ "' exists but is a directory");
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel input = null;
		FileChannel output = null;
		try {
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			input = fis.getChannel();
			output = fos.getChannel();
			long size = input.size();
			long pos = 0;
			long count = 0;
			while (pos < size) {
				count = (size - pos) > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE
						: (size - pos);
				pos += output.transferFrom(input, pos, count);
			}
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(fis);
		}

		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '"
					+ srcFile + "' to '" + destFile + "'");
		}
		if (preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}

	/**
	 * Opens a {@link FileInputStream} for the specified file, providing better
	 * error messages than simply calling <code>new FileInputStream(file)</code>
	 * .
	 * <p>
	 * At the end of the method either the stream will be successfully opened,
	 * or an exception will have been thrown.
	 * <p>
	 * An exception is thrown if the file does not exist. An exception is thrown
	 * if the file object exists but is a directory. An exception is thrown if
	 * the file exists but cannot be read.
	 * 
	 * @param file
	 *            the file to open for input, must not be <code>null</code>
	 * @return a new {@link FileInputStream} for the specified file
	 * @throws FileNotFoundException
	 *             if the file does not exist
	 * @throws IOException
	 *             if the file object is a directory
	 * @throws IOException
	 *             if the file cannot be read
	 * @since Commons IO 1.3
	 */
	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file
						+ "' exists but is a directory");
			}
			if (file.canRead() == false) {
				throw new IOException("File '" + file + "' cannot be read");
			}
		} else {
			throw new FileNotFoundException("File '" + file
					+ "' does not exist");
		}
		return new FileInputStream(file);
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings. The file
	 * is always closed.
	 * 
	 * @param file
	 *            the file to read, must not be <code>null</code>
	 * @param encoding
	 *            the encoding to use, <code>null</code> means platform default
	 * @return the list of Strings representing each line in the file, never
	 *         <code>null</code>
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws java.io.UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(File file, String encoding)
			throws IOException {
		InputStream in = null;
		try {
			in = openInputStream(file);
			return readLines(in, encoding);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings using the
	 * default encoding for the VM. The file is always closed.
	 * 
	 * @param file
	 *            the file to read, must not be <code>null</code>
	 * @return the list of Strings representing each line in the file, never
	 *         <code>null</code>
	 * @throws IOException
	 *             in case of an I/O error
	 * @since Commons IO 1.3
	 */
	public static List<String> readLines(File file) throws IOException {
		return readLines(file, null);
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a list of Strings, one
	 * entry per line, using the default character encoding of the platform.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(InputStream input) throws IOException {
		InputStreamReader reader = new InputStreamReader(input);
		return readLines(reader);
	}

	/**
	 * Get the contents of an <code>InputStream</code> as a list of Strings, one
	 * entry per line, using the specified character encoding.
	 * <p>
	 * Character encoding names can be found at <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedInputStream</code>.
	 * 
	 * @param input
	 *            the <code>InputStream</code> to read from, not null
	 * @param encoding
	 *            the encoding to use, null means platform default
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(InputStream input, String encoding)
			throws IOException {
		if (encoding == null) {
			return readLines(input);
		} else {
			InputStreamReader reader = new InputStreamReader(input, encoding);
			return readLines(reader);
		}
	}

	/**
	 * Get the contents of a <code>Reader</code> as a list of Strings, one entry
	 * per line.
	 * <p>
	 * This method buffers the input internally, so there is no need to use a
	 * <code>BufferedReader</code>.
	 * 
	 * @param input
	 *            the <code>Reader</code> to read from, not null
	 * @return the list of Strings, never null
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             if an I/O error occurs
	 * @since Commons IO 1.1
	 */
	public static List<String> readLines(Reader input) throws IOException {
		BufferedReader reader = new BufferedReader(input);
		List<String> list = new ArrayList<String>();
		String line = reader.readLine();
		while (line != null) {
			list.add(line);
			line = reader.readLine();
		}
		return list;
	}

	/**
	 * ���ٿ����ļ���
	 * 
	 * @param is
	 *            ������Դ
	 * @param os
	 *            ����Ŀ��
	 * @throws IOException
	 */
	public static void copyFileFast(FileInputStream is, FileOutputStream os)
			throws IOException {
		FileChannel in = is.getChannel();
		FileChannel out = os.getChannel();
		in.transferTo(0, in.size(), out);
	}
}