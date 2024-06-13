public class CustomerService extends UserService{
    
   

    /**
     * 卡状态判断
    */
    @SuppressWarnings("static-access")
    public boolean getCardCondition(){
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
                    xcard.balance += rechargemoney;
                    System.out.println("充值成功！");
                    break;
                }
                catch(Exception e){
                    System.out.println("发生错误，请重新输入！");
                }    
            }
        }else{
            System.out.println("该卡被冻结！");
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
                    xcard.balance -= consumemoney;
                    System.out.println("扣费成功");
                    break;
                }
                catch(Exception e){
                    System.out.println("发生错误，请重新输入！");
                }    
            }
        }else{
            System.out.println("该卡被冻结！");
        }


       
    }
    /**
     * 显示卡信息
     */
    public void showCard() {
        if(getCardCondition()){
            Card xcard = null;
            while (true) {
                try{
                    xcard = cards.get(CardDatas.getCardIndex(users.get(curUserIndex).cno));
                    System.out.println("卡号   |   卡状态 |   卡余额");
                    System.out.println(xcard.cno+" | "+xcard.cardCondition+" | "+xcard.balance);
                    break;
                }
                catch(Exception e){
                    System.out.println("发生错误，请重新输入！");
                }
            }
        }else{
            System.out.println("该卡被冻结！");
        }
    }
    /**
     * 显示卡消费记录
     */
    public void showTransaction() {
        

        
    }
 
    
}
