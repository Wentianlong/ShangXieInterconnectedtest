package avtivity.safeguard.heima.it.com.shangxieinterconnected.model.bean;

/*
 *  @项目名：  ShangXieInterconnected 
 *  @包名：    avtivity.safeguard.heima.it.com.shangxieinterconnected.model.singuser
 *  @文件名:   User
 *  @创建者:   文
 *  @创建时间:  2017/3/14 15:25
 *  @描述：    用户类,用单例设计模式写的
 */
public class User {
    private static final String TAG = "User";
    String userName;
    String  password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;

    }

    public User(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    private User(){}    //私有构造方法
    private static final User mUser = new User(); //对外提供引用
    public static User getUser(){
        return mUser;
    }

}
