package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.MainActivity;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.receiver
 *  @文件名:   UpdaterDownloadReceiver
 *  @创建者:   文
 *  @创建时间:  2017/3/13 22:05
 *  @描述：    检测下载完成的广播
 */
public class UpdaterDownloadReceiver
        extends BroadcastReceiver
{
    private static final String TAG = "UpdaterDownloadReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        //LogUtils.e("广播初始化了");
        String updater1 = intent.getStringExtra("updater1");
       // LogUtils.e(updater1);
        intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     //  if (!TextUtils.isEmpty(updater1)) {
       //     LogUtils.e("广播携带信息1");
            intent.putExtra("name", "111");
       // }
        context.startActivity(intent);
        //LogUtils.e("发送了广播");
    }
}

