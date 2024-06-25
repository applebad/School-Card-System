import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class OperatorService extends UserService{
    Scanner sc = new Scanner(System.in);
    /**
     * 显示所有卡信息
     */
    public void showCards(){
        System.out.printf("%-18s|%-17s|%-17s|%-17s\n","卡号","卡状态","卡余额","卡持有人");
        for(Card card : cards){
            System.out.printf("%-20s|%-20s|%-20s|%-20s\n",card.cno,card.cardCondition,card.balance,card.mobile);
        }
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
            }else {
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.println("退出开卡业务!");
            return;
        }
        System.out.println("输入持卡人账户:");
        String mobile = sc.nextLine();
        int createCardUserIndex = userDatas.findUser(mobile);
        if(createCardUserIndex > 0){//账户存在
            Card newCard = new Card(cno,true,0,mobile);
            cardDatas.addCard(newCard);
            users.get(createCardUserIndex).cno = cno;
            change=true;//数据更改标识
        }else{
            System.out.println("账户不存在！");
        }
    }

    /**
     * 冻结卡
     */
    public void frozenCard() {
        System.out.println("输入卡号:");
        String cno = sc.nextLine();
        int CardIndex = CardDatas.getCardIndex(cno);
        if(CardIndex!=-1){
            try{
                cards.get(CardDatas.getCardIndex(cno)).cardCondition = false;
                change = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("卡号不存在!");
        }
    }

    /**
     * 解冻卡
     */
    public void activateCard(){
        System.out.println("输入卡号:");
        String cno = sc.nextLine();
        int CardIndex = CardDatas.getCardIndex(cno);
        if(CardIndex!=-1){
            try{
                cards.get(CardDatas.getCardIndex(cno)).cardCondition = true;
                change = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("卡号不存在!");
        }
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

    @Override
    public void saveDatas(){
        if(!change) return;
        System.out.println("用户信息已经被修改,是否保存?(y/n)");
        String ans = sc.nextLine();
        if(ans.equals("y")){
            userDatas.saveUsers();
            cardDatas.saveCards();
        }
        //恢复标记
        change = false;
    }

     /**
     * 显示卡消费记录
     */
    public void showTransaction() {
        System.out.printf("%-26s|%-20s|%-10s\n","消费时间","消费卡号","金额变动");
        if(transactions==null) return;
        for(Map.Entry<String,ArrayList<Transaction>> map: transactions.entrySet()){
            ArrayList<Transaction> transaction = map.getValue();
            for(Transaction trans:transaction){
                System.out.printf("%-30s|%-24s|%-10s\n",sdf.format(trans.transTime),trans.cno,df.format(trans.balance));
            }
        }
    }
}
