package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.PackagetUtils;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter.UpldaterPresenter;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.IUpdapterView;

/**
 * 欢迎界面时,更新软件,
 */
public class UpdaterAndSplashActivity
        extends AppCompatActivity
        implements IUpdapterView
{


    private static final String TAG                                = "tag";
    private UpldaterPresenter mUpdater;
    private ProgressDialog    mPd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //初始化p层
        mUpdater = new UpldaterPresenter(this);
    }

    /**
     * 显示对话框的方法,被p层调用
     */
    @Override
    public void showAlertDialoUpdater() {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("是否下载新版");
        Dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                updaterDownloaProgress();
            }


        });
        Dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity();
            }
        });
        Dialog.setCancelable(false);
        Dialog.show();
    }

    @Override
    public void showAlertdialoInstall(final String filePath) {
        AlertDialog.Builder Dialog = new AlertDialog.Builder(this);
        Dialog.setTitle("更新的应用已下载,是否安装");
        Dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intnt = new Intent();
                intnt.setAction("updaterReceiver");
                //intnt.putExtra("updater1","222");
                sendBroadcast(intnt);
                startActivity();
            }


        });
        Dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity();
            }
        });
        Dialog.setCancelable(false);
        Dialog.show();
    }

    /**
     * 点击更新后下载更新,显示进度条
     */
    private void updaterDownloaProgress() {
        mPd = new ProgressDialog(UpdaterAndSplashActivity.this);
        mPd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);  //设置为水平样式的进度条
        mPd.show();
        mUpdater.downloadUpdate(mPd);                   //p层调用m层的设置进度条的方法
        mPd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                startActivity();
            }
        });
    }


    /**
     * 显示网络连接错误的提示,被p层调用
     */
    @Override
    public void showError() {

        toastUtils("请查看网络是否连接!");

    }

    @Override
    public void showIoError() {
        toastUtils("请查看网络,或本地文件是否足够!");
    }

    /**
     * toast的封装类
     * @param tag
     */
    private void toastUtils(String tag) {
        Toast.makeText(UpdaterAndSplashActivity.this, tag, Toast.LENGTH_SHORT)
             .show();
    }


    /**
     * 加载到主页的方法被p层调用
     */
    @Override
    public void startActivity() {
        startActivity(new Intent(UpdaterAndSplashActivity.this, MainActivity.class));
        finish();
    }



    /**
     * 返回版本信息到p层
     * @return
     */
    @Override
    public int getVerstionNum() {
        return PackagetUtils.getVersionCode(this);
    }

    /**
     *隐藏进度条
     * @return
     */


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void hideProgressbat() {
        mPd.dismiss();                         //隐藏进度条
    }

    /**
     * 返回this目录到p层,给m层调用
     * @return
     */
    @Override
    public Context getContext() {
        return this;
    }

    /**
     * 返回缓存目录到p层,给m层调用
     * @return
     */
    @Override
    public File getFiles() {
        return getCacheDir();
    }



}
