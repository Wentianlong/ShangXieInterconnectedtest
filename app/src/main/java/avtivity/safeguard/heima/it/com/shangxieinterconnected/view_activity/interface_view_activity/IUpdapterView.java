package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.view
 *  @文件名:   IUpdapterView
 *  @创建者:   文
 *  @创建时间:  2017/3/10 9:09
 *  @描述：    更新软件的view要显示的样式,和内容
 */

import android.content.Context;

import java.io.File;

public interface IUpdapterView {

    /**
     * 显示是否更新的对话框
     * @return
     */
    void showAlertDialoUpdater();

    /**
     * 显示是否安装的对话框
     */

    void showAlertdialoInstall(String filePath);

    /**
     * 显示网络连接错误信息
     * @return
     */
    void showError();

    /**
     * 显示下载时出现的错误
     */
    void showIoError();

    /**
     * 跳转到主页
     * @return
     */
    void startActivity();

    /**
     * 获取本地的版本信息
     * @return
     */
    int getVerstionNum();


    /**
     * 隐藏滚动条
     */
    void hideProgressbat();

    /**
     * 返回this
     * @return
     */
    Context getContext();

    /**
     * 返回file文件夹的目录
     * @return
     */
    File getFiles();



}
