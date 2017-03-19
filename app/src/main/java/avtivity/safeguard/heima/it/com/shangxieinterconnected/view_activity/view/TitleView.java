package avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.R;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.view_activity.view
 *  @文件名:   TitleView
 *  @创建者:   文
 *  @创建时间:  2017/3/17 16:14
 *  @描述：    组合控件标题栏
 */
public class TitleView extends LinearLayout{
    private static final String TAG = "TitleView";
    private RevertClickListener mListener;

    public TitleView(Context context) {
        this(context,null);
    }


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_titel,this); // 加载布局
        TypedArray  typedArray = context.obtainStyledAttributes(attrs,R.styleable.TitleView);   //获取布局中的信息
        String title = typedArray.getString(R.styleable.TitleView_sivText);        //读取值

        TextView        tvTitle  = (TextView) findViewById(R.id.view_title_tv);
        final ImageView ivRevert = (ImageView) findViewById(R.id.view_title_iv_revert);
        tvTitle.setText(title);
        typedArray.recycle();           //释放资源

        ivRevert.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.click();
            }
        });

        }


    public interface RevertClickListener{
        void click();
    }
    public void setOnRevertClicklietener(RevertClickListener listener){
        this.mListener = listener;
    }


}
