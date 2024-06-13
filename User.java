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
    /**
     * 卡号
     */
    public String cno;

    public User(String mobile,String password,String userName,int role,String cno){
        this.mobile = mobile;
        this.password = password;
        this.userName = userName;
        this.role = role;
        this.cno = cno;
    }
    /**
     * 获取账号 类型int
     * @return int
     */
    public int getmobileToInt(){
        return Integer.parseInt(mobile);
    }
    /**
     * 获取账号
     * @return String
     */
    public String getmobile(){
        return mobile;
    }
    /**
     * 改密码
     * @param newPwd 新密码
     */
    public void changePwd(String newPwd){
        this.password = newPwd;
    }
}