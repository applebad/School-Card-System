public class Card {
    /***
     * 卡号
     */
    String cno;
    /**
     * '卡'状态 True = 激活
     */
    boolean cardCondition;
    /**
     * 余额
     */
    float balance;

    /**
     * 持卡人账户
     */
    String mobile;
    public Card(){}

    public Card(String cno){
        this.cno = cno;
        cardCondition = true;//active - frozen
        balance = 0;
    }

    public Card(String cno, boolean cardCondition){
        this.cno = cno;
        this.cardCondition = cardCondition;
        balance = 0;
    }

    public Card(String cno, boolean cardCondition, float balance){
        this.cno = cno;
        this.cardCondition = cardCondition;
        this.balance = balance;
    }

    public Card(String cno, boolean cardCondition, float balance, String mobile){
        this.cno = cno;
        this.cardCondition = cardCondition;
        this.balance = balance;
        this.mobile = mobile;
    }
}
