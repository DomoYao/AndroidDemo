package com.Business;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;



import java.util.Stack;

/**
 * Ӧ�ó���Activity�����ࣺ����Activity�����Ӧ�ó����˳�
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AppManager {
    private static final Log4AK log = Log4AK.getLog(AppManager.class);

    private static Stack<Activity> activityStack = new Stack<Activity>();

    /**
     * ���Activity����ջ
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
    }

    /**
     * ��ȡ��ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public static Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * ������ǰActivity����ջ�����һ��ѹ��ģ�
     */
    public static void finishCurrentActivity() {
        Activity activity = activityStack.pop();
        activity.finish();
    }

    /**
     * ����ָ����Activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * ����ָ��������Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * ��������Activity
     */
    public static void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null) {
                activity.finish();
            }
        }
        activityStack.clear();
    }

    /**
     * �˳�Ӧ�ó���
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager manager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            manager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            log.w(e);
        }
    }
}