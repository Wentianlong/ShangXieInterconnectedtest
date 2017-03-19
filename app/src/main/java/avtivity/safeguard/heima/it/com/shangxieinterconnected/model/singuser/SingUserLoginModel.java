package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser;

import android.text.TextUtils;

import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.ISingUserLoginModel;
import avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing.OnUserLoginListener;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   SingUserLoginModel
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:41
 *  @描述：    m层用户登录
 */
public class SingUserLoginModel
        implements ISingUserLoginModel
{
    private static final String TAG = "SingUserLoginModel";

    @Override
    public boolean loginBilden(final String userName,
                               final String password,
                               final OnUserLoginListener listener)

    {
        boolean temp = false;
        if (TextUtils.isEmpty(userName)) {
            listener.isNumNullFailed();  //没有输入如号码和密码

        } else {
            String telRegx = "[1][358]\\d{9}";
            if (!userName.matches(telRegx)) {
                listener.issNumBildenError();
            } else {
                boolean tag = TextUtils.isEmpty(password);
                if (tag) {
                    listener.isPasswordNullError();
                } else if (password.length() < 6) {
                    listener.isPasswordBildenError();
                } else {
                    temp = true;
                }
            }

        }
        return temp;

    }

    @Override
    public void login(String userName, String password, OnUserLoginListener listener) {
        if ("13682327922".equals(userName) && "123456".equals(password)) {
            listener.loginSuccess();
        } else {
            listener.loginFailed();
        }
    }
}
