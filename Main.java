import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //初始化 只执行一次 创建管理员
        init();
        Action action = new Action();
        action.start();
    }
    public static void init(){
        User user = new User("admin","admin","admin",2,"");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        UserFile userDatas = new UserFile();
        userDatas.save(users);
    }
}
