package com.Business;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;


/**
 * KV��һ���򵥵�key-value��ȡ�࣬��SharePreference�����˷�װ��
 * 
 * @author Geek_Soledad (66704238@51uc.com)
 */
public class KV {
    private final static Log4AK log = Log4AK.getLog(KV.class);
    private final SharedPreferences mSP;
    private Editor mEditor;

    /**
     * ���췽����
     * 
     * @param context
     * @param kvName
     *            ��ֵ�����ơ�
     * @param mode
     *            �򿪵�ģʽ��ֵΪContext.MODE_APPEND, Context.MODE_PRIVATE,
     *            Context.WORLD_READABLE, Context.WORLD_WRITEABLE.
     */
    public KV(Context context, String kvName, int mode) {
        mSP = context.getSharedPreferences(kvName, mode);
        mEditor = mSP.edit();
    }

    /**
     * ����������Ĭ��ΪContext.MODE_PRIVATE�Ĵ�ģʽ��
     * 
     * @param context
     * @param kvName
     *            ���̱�����
     */
    public KV(Context context, String kvName) {
        mSP = context.getSharedPreferences(kvName, Context.MODE_PRIVATE);
        mEditor = mSP.edit();
    }

    /**
     * ��ȡ�����ŵ�boolean����
     * 
     * @param key
     *            ����
     * @param defValue
     *            ��������ʱ���ص�Ĭ��ֵ��
     * @return ���ػ�ȡ����ֵ����������ʱ����Ĭ��ֵ��
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSP.getBoolean(key, defValue);
    }

    /**
     * ��ȡ�����ŵ�int����
     * 
     * @param key
     *            ����
     * @param defValue
     *            ��������ʱ���ص�Ĭ��ֵ��
     * @return ���ػ�ȡ����ֵ����������ʱ����Ĭ��ֵ��
     */
    public int getInt(String key, int defValue) {
        return mSP.getInt(key, defValue);
    }

    /**
     * ��ȡ�����ŵ�long����
     * 
     * @param key
     *            ����
     * @param defValue
     *            ��������ʱ���ص�Ĭ��ֵ��
     * @return ���ػ�ȡ����ֵ����������ʱ����Ĭ��ֵ��
     */
    public long getLong(String key, long defValue) {
        return mSP.getLong(key, defValue);
    }

    /**
     * ��ȡ�����ŵ�float����
     * 
     * @param key
     *            ����
     * @param defValue
     *            ��������ʱ���ص�Ĭ��ֵ��
     * @return ���ػ�ȡ����ֵ����������ʱ����Ĭ��ֵ��
     */
    public float getFloat(String key, float defValue) {
        return mSP.getFloat(key, defValue);
    }

    /**
     * ��ȡ�����ŵ�String����
     * 
     * @param key
     *            ����
     * @param defValue
     *            ��������ʱ���ص�Ĭ��ֵ��
     * @return ���ػ�ȡ����ֵ����������ʱ����Ĭ��ֵ��
     */
    public String getString(String key, String defValue) {
        return mSP.getString(key, defValue);
    }

    /**
     * ��ȡ���м�ֵ�ԡ�
     * 
     * @return ��ȡ����������ֵ�ԡ�
     */
    public Map<String, ?> getAll() {
        return mSP.getAll();
    }

    /**
     * ����һ����ֵ�ԣ�������{@linkplain #commit()}������ʱ���档<br/>
     * ע�⣺�������value����boolean, byte(�ᱻת����int����),int, long, float,
     * String������ʱ����������toString()��������ֵ�ı��档
     * 
     * @param key
     *            �����ơ�
     * @param value
     *            ֵ��
     * @return ���õ�KV����
     */
    public KV put(String key, Object value) {
        if (value == null) {
            mEditor.putString(key, null);
        } else if (value instanceof Boolean) {
            mEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer || value instanceof Byte) {
            mEditor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            mEditor.putLong(key, (Long) value);
        } else if (value instanceof Float) {
            mEditor.putFloat(key, (Float) value);
        } else if (value instanceof String) {
            mEditor.putString(key, (String) value);
        } else {
            log.w("ֵ����Boolean, Integer, Byte, Long, Float, String������֮һ������������toString()���б���");
            mEditor.putString(key, value.toString());
        }
        return this;
    }

    /**
     * �����ֵ�ԡ�
     * 
     * @param key
     *            ����
     * @param value
     *            ֵ��<br/>
     *            <b>ע�⣺</b>�������value����boolean, byte(�ᱻת����int����),int, long,
     *            float, String������ʱ����������toString()��������ֵ�ı��档
     * @return ���ҽ����ύ�ɹ�ʱ����true, ���򷵻�false.
     */
    public boolean commit(String key, Object value) {
        return put(key, value).commit();
    }

    /**
     * �Ƴ���ֵ�ԡ�
     * 
     * @param key
     *            Ҫ�Ƴ��ļ����ơ�
     * @return ���õ�KV����
     */
    public KV remove(String key) {
        mEditor.remove(key);
        return this;
    }

    /**
     * ������м�ֵ�ԡ�
     * 
     * @return ���õ�KV����
     */
    public KV clear() {
        mEditor.clear();
        return this;
    }

    /**
     * �Ƿ����ĳ������
     * 
     * @param key
     *            ��ѯ�ļ����ơ�
     * @return ���ҽ��������ü�ʱ����true, ���򷵻�false.
     */
    public boolean contains(String key) {
        return mSP.contains(key);
    }

    /**
     * �ύ�޸ĵļ�ֵ�ԡ�
     * 
     * @return ���ҽ����ύ�ɹ�ʱ����true, ���򷵻�false.
     */
    public boolean commit() {
        boolean commit = mEditor.commit();
        mEditor = mSP.edit();
        return commit;
    }
}
