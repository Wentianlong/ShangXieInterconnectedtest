package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   OnUserLoginListener
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:11
 *  @描述：    用户登录回掉的接口
 */
public interface OnUserLoginListener {

    /**
     * 登录成功回掉的接口
     */
    void loginSuccess();

    /**
     * 密码或账户不正确
     */
    void loginFailed();

    /**
     * 手机号码为空
     */
    void isNumNullFailed();

    /**
     * 手机号码格式错误
     */
    void issNumBildenError();

    /**
     * 密码为空
     */
    void isPasswordNullError();

    /**
     * 密码格式错误
     */
    void isPasswordBildenError();
}
