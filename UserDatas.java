//对users数据进行查找、添加、修改、删除等操作

import java.util.Comparator;
import java.util.ArrayList;

public class UserDatas {
    private static ArrayList<User> users = null;//用户信息
    //用二分查找
    public int findUser(String mobile){
        if(users==null) return -1;
        if(mobile==null) return -1;
        int index = -1;
        sortedUsers();//排序
        binarySearch(0,users.size(),mobile);//二分查找
        return index;
    }
    /***
     * 排序算法
     * 根据mobile进行一次排序(升序)
     */
    private void sortedUsers(){//
        // Collections.sort(users,Comparator.comparingInt((User::getmobileToInt)));
        users.sort(Comparator.comparingInt(User::getmobileToInt));
    }
    /***
     * 二分查找算法
     * @param stat 起始指针
     * @param end 结束指针
     * @param key 关键字
     * @return index 目标指针
     */
    private int binarySearch(int stat, int end, String key){
        int index = (end+stat)/2;
        if(users.get(index).getmobile().equals(key)){
            return index;
        }
        if(users.get(index).getmobileToInt() > Integer.parseInt(key)){
            return binarySearch(index + 1, end, key);
        }else{
            return binarySearch(stat, index, key);
        }
    }
}
