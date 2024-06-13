import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    public static ArrayList<User> users = null;
    public static ArrayList<Card> cards = null;
    public static ArrayList<Transaction> transactions = null;
    /**
     * 当前操作用户索引 | default = -1
     */
    public static int curUserIndex = -1;
    /**
     * 标记业务操作是否更改数据 false:未更改 true:更改 | default = false
     */
    public boolean change = false;
    /**
     * 是否删除卡(外加交易记录)
     */
    public boolean delete = false;
<<<<<<< HEAD
    private Scanner sc = new Scanner(System.in);
=======
>>>>>>> 96b7fc89c380f88e17d34bcd494a2fd3dc947cbb
    protected Scanner sc = new Scanner(System.in);
    /**
     * 数据集
     */
    protected UserDatas userDatas = new UserDatas();
    protected CardDatas cardDatas = new CardDatas();
    protected TransactionDatas transDatas = new TransactionDatas();
    /**
     * 用于格式化金额
     */
    public DecimalFormat df = new DecimalFormat("#.00");
    /**
     * 用于格式化时间
     */
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void getDatas(){
        users = UserDatas.getUsers();
        cards = CardDatas.getCards();
        transactions = TransactionDatas.getTransactions();
    }
    public int getCurUserIndex(){
        return curUserIndex;
    }
    /**
     * 用户登录
     * @return 是否成功登录
     */
    public boolean login(){
        System.out.println("mobile:");
        String mobile = sc.next();
        System.out.println("password:");
        String password = sc.next();
        int index = userDatas.findUser(mobile);
        if(index<0){
            System.out.print("用户不存在！");
            return false;
        }
        User user = users.get(index);
        boolean pass = user.password.equals(password);
        if(!pass){
            System.out.println("用户密码错误!");
            return false;
        }
        return true;
    }
    /**
     * 改密码
     */
    public boolean modifyPassword(){
        User user = users.get(curUserIndex);
        System.out.println("输入原密码:");
        String originalPwd = sc.next();
        if(!originalPwd.equals(user.password)){
            System.out.println("原密码不正确!");
            return false;
        }
        String newPwd = null;
        String newPwd2 = null;
        while(true){
            System.out.println("输入新密码:");
            newPwd = sc.next();
            System.out.println("再次输入新密码:");
            newPwd2 = sc.next();
            if(!newPwd.equals(newPwd2)){
                System.out.println("两次密码输入不一致,修改失败");
                System.out.println("是否重新输入?(y/n)");
                String ans = sc.next();
                if(ans.equals('n')){
                if(ans.equals("n")){
                    return false;
                }
                else if(ans.equals('y')){}
                else if(ans.equals("y")){}
                else{
                    System.out.println("非法的输入!");
                    return false;
                }
            }else{
                break;
            }
        }
        user.password = newPwd;
        users.set(curUserIndex,user);
        System.out.println("密码修改成功!");
        change = true;//数据更改了
        return true;
    }
    /***
     * 用户注销
     */
    public void logout(){
        saveDatas();//保存数据
        //清空全局变量/对象数据
        UserDatas.clear();
        CardDatas.clear();
        TransactionDatas.clear();
        curUserIndex = -1;
    }
    /***
     * 用户退出
     */
    public void exit(){
        saveDatas();
        System.exit(0);
    }
    /***
     * 保存数据
     * 身份不一样操作不同 逻辑在子类中实现
     */
    public void saveDatas(){}
}
