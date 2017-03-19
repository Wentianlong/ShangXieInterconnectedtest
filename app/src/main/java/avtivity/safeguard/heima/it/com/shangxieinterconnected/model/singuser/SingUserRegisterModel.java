package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser;

import android.text.TextUtils;
import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserRegisterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserRegisterListener;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.CountDownTimeUtils;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   SingUserRegisterModel
 *  @创建者:   文
 *  @创建时间:  2017/3/15 15:05
 *  @描述：    m层用户登录的逻辑
 */
public class SingUserRegisterModel implements ISingUserRegisterModel {
    private static final String TAG = "SingUserRegisterModel";
    String mVerificationCode =  null;
    /**
     * 注册账号
     * @param unm
     * @param password
     * @param verificationCode
     * @param listener
     */
    @Override
    public void register(String unm,
                         String password,
                         String verificationCode,
                         OnUserRegisterListener listener)
    {
        if (TextUtils.isEmpty(verificationCode)) {
            listener.InputVerificationCodeIsNull();

        } else {

            if (mVerificationCode.equals(verificationCode)) {
                listener.registerSuccess();
            } else {
                listener.registerFailed();
            }
        }
    }

    /**
     * 判断输入的号码和密码格式是否正确
     * @param num
     * @param password
     * @param listener
     * @return
     */
    @Override
    public boolean isVerificationCode(String num, String password, OnUserRegisterListener listener)
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
    public boolean getVerificationCode(TextView tvGetVerifiactionCode, OnUserRegisterListener listener) {
        boolean tag =false;
        mVerificationCode = "12345";
        if (TextUtils.isEmpty(mVerificationCode)){
            listener.getVerificationCodeFailed();   //获取验证码失败
        }else {
            CountDownTimeUtils countDownTimeUtils = new CountDownTimeUtils(30000,1000,tvGetVerifiactionCode);
            countDownTimeUtils.start();
            listener.getVerificationCodeSuccess();   //获取验证码成功
            tag = true;
        }
        return tag;
    }

}
