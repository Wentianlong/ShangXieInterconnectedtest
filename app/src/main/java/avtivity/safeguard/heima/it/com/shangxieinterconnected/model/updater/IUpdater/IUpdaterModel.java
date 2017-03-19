package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater
 *  @文件名:   IUpdaterModel
 *  @创建者:   文
 *  @创建时间:  2017/3/9 11:56
 *  @描述：    更新的业务逻辑
 */

import android.app.ProgressDialog;
import android.content.Context;

import java.io.File;

public interface IUpdaterModel {
    /**
     * 获取版本和更新应用的app的方法
     * @param listener
     */
    void updater(OnUpdaterModel listener);

    /**
     * 更新下载数据
     * @param updaterUrl
     * @param pd
     * @param upldaterPresenter
     */
    void updateDownload(String updaterUrl,
                        ProgressDialog pd,
                        Context context,
                        File files,
                        OnUpdaterModel upldaterPresenter);


    /**
     * 获取下更新的file文件路径
     * @return
     */
    File getFiles();
}
