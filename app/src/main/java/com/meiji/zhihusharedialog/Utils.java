package com.meiji.zhihusharedialog;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meiji on 2017/9/30.
 */

public class Utils {

    /**
     * 获取手机内所有支持分享的应用列表
     */
    public static ArrayList<AppInfo> getShareAppList(Context context, Intent intent) {
        ArrayList<AppInfo> shareAppInfos = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = getShareApps(context, intent);
        if (null == resolveInfos) {
            return null;
        } else {
            for (ResolveInfo resolveInfo : resolveInfos) {
                AppInfo appInfo = new AppInfo();
                appInfo.setPkgName(resolveInfo.activityInfo.packageName);
                appInfo.setLaunchClassName(resolveInfo.activityInfo.name);
                appInfo.setAppName(resolveInfo.loadLabel(packageManager).toString());
                appInfo.setAppIcon(resolveInfo.loadIcon(packageManager));
                shareAppInfos.add(appInfo);
            }
        }
        return shareAppInfos;
    }

    /**
     * 查询手机内所有支持分享的应用列表
     */
    public static List<ResolveInfo> getShareApps(Context context, Intent intent) {
        List<ResolveInfo> resolveInfoList;
        PackageManager pm = context.getPackageManager();
        resolveInfoList = pm.queryIntentActivities(intent, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
        // 按名称排序
//        ResolveInfo.DisplayNameComparator comparator = new ResolveInfo.DisplayNameComparator(pm);
//        Collections.sort(resolveInfoList, comparator);
        return resolveInfoList;
    }
}
