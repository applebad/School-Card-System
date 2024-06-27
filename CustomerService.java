import java.util.ArrayList;
import java.util.Date;
public class CustomerService extends UserService{
    
   

    /**
     * 卡状态判断
    */
    @SuppressWarnings("static-access")
    public boolean getCardCondition(){
        if(users.get(curUserIndex).cno.equals("")) {
            return false;
        }
        return cards.get(CardDatas.getCardIndex(users.get(curUserIndex).cno)).cardCondition;
    } 


    /**
     * 充钱
     */
    public void recharge() {
        if (getCardCondition()){
            Card xcard = null;
            while (true) {
                try{
                    xcard = cards.get(CardDatas.getCardIndex(users.get(curUserIndex).cno));
                    System.out.println("输入充值金额：");
                    float rechargemoney = sc.nextFloat();
                    if(rechargemoney<=0){
                        System.out.println("非法的充值金额!");
                        return;
                    }
                    xcard.balance += rechargemoney;
                    System.out.println("充值成功！");
                    
                    if(transactions.get(xcard.cno)==null){//判空 则实例化一个列表
                        transactions.put(xcard.cno, new ArrayList<Transaction>());
                    }
                    transactions.get(xcard.cno).add(new Transaction(new Date(), xcard.cno, rechargemoney));
                    change = true;
                    break;
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println("发生错误，请重新输入！");
                }    
            }
        }else{
            System.out.println("该卡不存在或被冻结！");
        }
        
        
    }
    /**
     * 消费
     */
    public void consume() {
        if(getCardCondition()){
            Card xcard = null;
            while (true) {
                try{
                    xcard = cards.get(CardDatas.getCardIndex(users.get(curUserIndex).cno));
                    System.out.println("您本次消费的金额是：");
                    float consumemoney = sc.nextFloat();
                    if(consumemoney<=0){
                        System.out.println("非法的消费金额!");
                        return;
                    }else if(xcard.balance-consumemoney < 0){
                        System.out.println("余额不足!请及时充值");
                        return;
                    }
                    xcard.balance -= consumemoney;
                    System.out.println("扣费成功");
                    
                    if(transactions.get(xcard.cno)==null){//判空 则实例化一个列表
                        transactions.put(xcard.cno, new ArrayList<Transaction>());
                    }
                    transactions.get(xcard.cno).add(new Transaction(new Date(), xcard.cno, -consumemoney));
                    change = true;
                    break;
                }
                catch(Exception e){
                    System.out.println("发生错误，请重新输入！");
                }    
            }
        }else{
            System.out.println("该卡不存在或被冻结！");
        }
    }
    /**
     * 显示卡信息
     */
    public void showCard() {
        if(getCardCondition()){
            Card xcard = null;
            System.out.printf("%-26s|%-20s|%-15s\n","卡号","卡状态","卡余额");
            while (true) {
                try{
                    xcard = cards.get(CardDatas.getCardIndex(users.get(curUserIndex).cno));
                    System.out.printf("%-26s|%-20s|%-15s\n",xcard.cno,xcard.cardCondition,df.format(xcard.balance));
                    break;
                }
                catch(Exception e){
                    System.out.println("发生错误，请重新输入！");
                }
            }
        }else{
            System.out.println("该卡不存在或被冻结！");
        }
    }
    /**
     * 显示卡消费记录
     */
    public void showTransaction() {
        System.out.printf("%-20s|%-20s|%-15s\n","消费时间","消费卡号","金额变动");
        ArrayList<Transaction> transaction = transactions.get(users.get(curUserIndex).cno);
        if(transaction==null) return;
        for(Transaction trans : transactions.get(users.get(curUserIndex).cno)){
            System.out.printf("%-20s|%-20s|%-15s\n",sdf.format(trans.transTime),trans.cno,df.format(trans.balance));
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
        if(change){
            userDatas.saveUsers();
            cardDatas.saveCards();
            transDatas.saveTransaction();
        }
    }
    
}
