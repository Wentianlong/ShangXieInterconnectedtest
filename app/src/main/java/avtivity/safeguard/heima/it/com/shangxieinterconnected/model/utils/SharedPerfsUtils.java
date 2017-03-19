package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

/*
        *  @项目名：  ShangXieInterconnected
        *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater
        *  @文件名:   UserUpdaterModel
        *  @创建者:   文
        *  @创建时间:  2017/3/6 12:03
        *  @描述：    sharedPerfsUtils的封装类
        */
public class SharedPerfsUtils {
    private static final String TAG = "SharedPerfsUtils";
    public static SharedPreferences instance;

    public static SharedPreferences getInstance(Context context){
        if(instance == null){
            instance = context.getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        }
        return instance;
    }
    /**
     * 获取boolean的值,要给默认值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp = getInstance(context);
       return sp.getBoolean(key,defValue);
    }
    /**
     * 获取boolean的值,默认值是false
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context,String key){
        return getBoolean(context,key,false);
    }

    /**
     * 保存一个boolaen值
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences   sp = getInstance(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    /**
     * 获取一个String值,默认是null
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context,key,null);
    }

    /**
     * 获取一个String值
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = getInstance(context);
        return sp.getString(key,defValue);
    }

    public static void putString(Context context,String key,String value) {
        SharedPreferences        sp = getInstance(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }

}
