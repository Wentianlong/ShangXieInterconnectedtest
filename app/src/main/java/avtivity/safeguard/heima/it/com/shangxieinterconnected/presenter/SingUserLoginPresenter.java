package avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter;

import android.os.Handler;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.SingUserLoginModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserLoginModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserLoginListener;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.Constants;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.ShangXieInterconnecredApplication;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils.SharedPerfsUtils;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserLoginView;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter
 *  @文件名:   SingUserLoginPresenter
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:40
 *  @描述：    p层用户登录的逻辑
 */
public class SingUserLoginPresenter
        implements OnUserLoginListener
{
    private static final String TAG = "SingUserLoginPresenter";
    private ISingUserLoginView  mISingUserLoginView;
    private ISingUserLoginModel mISingUserLoginModel;
    private Handler mHandler = new Handler();

    public SingUserLoginPresenter(ISingUserLoginView ISingUserLoginView) {
        mISingUserLoginView = ISingUserLoginView;
        mISingUserLoginModel = new SingUserLoginModel();
    }

    public void login() {
        String name = mISingUserLoginView.getUserName();
        String psw  = mISingUserLoginView.getPassword();
        mISingUserLoginView.showProgressbar();
        boolean tag = mISingUserLoginModel.loginBilden(name, psw, this);
        if (tag) {
            mISingUserLoginModel.login(name, psw, this);
        }
    }

    /**
     * 成功回掉的方法
     */
    @Override
    public void loginSuccess() {
        SharedPerfsUtils.putBoolean(ShangXieInterconnecredApplication.getContextObject(),
                                    Constants.LOGIN,
                                    true);
        mISingUserLoginView.jumppTo();
        mISingUserLoginView.hideProgressbar();


    }

    /**
     * 失败回掉的方法
     */
    @Override
    public void loginFailed() {
        mISingUserLoginView.hideProgressbar();
        mISingUserLoginView.showLoginError();

    }

    @Override
    public void isNumNullFailed() {
        mISingUserLoginView.hideProgressbar();
        mISingUserLoginView.showNumNullError();
    }

    @Override
    public void issNumBildenError() {
        mISingUserLoginView.hideProgressbar();
        mISingUserLoginView.showNumBildenError();
    }

    @Override
    public void isPasswordNullError() {
        mISingUserLoginView.hideProgressbar();
        mISingUserLoginView.showPasswordNullError();
    }

    @Override
    public void isPasswordBildenError() {
        mISingUserLoginView.hideProgressbar();
        mISingUserLoginView.showPasswordBildenError();
    }


}
