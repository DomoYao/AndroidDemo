package com.Business;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * �̳��Գ�����SQLiteOpenHelper��ͨ���������Դ���sql������������������ݿ⡣
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
	private static final int INIT_VERSION = 1;

	/**
	 * �������������ݿ�ʱִ�е���䡣
	 */
	private List<String> sqlStatementExed;

	/**
	 * ����Ǵ������������ݿ⣬��ʹ�ô�List�����Ĺ��췽����
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 * @param factory
	 *            to use for creating cursor objects, or null for the default
	 * @param version
	 *            number of the database (starting at 1); if the database is
	 *            older, onUpgrade(SQLiteDatabase, int, int) will be used to
	 *            upgrade the database; if the database is newer,
	 *            onDowngrade(SQLiteDatabase, int, int) will be used to
	 *            downgrade the database
	 */
	public SimpleSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		sqlStatementExed = null;
	}

	/**
	 * ��SQL���Ĺ��췽������SQL��佫�����ݿⴴ��������ʱִ�С�
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 * @param factory
	 *            to use for creating cursor objects, or null for the default
	 * @param version
	 *            number of the database (starting at 1); if the database is
	 *            older, onUpgrade(SQLiteDatabase, int, int) will be used to
	 *            upgrade the database; if the database is newer,
	 *            onDowngrade(SQLiteDatabase, int, int) will be used to
	 *            downgrade the database
	 * @param sqlStatementExed
	 *            �����ݿⴴ����������ʱ��ִ�е���䡣
	 */
	public SimpleSQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version, List<String> sqlStatementExed) {
		super(context, name, factory, version);
		this.sqlStatementExed = sqlStatementExed;
	}

	/**
	 * ����Ǵ������������ݿ⣬��ʹ�ô�List�����Ĺ��췽����
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 * @param version
	 *            number of the database (starting at 1); if the database is
	 *            older, onUpgrade(SQLiteDatabase, int, int) will be used to
	 *            upgrade the database; if the database is newer,
	 *            onDowngrade(SQLiteDatabase, int, int) will be used to
	 *            downgrade the database
	 */
	public SimpleSQLiteOpenHelper(Context context, String name, int version) {
		super(context, name, null, version);
		sqlStatementExed = null;
	}

	/**
	 * ����Ǵ������������ݿ⣬��ʹ�ô�List�����Ĺ��췽����
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 */
	public SimpleSQLiteOpenHelper(Context context, String name) {
		super(context, name, null, INIT_VERSION);
		sqlStatementExed = null;
	}

	/**
	 * ����Ǵ������������ݿ⣬��ʹ�ô�List�����Ĺ��췽����
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param name
	 *            of the database file, or null for an in-memory database
	 * @param version
	 *            number of the database (starting at 1); if the database is
	 *            older, onUpgrade(SQLiteDatabase, int, int) will be used to
	 *            upgrade the database; if the database is newer,
	 *            onDowngrade(SQLiteDatabase, int, int) will be used to
	 *            downgrade the database
	 * @param sqlCreateStatement
	 *            �ڴ������������ݿ�ʱҪִ�е���䡣
	 */
	public SimpleSQLiteOpenHelper(Context context, String name, int version,
			List<String> sqlCreateStatement) {
		super(context, name, null, version);
		this.sqlStatementExed = sqlCreateStatement;
	}

	/**
	 * @param context
	 * @param name
	 * @param sqlCreateStatement
	 *            �ڴ������������ݿ�ʱҪִ�е���䡣
	 */
	public SimpleSQLiteOpenHelper(Context context, String name,
			List<String> sqlCreateStatement) {
		super(context, name, null, INIT_VERSION);
		this.sqlStatementExed = sqlCreateStatement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	@Deprecated
	public void onCreate(SQLiteDatabase db) {
		exeSqlStatementExed(db);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	@Deprecated
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > oldVersion) {
			exeSqlStatementExed(db);
		}
	}

	/**
	 * ��ʼ�����������ݿ�ʱִ�е�SQL��䡣��
	 */
	private void exeSqlStatementExed(SQLiteDatabase db) {
		if (sqlStatementExed != null) {
			for (String statement : sqlStatementExed) {
				db.execSQL(statement);
			}
		}
	}
}