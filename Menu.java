public class Menu {
    public static void preLoginMenu(){
        System.err.println("\n\n1.登录");
        System.out.println("2.退出");
        System.out.println("\n\t请选择你的操作:");
    }
    /***
     * 系统管理员菜单
     */
    public static void managerMenu(){
        System.out.println("\n\n\t1. 修改密码");
        System.out.println("\t2. 浏览用户信息");
        System.out.println("\t3. 添加新用户");
        System.out.println("\t4. 删除用户");
        System.out.println("\t5. 注销");
        System.out.println("\t6. 退出");
        System.out.println("\n\t 请选择您的操作：");
    }
    /***
     * 卡业务员操作
     */
    public static void operatorMenu(){
        System.out.println("\n\n\t1. 修改密码");
        System.out.println("\t2. 浏览校园卡信息");
        System.out.println("\t3. 开卡");
        System.out.println("\t4. 挂失");
        System.out.println("\t5. 解挂");
        System.out.println("\t6. 注销");
        System.out.println("\t7. 退出");
        System.out.println("\t8. 查看所有卡的消费记录");
        System.out.println("\n\t 请选择您的操作：");
    }
    /***
    * 卡用户菜单
    */ 
    public static void customerMenu(){
        System.out.println("\n\n\t1. 修改密码");
        System.out.println("\t2. 充值");
        System.out.println("\t3. 消费");
        System.out.println("\t4. 我的校园卡");
        System.out.println("\t5. 查看交易记录");//添加功能:根据日期查找
        System.out.println("\t6. 注销");
        System.out.println("\t7. 退出");
        System.out.println("\n\t 请选择您的操作：");
    }
}
