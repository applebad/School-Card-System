public class User{
    /**
     * 账号
     */
    public String mobile;
    /**
     * 用户名
     */
    public String userName;
    /**
     * 密码
     */
    public String password;
    /**
     * 角色：0 卡用户 1 业务员 2 管理员
     */
    public int role;
    public String cno;//卡号
    public User(String mobile,String password,String userName,int role,String cno){
        this.mobile = mobile;
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.cno = cno;
    }
    
    public int getmobileToInt(){
        return Integer.parseInt(mobile);
    }
    public String getmobile(){
        return mobile;
    }
    public void changePwd(String newPwd){
        this.password = newPwd;
    }
}