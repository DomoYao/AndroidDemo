package com.Business;



/**
 * 打印日志类，该类封装了tag。
 * 
 * @author Geek_Soledad <a target="_blank" href=
 *         "http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=XW9vbmxuZG5qamQdLCxzPjIw"
 *         style="text-decoration:none;"><img src=
 *         "http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"
 *         /></a>
 */
public final class Log4AK extends LogAuto {

	private Log4AK(Class<?> clazz) {
		super(clazz);
	}

	@Override
	protected String initTag(Class<?> clazz) {
		return String.format("%s: %s", Version.ANDROIDKIT_NAME, clazz.getSimpleName());
	}

	public static final Log4AK getLog(Class<?> clazz) {
		return new Log4AK(clazz);
	}
}
