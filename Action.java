import java.util.Scanner;

public class Action {
    UserService usersService = new UserService();
    ManagerService managerService = new ManagerService();
    OperatorService operatorService = new OperatorService();
    CustomerService customerService = new CustomerService();
    private Scanner sc = new Scanner(System.in);
    /**
     * 系统启动时，显示菜单，提供操作
     */
    public void start(){
        while(true){
            Menu.preLoginMenu();//登录前菜单
            int item = sc.nextInt();
            if(item == 1){
                usersService.getDatas();//获取数据
                boolean pass = usersService.login();//登录
                if(!pass) continue;
                @SuppressWarnings("static-access")
                User user = usersService.users.get(usersService.getCurUserIndex());
                switch (user.role) {
                    case 0:
                        customerAction();
                        break;
                    case 1:
                        operatorAction();
                        break;
                    case 2:
                        managerAction();
                        break;
                    default:
                        break;
                }
            }else if(item==2){
                usersService.logout();
                break;
            }
        }
    }

    /**
     * 系统管理员菜单
     */
    public void managerAction(){
        boolean flag = false;
        while (true) {
            Menu.managerMenu();//菜单
            int item = sc.nextInt();sc.nextLine();
            switch (item) {
                case 1:
                    managerService.modifyPassword();
                    break;
                case 2:
                    managerService.showUsers();
                    break;
                case 3:
                    managerService.addUser();
                    break;
                case 4:
                    managerService.removeUser();
                    break;
                case 5:
                    managerService.logout();
                    flag = true;
                    break;
                case 6:
                    managerService.exit();
                    flag = true;
                    break;
                default:
                    System.out.println("错误的输入!");
                    break;
            }
            if(flag) break;
        }
    }
    /**
     * 卡业务员菜单
     */
    public void operatorAction(){
        boolean flag = false;
        while (true) {
            Menu.operatorMenu();
            int item = sc.nextInt();sc.nextLine();
            switch (item) {
                case 1:
                    operatorService.modifyPassword();
                    break;
                case 2:
                    operatorService.showCards();
                    break;
                case 3:
                    operatorService.createCard();
                    break;
                case 4:
                    operatorService.frozenCard();
                    break;
                case 5:
                    operatorService.createCard();
                    break;
                case 6:
                    operatorService.logout();
                    flag = true;
                    break;
                case 7:
                    operatorService.exit();
                    flag = true;
                    break;
                default:
                    System.out.println("错误的输入!");
                    break;
            }
            if(flag) break;
        }
    }

    /**
     * 卡用户菜单
     */
    public void customerAction(){
        boolean flag = false;
        while(true){
            Menu.customerMenu();
            int item = sc.nextInt();sc.nextLine();
            switch (item) {
                case 1:
                    customerService.modifyPassword();
                    break;
                case 2:
                    customerService.recharge();
                    break;
                case 3:
                    customerService.consume();
                    break;
                case 4:
                    customerService.showCard();
                    break;
                case 5:
                    customerService.showTransaction();
                    break;
                case 6:
                    customerService.logout();
                    flag = true;
                    break;
                case 7:
                    customerService.exit();
                    flag = true;
                    break;
                default:
                    System.out.println("错误的输入!");
                    break;
            }
            if(flag) break;
        }
    }
}
