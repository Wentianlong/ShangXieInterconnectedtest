package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   ISingUserSuchenPasswordModel
 *  @创建者:   文
 *  @创建时间:  2017/3/16 13:37
 *  @描述：    m层重置密码的接口
 */

import android.widget.TextView;

public interface ISingUserSuchenPasswordModel {


    /**
     * 重置账号的逻辑
     * @param num
     * @param password
     * @param verificatonCode
     * @param listener
     */
    void suchenPassword(String num,
                        String password,
                        String verificatonCode,
                        OnUserSuchenPasswordlistener listener);
    /**
     * 获取验证码的逻辑
     * @param num
     * @param password
     * @param listener
     */
    boolean isVerificationCode(String num, String password, OnUserSuchenPasswordlistener listener);


    boolean getVerificationCode(TextView tvGetVerificationCode,
                                OnUserSuchenPasswordlistener listener);
}
