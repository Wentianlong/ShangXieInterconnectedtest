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
import avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter.SingUserRegisterPresenter;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserRegisterView;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view.TitleView;

public class SingRegisterActivity
        extends AppCompatActivity
        implements View.OnClickListener, ISingUserRegisterView
{

    private EditText                  mEtNum;
    private EditText                  mEtPassword;
    private EditText                  mEtVerifiactionCode;
    private TextView                  mTvGetVerifiactionCode;
    private Button                    mBtRegister;
    private ProgressBar               mPb;
    private SingUserRegisterPresenter mSingUserRegisterPresenter;
    private TitleView                 mIvRevert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_register);
        initView();
        initEvent();
    }

    private void initEvent() {
        mBtRegister.setOnClickListener(this);
        mTvGetVerifiactionCode.setOnClickListener(this);
        mSingUserRegisterPresenter = new SingUserRegisterPresenter(this);
        mIvRevert.setOnRevertClicklietener(new TitleView.RevertClickListener() {
            @Override
            public void click() {
                finish();
            }
        });
    }

    private void initView() {
        mEtNum = (EditText) findViewById(R.id.act_sing_register_et_num);
        mEtPassword = (EditText) findViewById(R.id.act_sing_register_et_password);
        mEtVerifiactionCode = (EditText) findViewById(R.id.act_sing_register_et_verification_code);
        mTvGetVerifiactionCode = (TextView) findViewById(R.id.act_sing_register_tv_get_verification_code);
        mBtRegister = (Button) findViewById(R.id.act_sing_register_bt);
        mPb = (ProgressBar) findViewById(R.id.act_sing_Register_pb);
        mIvRevert = (TitleView) findViewById(R.id.act_sing_register_iv_revert);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_sing_register_tv_get_verification_code:
                mSingUserRegisterPresenter.getVericationCode(mTvGetVerifiactionCode);

                break;
            case R.id.act_sing_register_bt:
                mSingUserRegisterPresenter.register();
                break;
        }
    }

    @Override
    public String getUserNum() {

        return mEtNum.getText()
                     .toString()
                     .trim();
    }


    @Override
    public String getPassword() {
        return mEtPassword.getText()
                          .toString()
                          .trim();
    }

    @Override
    public String getVerificationCode() {
        return mEtVerifiactionCode.getText()
                                  .toString()
                                  .trim();
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
        toast("注册成功");
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
    public void showRegisterUrlError() {
        toast("注册失败,请检查网络是否连接");
    }

    @Override
    public void showRegisterCodeError() {
        toast("注册失败,请检查验证输入是否整正确");
    }

    @Override
    public void showInputVerificationCodeNull() {
        toast("验证码不能为空");
    }

    @Override
    public void showNoGetVerificationCode() {
        toast("还没有获验证码,请获取以后再注册!");
    }

    public void toast(String tag) {
        Toast.makeText(this, tag, Toast.LENGTH_SHORT)
             .show();
    }


}
