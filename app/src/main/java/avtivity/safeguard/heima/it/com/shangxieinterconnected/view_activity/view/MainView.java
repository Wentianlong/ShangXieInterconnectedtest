package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.view
 *  @文件名:   SingUserLoginView
 *  @创建者:   文
 *  @创建时间:  2017/3/15 8:23
 *  @描述：
 */
public class MainView
        extends View
{
    private static final String TAG = "SingUserLoginView";
    private Paint mPaint;
    private final int mWidth;
    private final int mHeight;


    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);   //设置为没有锯齿

        WindowManager wm     = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();


    }


    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawCircle(mWidth/2,mHeight/2,mHeight/2,mPaint);
    }
}
