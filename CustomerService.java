public class CustomerService extends UserService{
    
   

    /**
     * 卡状态判断
    */
    public boolean getCardCondition(){
        return cards.get(cardDatas.getCardIndex(curUserIndex, curUserIndex, users.get(curUserIndex).cno)).cardCondition;
    } 

    /**
     * 充钱
     */
    public void recharge() {
        
        
        System.out.println("输入充值金额：");
        float balance = sc.nextFloat();
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recharge'");
    }
    /**
     * 消费
     */
    public void consume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consume'");
    }
    /**
     * 显示卡信息
     */
    public void showCard() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showCard'");
    }
    /**
     * 退出该卡
     */
    public void exit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exit'");
    }
    /**
     * 显示卡消费记录
     */
    public void showTransaction() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showTransaction'");
    }
    /**
     * 注销卡
     */
    public void logout() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }
    
}
