import java.util.Date;

public class Transaction {
    /**
     * 消费日期 时间
     */
    Date transTime;
    /**
     * 对应卡号
     */
    String cno;
    /**
     * 变动金额
     */
    float balance;
    public Transaction(Date transTime, String cno, float balance){
        this.transTime = transTime;
        this.cno = cno;
        this.balance = balance;
    }
}
