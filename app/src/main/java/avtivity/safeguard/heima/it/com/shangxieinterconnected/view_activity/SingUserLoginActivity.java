package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.presenter.SingUserLoginPresenter;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.interface_view_activity.ISingUserLoginView;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view.TitleView;

public class SingUserLoginActivity
        extends AppCompatActivity
        implements View.OnClickListener,ISingUserLoginView
{

    private TitleView  mIvRevert;
    private EditText  mEtUserName;
    private EditText  mEtUserPassword;
    private TextView  mTvLogin;
    private TextView  mTvRegister;
    private TextView  mTvSuhenPassword;
    private ProgressBar mPb;
    private SingUserLoginPresenter mLoginPresenter;

    /**
     * 登录activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_association);
        initView();
        initEvent();
    }

    private void initEvent() {
        mIvRevert.setOnRevertClicklietener(new TitleView.RevertClickListener() {
            @Override
            public void click() {
                startActivity(new Intent(SingUserLoginActivity.this, MainActivity.class));
                finish();
            }
        });
        mTvLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvSuhenPassword.setOnClickListener(this);
        mLoginPresenter = new SingUserLoginPresenter(this);
    }

    private void initView() {
        mIvRevert = (TitleView) findViewById(R.id.act_sing_association_ll);
        mEtUserName = (EditText) findViewById(R.id.act_sing_association_et_user_name);
        mEtUserPassword = (EditText) findViewById(R.id.act_sing_association_et_uset_password);
        mTvLogin = (TextView) findViewById(R.id.act_sing_association_tv_login);
        mTvRegister = (TextView) findViewById(R.id.act_sing_association_tv_register);
        mTvSuhenPassword = (TextView) findViewById(R.id.act_sing_association_tv_suchen_password);
        mPb = (ProgressBar) findViewById(R.id.act_sing_association_pb);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_sing_association_tv_login:
                mLoginPresenter.login();
                break;
            case R.id.act_sing_association_tv_register:
                startActivity(new Intent(this,SingRegisterActivity.class));
                break;
            case R.id.act_sing_association_tv_suchen_password:
                startActivity(new Intent(this,SingSuchenPassword.class));
                break;
        }
    }


    @Override
    public String getUserName() {

        return mEtUserName.getText().toString().trim();
    }

    @Override
    public String getPassword() {

        return mEtUserPassword.getText().toString().trim();
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
    public void jumppTo() {
        toast("登录成功");
        startActivity(new Intent(this, AllAssociationActivity.class));
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
        toast("请输入6位,或6位以上的密码");
    }

    @Override
    public void showLoginError() {
        toast("登录失败,请检查户名和密码是否正确");
    }



    public void toast(String tag){
        Toast.makeText(this,tag,Toast.LENGTH_SHORT).show();
    }
}
