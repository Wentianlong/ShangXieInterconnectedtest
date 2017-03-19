package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;

/**
 * 主页面的方法
 */
public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener
{

    private static final int REQ_CODE_INSTALL_APP = 1;
    private Button mBtSingle;
    private Button mBtAll;
    private boolean tag      = true;
    private long    exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置全屏
        //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
        initEvent();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_single_association_button:
                startActivity(new Intent(this, SingUserLoginActivity.class));
                break;
            case R.id.act_all_association_button:
                startActivity(new Intent(this, AllAssociationActivity.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(tag){
            Updater();
        }
    }



    private void initEvent() {
        mBtSingle.setOnClickListener(this);
        mBtAll.setOnClickListener(this);
    }

    private void initView() {
        mBtSingle = (Button) findViewById(R.id.act_single_association_button);
        mBtAll = (Button) findViewById(R.id.act_all_association_button);
    }

    /**
     * 监听下载完成的广播的接口回掉
     */
    private void Updater() {
        File   file = new File(getFile(), "商协互联.apk");
        String key  = getIntent().getStringExtra("name");
     //   LogUtils.e("获取到了值是:"+key);
        if (!TextUtils.isEmpty(key)) {
                tag = !tag;
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setDataAndType(Uri.parse("file:" + file.getAbsolutePath()),
                                      "application/vnd.android.package-archive");
                startActivityForResult(intent, REQ_CODE_INSTALL_APP);
        }
    }

    /**
     * 判断目录是否存在返回一个路径
     * @return
     */
    public File getFile() {
        File file = Environment.getExternalStorageDirectory();
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            file = getCacheDir();
        }

        return file;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT)
                     .show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_INSTALL_APP:
                if (requestCode == Activity.RESULT_CANCELED ){
                    finish();
                }else{
                    finish();
                }
                 break;
            default:
                 break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


