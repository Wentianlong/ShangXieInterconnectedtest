package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   OnUserRegisterListener
 *  @创建者:   文
 *  @创建时间:  2017/3/15 14:58
 *  @描述：    TODO
 */
public interface OnUserRegisterListener {

    /**
     * 注册成功的回掉方法
     */
    void registerSuccess();


    /**
     * 注册失败的回掉
     */
    void registerFailed();


    /**
     * 获取验证码成功的回掉方法
     */
    void getVerificationCodeSuccess();


    /**
     * 获取验证码失败的回掉方法
     */
    void getVerificationCodeFailed();

    /**
     * 手机号不能为空的方法
     */

    void isNumNullFailed();

    /**
     * 手机号码格式错误
     */
    void issNumBildenError();

    /**
     * 显示密码,为空
     */
    void isPasswordNullError();

    /**
     * 显示密码格式错误
     */
    void isPasswordBildenError();


    /**
     * 输入的验证码为空的回掉
     */
    void InputVerificationCodeIsNull();

}
