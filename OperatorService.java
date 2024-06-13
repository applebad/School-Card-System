import java.util.Scanner;

public class OperatorService extends UserService{
    Scanner sc = new Scanner(System.in);
    /**
     * 显示所有卡信息
     */
    public void showCards(){
        //TODO 
    }

    /**
     * 开卡
     */

    public void createCard() {
        String cno;boolean flag = true;
        while(true){
            System.out.println("输入卡号:");
            cno = sc.nextLine();
            if(CardDatas.getCardIndex(cno) > 0){//存在
                System.out.println("卡号存在!是否重新输入(y/n):");
                String ans = sc.next();
                if(ans.equals("n")){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            System.out.println("退出开卡业务!");
            return;
        }

        Card newCard = new Card(cno);
        
    }

    /**
     * 冻结卡
     */
    public void frozenCard() {
        //TODO
    }

    /**
     * 解冻卡
     */
    public void activateCard(){
        //TODO
    }
    /**
     * 注销
     */
    public void logout() {
       //TODO
    }

    /**
     * 退出该卡
     */
    public void exit() {
        //TODO
    }
}
