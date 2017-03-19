package avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;

import java.io.File;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater.IUpdaterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater.OnUpdaterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.UserUpdaterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.Constants;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.SharedPerfsUtils;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.IUpdapterView;


/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter
 *  @文件名:   UpldaterPresenter
 *  @创建者:   文
 *  @创建时间:  2017/3/9 17:44
 *  @描述：    p层更新的逻辑
 */
public class UpldaterPresenter
        implements OnUpdaterModel
{
    private static final String TAG = "UpldaterPresenter";

    private IUpdapterView mIUpladterView;
    private IUpdaterModel mIUpdaterModel;
    private String updaterUrl;
    private Handler mHandler = new Handler();

    /**
     * 初始化该构造时,进行查询网络
     * @param iUpdapterView
     */
    public UpldaterPresenter(IUpdapterView iUpdapterView) {
        mIUpladterView = iUpdapterView;
        mIUpdaterModel = new UserUpdaterModel();
        final String filePath = SharedPerfsUtils.getString(mIUpladterView.getContext(), Constants.DOWNLOAD_UPDATER_OK);

        if (TextUtils.isEmpty(filePath)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);
                    mIUpdaterModel.updater(UpldaterPresenter.this);     //查询网络,获取到服务器的版本号,并获取最新应用的下载地址
                }
            }).start();
        }else{
                    mIUpladterView.showAlertdialoInstall(filePath);

        }


    }


    /**
     * m层里面的方法回掉错误信息时,在v层里面显示提示信息,并跳转到主页
     */
    @Override
    public void error() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mIUpladterView.showError();
                mIUpladterView.startActivity();
            }
        });

    }
   /* *//**
     * 如果出现异常时,进行提示,并跳转到主页
     *//*
    @Override
    public void ioError() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mIUpladterView.showIoError();
                mIUpladterView.startActivity();
            }
        });
    }
*/
    /**
     * m层里的回掉方法,获取最新的版本,和本地的版本并进行判断是否进行弹出对话框的逻辑,
     * @param version
     * @param updater
     */
    @Override
    public void updater(final int version, final String updater) { //查询网络,获取到服务器的版本号,并获取最新应用的下载地址
        int oldVersion = mIUpladterView.getVerstionNum();
        if (oldVersion < version) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    updaterUrl = updater;
                   mIUpladterView.showAlertDialoUpdater();

                }
            });
        } else {
            mIUpladterView.startActivity();
        }
    }

    /**
     * 下载更新软件的逻辑
     * @param pd
     */
    public void downloadUpdate(final ProgressDialog pd) {
    new Thread (new Runnable() {
        @Override
        public void run() {
              mIUpdaterModel.updateDownload(updaterUrl, pd,
                                            mIUpladterView.getContext(),
                                            mIUpladterView.getFiles(),
                                          UpldaterPresenter.this);
            mIUpladterView.hideProgressbat();
        }
    }).start();
    }



    /**
     * 获取下载软存放的路径
     * @return
     */
    public File getFile() {
        return mIUpdaterModel.getFiles();
    }
}
