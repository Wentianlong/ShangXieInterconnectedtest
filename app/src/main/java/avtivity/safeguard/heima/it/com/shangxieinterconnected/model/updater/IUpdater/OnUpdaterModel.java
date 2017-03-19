package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater.IUpdater;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.updater
 *  @文件名:   OnUpdaterModel
 *  @创建者:   文
 *  @创建时间:  2017/3/9 11:59
 *  @描述：请求网络的回掉接口
 */
public interface OnUpdaterModel {
   static final String TAG = "OnUpdaterModel";

    void error();           //无网络时,处理网络的方法

    void updater(int version, String updater);  //获取版本信息,和更新资源的方法
    /**
     * 本地文件没有的错误
     */
    //void ioError();
    
}
