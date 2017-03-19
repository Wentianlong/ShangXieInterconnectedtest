package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.utils
 *  @文件名:   CountDownTimeUtils
 *  @创建者:   文
 *  @创建时间:  2017/3/17 10:47
 *  @描述：    覆写了倒计时的类
 */
public class CountDownTimeUtils extends CountDownTimer {
    private static final String TAG = "CountDownTimeUtils";
    private TextView mTextView;

    public CountDownTimeUtils(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);  //设置为不可点击
        mTextView.setText(millisUntilFinished /  1000 +" 秒后可重新发送");  //设置倒计时时间
      //  mTextView.setBackgroundColor(Color.BLUE);               //设置按钮为蓝色,这时是不能点击的
        mTextView.setBackgroundResource(R.drawable.et_user_text_color);     //设置背景颜色
        mTextView.setTextColor(Color.WHITE);
        SpannableString spannableString= new SpannableString(mTextView.getText().toString());   //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);

        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);    //将倒计时的时间设置为红色
        mTextView.setText(spannableString);
    }

    @Override
    public void onFinish() {
        mTextView.setText("重新获取验证码");
        mTextView.setClickable(true);   //重新获取点击事件
        mTextView.setTextColor(Color.BLUE);
      //  mTextView.setBackgroundColor(Color.WHITE);  //设置背景颜色为白色
        mTextView.setBackgroundResource(R.drawable.et_user_text);//设置背景颜色
    }
}
