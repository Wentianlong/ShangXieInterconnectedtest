package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/*
        *  @项目名：  ShangXieInterconnected
        *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater
        *  @文件名:   UserUpdaterModel
        *  @创建者:   文
        *  @创建时间:  2017/3/9 12:03
        *  @描述：    获取版本号的类
        */
public class PackagetUtils {
    private static final String TAG = "PackagetUtils";

    /**
     * 获取当前的版本
     * @param context
     * @return 返回版本
     */
    public static String  getVersionName(Context context){
        String versionName = "";
        //获取到包管理器
        PackageManager pm = context.getPackageManager();
        try {
            //获取manifest下的所有信息
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //获取版本号
                versionName = packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            versionName="未知";
            e.printStackTrace();
        }
        return versionName;
    }


    public static int getVersionCode(Context context) {
        int versionCode = -1;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);

            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = -1;
            e.printStackTrace();
        }
        return versionCode;
    }
}
