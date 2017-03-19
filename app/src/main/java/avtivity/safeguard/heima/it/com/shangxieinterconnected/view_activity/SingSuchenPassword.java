package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter.SingUserSuchenPasswordPresenter;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserSuchenPasswordView;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view.TitleView;

public class SingSuchenPassword
        extends AppCompatActivity
        implements View.OnClickListener,ISingUserSuchenPasswordView
{

    private Button                          mBtSuchenPassword;
    private TextView                        mTvGetVerificationCode;
    private EditText                        mEtUserVerificationCode;
    private EditText                        mEtUserPsw;
    private EditText                        mEtUserName;
    private TitleView                       mIvRevert;
    private ProgressBar                     mPb;
    private SingUserSuchenPasswordPresenter mSingUserSuchenPasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_suchen_password);
        initView();
        initEvent();
    }

    private void initEvent() {
        mTvGetVerificationCode.setOnClickListener(this);
        mBtSuchenPassword.setOnClickListener(this);
        mSingUserSuchenPasswordPresenter = new SingUserSuchenPasswordPresenter(this);
        mIvRevert.setOnRevertClicklietener(new TitleView.RevertClickListener() {
            @Override
            public void click() {
                finish();
            }
        });
    }

    private void initView() {
        mIvRevert = (TitleView) findViewById(R.id.act_sing_suchen_password_iv_revert);
        mEtUserName =  (EditText) findViewById(R.id.act_sing_suchen_password_et_user_num);
        mEtUserPsw = (EditText) findViewById(R.id.act_sing_suchen_password_et_user_psw);
        mEtUserVerificationCode = (EditText) findViewById(R.id.act_sing_suchen_password_et_user_verification_code);
        mTvGetVerificationCode = (TextView) findViewById(R.id.act_sing_suchen_password_tv_user_get_verification_code);
        mBtSuchenPassword = (Button) findViewById(R.id.act_sing_suchen_password_bt);
        mPb = (ProgressBar) findViewById(R.id.act_sing_suchen_password_pb);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_sing_suchen_password_tv_user_get_verification_code:
                mSingUserSuchenPasswordPresenter.getVerificationCode(mTvGetVerificationCode);
                break;
            case R.id.act_sing_suchen_password_bt:
                mSingUserSuchenPasswordPresenter.suchenPassword();
                break;
        }
    }
    @Override
    public String getUserNum() {
        return mEtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mEtUserPsw.getText().toString().trim();
    }

    @Override
    public String getVerificationCode() {
        return mEtUserVerificationCode.getText().toString().trim();
    }

    @Override
    public void showProgressbar() {
        mPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        mPb.setVisibility(View.GONE);
    }

    @Override
    public void jumppToSingUserLogin() {
        toast("重置密码成功");
        startActivity(new Intent(this, SingUserLoginActivity.class));
        finish();
    }

    @Override
    public void showNumNullError() {
        toast("手机号码不能为空");
    }

    @Override
    public void showNumBildenError() {
        toast("请输入正确的手机号");
    }

    @Override
    public void showPasswordNullError() {
        toast("密码不能为空");
    }

    @Override
    public void showPasswordBildenError() {
        toast("请输入6位,或6位以上的密码!");
    }


    @Override
    public void showGetVerificationCodeError() {
        toast("获取验证码失败,请检查网络是否连接");
    }

    @Override
    public void showGetVerificationCodeSuccess() {
        toast("获取验证码成功,请留意短信");
    }

    @Override
    public void showSuchenPasswordUrlError() {
          toast("重置密码失败,请检查是否连接网络");
    }

    @Override
    public void showSuchenPasswordCodeError() {
        toast("重置密码失败,请检查验证码是否正确");

    }

    @Override
    public void showInputVerificationCodeNull() {
        toast("验证码不能为空");
    }

    @Override
    public void showNoGetVerificationCode() {
        toast("还没有获验证码,请获取以后再重置密码!");
    }


    public void toast(String tag) {
        Toast.makeText(this, tag, Toast.LENGTH_SHORT)
             .show();
    }
}
