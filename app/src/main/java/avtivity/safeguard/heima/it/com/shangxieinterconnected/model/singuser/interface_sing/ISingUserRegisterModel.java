package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   ISingUserRegisterModel
 *  @创建者:   文
 *  @创建时间:  2017/3/15 15:04
 *  @描述：    m层注册的接口
 */

import android.widget.TextView;

public interface ISingUserRegisterModel {

    /**
     *注册账号的逻辑
     * @param unm
     * @param password
     * @param verificationCode
     * @param listener
     */
    public void register(String unm,
                         String password,
                         String verificationCode,
                         OnUserRegisterListener listener);





    /**
     * 获取验证码的逻辑
     * @param num
     * @param password
     * @param listener
     */
    boolean isVerificationCode(String num, String password, OnUserRegisterListener listener);

    /**
     * 获取验证码
     * @param listener
     * @return
     */

    boolean getVerificationCode(TextView tvGetVerifiactionCode,
                                OnUserRegisterListener listener);
}
