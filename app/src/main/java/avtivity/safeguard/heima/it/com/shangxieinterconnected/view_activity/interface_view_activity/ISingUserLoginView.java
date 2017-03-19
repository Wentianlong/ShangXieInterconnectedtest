package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.view
 *  @文件名:   ISingUserLoginView
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:43
 *  @描述：    用户登录的view层
 */

public interface ISingUserLoginView {
    /**
     * 获取用户名
     * @return
     */
    String getUserName();

    /**
     * 获取密码
     * @return
     */
    String getPassword();

    /**
     * 显示进度条
     */
    void showProgressbar();


    /**
     * 隐藏进度条
     */
    void hideProgressbar();

    /**
     * 跳转到我的页面
     */
    void jumppTo();



    /**
     * 显示网络错误,登录失败
     */
    void showLoginError();

    /**
     * 用户名为空
     */
    void showNumNullError();

    /**
     * 用户名格式错误
     */
    void showNumBildenError();

    /**
     * 密码为空
     */
    void showPasswordNullError();

    /**
     * 密码格式错误
     */
    void showPasswordBildenError();
}
