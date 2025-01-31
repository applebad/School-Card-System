import java.util.ArrayList;

public class UserFile extends FileHandler{

    /**
     * 用户文件数据的读取与保存
     */
    public UserFile(){
        super("users.dat","data"+FileSeparator+"users");
    }

    /**
     * 读取文件数据保存到users中
     * @return users
     */
    public ArrayList<User> acquire(){
        datas.clear();//读取数据前先清除datas
        read();
        if(datas == null) return null;
        ArrayList<User> users = new ArrayList<User>();
        for(int i = 0; i < datas.size(); i++){
            User user = dataFromString(datas.get(i));
            if(users != null) users.add(user);
        }
        return users;
    }

    /**
     * 将users中数据写入文件
     * @param users 用户集
     */
    public void save(ArrayList<User> users){
        if(users.isEmpty()) return;
        datas.clear();
        for(int i = 0; i < users.size(); i++){
            datas.add(dataToString(users.get(i)));
        }
        write();
    }

    /**
     * 将读取的字符串数据转化成用户对象
     * @param line 数据行
     * @return
     */
    private User dataFromString(String line){
        User user = null;
        //按照separator 分隔符分割
        String[] item = line.split(separator);
        String cno = "";
        if(item.length == 5) cno = item[4];
        //用户没有卡号时,数组长度为4
        if(item.length >= 4){
            user = new User(item[0],item[1],item[2],Integer.parseInt(item[3]),cno);
        }
        return user;
    }

    /**
     * 将用户对象转化成字符串
     * @param user 用户对象
     * @return 用户->字符串
     */
    public String dataToString(User user){
        String line = null;
        if(user!=null){
            //使用sparator符号连接数据项
            line = user.mobile+separator+user.password+separator+user.userName+separator+user.role+separator+user.cno+separator+'\n';
        }
        return line;
    }
}
