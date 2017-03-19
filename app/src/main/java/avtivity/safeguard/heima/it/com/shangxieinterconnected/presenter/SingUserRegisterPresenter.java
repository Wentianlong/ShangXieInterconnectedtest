package avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter;

import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.SingUserRegisterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserRegisterModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserRegisterListener;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserRegisterView;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter
 *  @文件名:   SingUserRegisterPresenter
 *  @创建者:   文
 *  @创建时间:  2017/3/15 15:12
 *  @描述：    注册的p
 */
public class SingUserRegisterPresenter
        implements OnUserRegisterListener
{
    private static final String TAG = "SingUserRegisterPresenter";
    private ISingUserRegisterView  mISingUserRegisterView;
    private ISingUserRegisterModel mISingUserRegisterModel;
    boolean mTag;

    public SingUserRegisterPresenter(ISingUserRegisterView ISingUserRegisterView) {
        mISingUserRegisterView = ISingUserRegisterView;
        mISingUserRegisterModel = new SingUserRegisterModel();
    }


    /**
     * 获取验证码
     * @param tvGetVerifiactionCode
     */
    public void getVericationCode(TextView tvGetVerifiactionCode) {
        mISingUserRegisterView.showProgressbar();
        String num = mISingUserRegisterView.getUserNum();
        String psw = mISingUserRegisterView.getPassword();
        boolean tag = mISingUserRegisterModel.isVerificationCode(num,
                                                                 psw,
                                                                 this);      //判断号码和密码格式是否正确
        if (tag) {
            mTag = mISingUserRegisterModel.getVerificationCode(tvGetVerifiactionCode,this);   //验证码是否获取成功
        }
    }

    /**
     * 注册
     */
    public void register() {
        mISingUserRegisterView.showProgressbar();

        String  num                = mISingUserRegisterView.getUserNum();
        String  psw                = mISingUserRegisterView.getPassword();
        String  code               = mISingUserRegisterView.getVerificationCode();
        boolean tag = mISingUserRegisterModel.isVerificationCode(num,
                                                                                psw,
                                                                                this);    //判断号码和密码格式是否正确

        if (tag) {
            if (mTag) {         //如果验证获取成功
                mISingUserRegisterModel.register(num, psw, code, this);
                mISingUserRegisterView.hideProgressbar();
            } else {
                mISingUserRegisterView.showNoGetVerificationCode();         //验证码获取失败,
                mISingUserRegisterView.hideProgressbar();
            }
        }

    }


    /**
     * 注册成功的回掉方法
     */
    @Override
    public void registerSuccess() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.jumppToSingUserLogin();
    }

    /**
     * 注册失败的回掉方法
     */
    @Override
    public void registerFailed() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showRegisterCodeError();
    }

    @Override
    public void getVerificationCodeSuccess() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showGetVerificationCodeSuccess();
    }

    @Override
    public void getVerificationCodeFailed() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showGetVerificationCodeError();
    }

    @Override
    public void isNumNullFailed() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showNumNullError();
    }

    @Override
    public void issNumBildenError() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showNumBildenError();
    }

    @Override
    public void isPasswordNullError() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showPasswordNullError();
    }

    @Override
    public void isPasswordBildenError() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showPasswordBildenError();
    }

    @Override
    public void InputVerificationCodeIsNull() {
        mISingUserRegisterView.hideProgressbar();
        mISingUserRegisterView.showInputVerificationCodeNull();
    }
}
