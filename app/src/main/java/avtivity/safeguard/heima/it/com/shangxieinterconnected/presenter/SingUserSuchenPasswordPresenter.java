package avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter;

import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.SingUserSuchenPasswordModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserSuchenPasswordModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserSuchenPasswordlistener;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserSuchenPasswordView;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter
 *  @文件名:   SingUserSuchenPasswordPresenter
 *  @创建者:   文
 *  @创建时间:  2017/3/16 14:24
 *  @描述：    p层的用户重置密码的逻辑
 */
public class SingUserSuchenPasswordPresenter
        implements OnUserSuchenPasswordlistener
{
    private static final String TAG = "SingUserSuchenPasswordPresenter";

    private ISingUserSuchenPasswordModel mISingUserSuchenPasswordModel;
    private ISingUserSuchenPasswordView  mISingUserSUchenPasswordView;
    private boolean                      mTag;

    public SingUserSuchenPasswordPresenter(ISingUserSuchenPasswordView ISingUserSUchenPasswordView) {
        mISingUserSUchenPasswordView = ISingUserSUchenPasswordView;
        mISingUserSuchenPasswordModel = new SingUserSuchenPasswordModel();
    }


    /**
     * 获取验证码
     * @param tvGetVerificationCode
     */

    public void getVerificationCode(TextView tvGetVerificationCode) {
        mISingUserSUchenPasswordView.showProgressbar();
        String  num = mISingUserSUchenPasswordView.getUserNum();
        String  psw = mISingUserSUchenPasswordView.getPassword();
        boolean tag = mISingUserSuchenPasswordModel.isVerificationCode(num, psw, this);
        if (tag) {
            mTag = mISingUserSuchenPasswordModel.getVerificationCode(tvGetVerificationCode,this);
        }
    }

    /**
     * 重置密码
     */
    public void suchenPassword() {
        String num  = mISingUserSUchenPasswordView.getUserNum();
        String psw  = mISingUserSUchenPasswordView.getPassword();
        String code = mISingUserSUchenPasswordView.getVerificationCode();
        boolean ISVerificationCode = mISingUserSuchenPasswordModel.isVerificationCode(num,
                                                                                      psw,
                                                                                      this);

        if (ISVerificationCode) {
            if (mTag) {
                mISingUserSUchenPasswordView.showProgressbar();
                mISingUserSuchenPasswordModel.suchenPassword(num, psw, code, this);
            } else {
                mISingUserSUchenPasswordView.showNoGetVerificationCode();
            }
        }

    }

    /**
     * 获取验证码成功
     * @return
     */
    @Override
    public void getVerificationCodeSuccess() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showGetVerificationCodeSuccess();
    }

    /**
     * 获取验证码失败
     */
    @Override
    public void getVerificationCodeFailed() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showGetVerificationCodeError();
    }

    /**
     * 用户名或密码为空
     */
    @Override
    public void isNumNullFailed() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showNumNullError();
    }

    @Override
    public void issNumBildenError() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showNumBildenError();
    }

    @Override
    public void isPasswordNullError() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showPasswordNullError();
    }

    @Override
    public void isPasswordBildenError() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showPasswordBildenError();
    }

    /**
     * 重置密码为空
     */
    @Override
    public void InputVerificationCodeIsNull() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showInputVerificationCodeNull();

    }


    /**
     * 重置密码失败,验证码错误
     */
    @Override
    public void suchenPaswordFailed() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.showSuchenPasswordCodeError();
    }

    /**
     * 重置密码成功
     */
    @Override
    public void suchenPasswordSuccess() {
        mISingUserSUchenPasswordView.hideProgressbar();
        mISingUserSUchenPasswordView.jumppToSingUserLogin();
    }


}
