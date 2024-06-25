//对users数据进行查找、添加、修改、删除等操作

import java.util.ArrayList;

public class UserDatas {

    /**
     * 用户信息
     */
    private static ArrayList<User> users = null;

    /**
     * 用二分查找
     * @param mobile 手机号
     * @return index 目标指针
     */
    public int findUser(String mobile){
        if(users==null) return -1;
        if(mobile==null) return -1;
        int index = -1;
        if(mobile.equals("admin")){//管理员通道(无法排序导致)
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).mobile.equals(mobile)){
                    return i;
                }
            }
        }
        sortedUsers();//排序
        index = binarySearch(0,users.size()-1,mobile);//二分查找
        return index;
    }
    /**
     * 排序算法
     * 根据mobile进行一次排序(升序)
     */
    private void sortedUsers(){//插入排序
        if(users.size()<=1) return;
        int base = 1;
        while(base < users.size()){
            for(int i = base; i > 0; i--){
                if(users.get(i).mobile.length() < users.get(i-1).mobile.length()){
                    swap(i, i-1);
                }else if(users.get(i).mobile.length() == users.get(i-1).mobile.length()){
                    String mobile1 = users.get(i).mobile, mobile2 = users.get(i-1).mobile;
                    for(int j = 0; j < mobile1.length(); j++){
                        if(mobile1.charAt(j) > mobile2.charAt(i)){
                            swap(i, i-1);
                        }
                    }
                }
            }
            base++;
        }
        // users.sort(Comparator.comparingInt(User::getmobileToInt));
    }
    /**
     * 交换
     * @param i
     * @param j
     */
    private void swap(int i, int j){
        User tmp = users.get(i);
        users.set(i, users.get(j));
        users.set(j, tmp);
    }
    /**
     * 二分查找算法
     * @param stat 起始指针
     * @param end 结束指针
     * @param key 关键字
     * @return index 目标指针
     */
    private int binarySearch(int stat, int end, String key){
        int index = (end + stat) / 2;
        if(stat==end && !users.get(index).mobile.equals(key)) return -1;
        if(users.get(index).getmobile().equals(key)){
            return index;
        }
        if(users.get(index).mobile.length() < key.length()){
            return binarySearch(index + 1, end, key);
        }else if(users.get(index).mobile.length() == key.length()){
            for(int i = 0; i < key.length(); i++){
                if(users.get(index).mobile.charAt(i) < key.charAt(i)){
                    return binarySearch(index + 1, end, key);
                }else if(users.get(index).mobile.charAt(i) > key.charAt(i)){
                    return binarySearch(stat, index, key);
                }
            }
            return index;
        }else{
            return binarySearch(stat, index, key);
        }
    }
    /**
     * 新增一个用户
     * @param user 用户类对象
     */
    public void addUser(User user){
        users.add(user);
    }
    /**
     * 修改users中一个用户信息
     * @param user 用户类对象
     */
    public void modifyUser(User user){
        int index = findUser(user.getmobile());
        users.set(index,user);//索引,数据
    }
    /**
     * users对象数据保存到文件
     */
    public void saveUsers(){
        UserFile file = new UserFile();
        file.save(users);
    }
    /**
     * 从文件读取所有用户信息到users对象
     * @return ArrayList<User> users
     */
    public static ArrayList<User> getUsers(){
        if(users == null){
            UserFile file = new UserFile();
            users = file.acquire();
        }
        return users;
    }
    /***
     * 清除数据
     */
    public static void clear(){
        users=null;
    }
    /***
     * 删除用户
     * @param user 用户
     */
    public void removeUser(User user) {
        int index = findUser(user.mobile);
        users.remove(index);
    }
}
