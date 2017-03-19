package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.view
 *  @文件名:   ISingUserRegisterView
 *  @创建者:   文
 *  @创建时间:  2017/3/14 21:18
 *  @描述：    用户注册的view
 */

public interface ISingUserRegisterView {
    /**
     * 获取用手机号
     * @return
     */
    String getUserNum();

    /**
     * 获取密码
     * @return
     */
    String getPassword();

    /**
     * 获取验证码
     * @return
     */
    String getVerificationCode();


    /**
     * 显示进度条
     */
    void showProgressbar();


    /**
     * 隐藏进度条
     */
    void hideProgressbar();

    /**
     * 注册成功时,跳转到主页
     */
    void jumppToSingUserLogin();

    /**
     * 显示手机号为空
     */
    void showNumNullError();

    /**
     *  显示手机格式错误
     */
    void showNumBildenError();

    /**
     * 显示密码,为空
     */
    void showPasswordNullError();

    /**
     * 显示密码格式错误
     */
    void showPasswordBildenError();

    /**
     * 显示获取验证码错误
     */
    void showGetVerificationCodeError();


    /**
     * 显示验证码获取成功
     */
    void showGetVerificationCodeSuccess();

    /**
     * 注册错误,网络是否连接
     */
    void showRegisterUrlError();
    /**
     * 注册错误,检查验证码输入是否正确
     */
    void showRegisterCodeError();

    /**
     * 显示输入的验证码为空
     */
    void showInputVerificationCodeNull();

    /**
     * 还没有获取验证码
     */
    void showNoGetVerificationCode();
}
