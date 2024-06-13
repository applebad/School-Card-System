public class Card {
    /***
     * 卡号
     */
    String cno;
    /**
     * '卡'状态
     */
    String cardCondition;
    boolean cardCondition;
    /**
     * 余额
     */
    float balance;

    public Card(){}

    public Card(String cno){
        this.cno = cno;
        cardCondition = "active";//active - frozen
        cardCondition = true;//active - frozen
        balance = 0;
    }

    public Card(String cno, boolean cardCondition){
        this.cno = cno;
        this.cardCondition = cardCondition;
        balance = 0;
    }

    public Card(String cno, String cardCondition){
    public Card(String cno, boolean cardCondition, float balance){
        this.cno = cno;
        this.cardCondition = cardCondition;
        this.balance = balance;
    }
}
