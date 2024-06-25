import java.util.ArrayList;
import java.util.Scanner;

/***
 * 实现系统管理员的功能
 */
public class ManagerService extends UserService{
    //删除用户需要同时删除用户的卡号和交易记录,需要单独标记
    public Scanner sc = new Scanner(System.in);
    private ArrayList<String> removeCno = new ArrayList<String>();
    /***
     * 新增用户
     */
    public void addUser(){
        String mobile;
        int index = -1;
        while(true){
            System.out.println("手机号(11位):");
            mobile = sc.next();
            index = userDatas.findUser(mobile);
            if(index < 0){
                break;
            }
            System.out.println("用户["+mobile+"]已存在,重新输入");
        }
        System.out.println("登录密码:");
        String password = sc.next();
        System.out.println("用户名:");
        String userName = sc.next();
        System.out.println("角色(0 卡用户, 1 卡业务员, 2 系统管理员):");
        int role = sc.nextInt();
        sc.nextLine();//消除缓存区的回车
        User user = new User(mobile, password, userName, role, "");
        userDatas.addUser(user);
        change = true;//数据更改
    }
    /***
     * 删除用户
     */
    public void removeUser(){
        //系统中只有admin用户,不进行删除
        if(users.size()==1){
            return;
        }
        String mobile;
        int index = -1;
        while(true){
            System.out.println("手机号:");
            mobile = sc.next();sc.nextLine();
            // if(mobile.length()!=11) {
            //     System.out.println("手机号位数错误");
            //     continue;
            // }
            index = userDatas.findUser(mobile);
            if(index >= 0){
                break;
            }
            System.out.println("用户["+mobile+"]不存在,是否重新输入(y/n):");
            sc.nextLine();String ans = sc.nextLine();
            if(ans.equals("y")){
                continue;
            }else{
                return;
            }
        }
        User user = users.get(index);
        if(user.mobile.equals(users.get(curUserIndex).mobile)){
            System.out.println("不能删除当前用户!");
            return;
        }
        if(users.get(index).role==2){
            System.out.println("不能删除[admin]系统管理员用户");
            return;
        }
        userDatas.removeUser(user);
        change=true;//数据更改标识
        //删除用户时同时删除对应卡号和记录
        if(!user.cno.isEmpty()){
            cardDatas.removeCard(user.cno);
            removeCno.add(user.cno);
            delete = true;//删除用户的卡号和交易记录
        }
    }
    /***
     * 显示所有用户
     */
    public void showUsers(){
        System.out.printf("\n%-10s\t\t%-10s\t\t%-10s\t\t%-10s\t\t%-10s\n","手机号","密码","用户名","身份","卡号");
        for(int i = 0; i < users.size(); i++){
            User user = users.get(i);
            String roleName;
            switch (user.role) {
                case 0:
                    roleName = "卡用户";
                    break;
                case 1:
                    roleName = "卡业务员";
                    break;
                case 2:
                    roleName = "系统管理员";
                    break;
                default:
                    roleName = "卡用户";
                    break;
            }
            System.out.printf("%-10s\t\t%-10s\t\t%-10s\t\t%-10s\t\t%-10s\n",user.mobile,user.password,user.userName,roleName,user.cno);
        }
    }
    @Override
    public void saveDatas(){
        if(!change) return;
        System.out.println("用户信息已经被修改,是否保存?(y/n)");
        String ans = sc.nextLine();
        if(ans.equals("y")){
            userDatas.saveUsers();
            if(delete){//删除用户,需要删除卡和交易记录
                transDatas.removeTransactionByCno(removeCno);
                cardDatas.saveCards();//保存卡号数据
                
                transDatas.createCardTransaction(cards);
                transactions = TransactionDatas.getTransactions();//图
            }
        }
        //恢复标记
        change = false;
        delete = false;
    }

    /**
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
    /**
     * 用户退出
     */
    public void exit(){
        saveDatas();
        System.exit(0);
    }
}
