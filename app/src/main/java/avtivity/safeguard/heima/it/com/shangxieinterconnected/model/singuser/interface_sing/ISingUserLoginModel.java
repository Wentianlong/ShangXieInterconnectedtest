package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser.interface_sing;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   ISingUserLoginModel
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:45
 *  @描述：    m层登录的接口
 */

public interface ISingUserLoginModel {
    boolean loginBilden(String userName, String password, OnUserLoginListener listener);

    public void login(String userName, String password, OnUserLoginListener listener);
}
