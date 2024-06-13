public class Card {
    /***
     * 卡号
     */
    String cno;
    /**
     * '卡'状态
     */
    String cardCondition;

    public Card(){}

    public Card(String cno){
        this.cno = cno;
        cardCondition = "active";//active - frozen
    }

    public Card(String cno, String cardCondition){
        this.cno = cno;
        this.cardCondition = cardCondition;
    }
}
