package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils;

import android.app.Application;
import android.content.Context;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils
 *  @文件名:   ShangXieInterconnecredApplication
 *  @创建者:   文
 *  @创建时间:  2017/3/16 9:46
 *  @描述：    一个应用进程的application类
 */
public class ShangXieInterconnecredApplication extends Application {
    private static final String TAG = "ShangXieInterconnecredApplication";

    private static Context mContext;
    /**
     * 应用创建的时候就会调用onCreat,初始化应用层级或对象
     */

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContextObject(){
        return mContext;
    }
}
