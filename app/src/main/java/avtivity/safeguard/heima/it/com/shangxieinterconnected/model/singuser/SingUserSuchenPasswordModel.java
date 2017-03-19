package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser;

import android.text.TextUtils;
import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserSuchenPasswordModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserSuchenPasswordlistener;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.CountDownTimeUtils;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   SingUserSuchenPasswordModel
 *  @创建者:   文
 *  @创建时间:  2017/3/16 13:40
 *  @描述：    m层重置密码的逻辑
 */
public class SingUserSuchenPasswordModel implements ISingUserSuchenPasswordModel {
    private static final String TAG = "SingUserSuchenPasswordModel";

    String mVerificationCode =  null;
    /**
     * 重置密码的方法
     * @param num
     * @param password
     * @param verificatonCode
     * @param listener
     */
    @Override
    public void suchenPassword(String num,
                               String password,
                               String verificatonCode,
                               OnUserSuchenPasswordlistener listener)
    {
            if (TextUtils.isEmpty(verificatonCode)) {
                listener.InputVerificationCodeIsNull();

            } else {

                if (mVerificationCode.equals(verificatonCode)) {
                    listener.suchenPasswordSuccess();
                } else {
                    listener.suchenPaswordFailed();
                }
            }

    }

    /**
     * 判断输入的号码和密码格式是否正确
     * @param num
     * @param password
     * @param listener
     */
    @Override
    public boolean isVerificationCode(String num, String password, OnUserSuchenPasswordlistener listener)
    {
        boolean temp = false;

        if (TextUtils.isEmpty(num)) {
            listener.isNumNullFailed();  //没有输入如号码和密码

        } else {
            String telRegx  = "[1][358]\\d{9}";
            if ( !num.matches(telRegx)){
            listener.issNumBildenError();
            }else{
                boolean tag = TextUtils.isEmpty(password);
                if (tag){
                    listener.isPasswordNullError();
                }else if(password.length() < 6){
                    listener.isPasswordBildenError();
                }else{
                     temp = true;

                }
            }



        }
        return temp;
    }

    /**
     * 获取验证码
     */
    @Override
    public boolean getVerificationCode(TextView tv, OnUserSuchenPasswordlistener listener) {
        boolean tag =false;
        mVerificationCode = "12345";
        if (TextUtils.isEmpty(mVerificationCode)){
            listener.getVerificationCodeFailed();   //获取验证码失败
        }else {
            CountDownTimeUtils countDownTimeUtils = new CountDownTimeUtils(30000, 1000, tv);
            countDownTimeUtils.start();
            listener.getVerificationCodeSuccess();   //获取验证码成功
            tag = true;
        }
        return tag;
    }

}
