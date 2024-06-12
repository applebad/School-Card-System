import java.util.Comparator;
import java.util.ArrayList;
/***
 * test:
 * 测试类
 */
public class test {
    public static void main(String[] args) {
        System.out.println("test");
        BinarySearchTest();
    }

    /***
     * 二分查找 "排序" 测试
     * 注:测试通过
     */
    public static void BinarySearchTest(){
        ArrayList<User> users = new ArrayList<User>();
        User user1 = new User("0006", "666", "第一个插入", 0, null);
        User user2 = new User("0007", "666", "第一个插入", 0, null);
        User user3 = new User("0008", "666", "第一个插入", 0, null);
        User user4 = new User("0009", "666", "第一个插入", 0, null);
        users.add(user4);
        users.add(user3);
        users.add(user2);
        users.add(user1);
        users.sort(Comparator.comparingInt(User::getmobileToInt));
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i).mobile);
        }
        return;
    }
}
