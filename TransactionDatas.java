import java.util.ArrayList;


public class TransactionDatas {
    
    /**
     * 移除卡号交易记录
     * @param cno 卡号
     */
    public void removeTransactionByCno(ArrayList<String> cnos) {
        TransactionFiles transactionFiles = new TransactionFiles();
        for(String cno:cnos){
            transactionFiles.removeTransactionByCno(cno);
        }
    }
    /**
     * 保存交易记录数据
     */
    public void saveTransaction() {
       
    }
    /**
     * 获取交易记录
     * @return
     */
    public static ArrayList<Transaction> getTransactions() {
        TransactionFiles transactionFiles = new TransactionFiles();
        
    }
    /**
     * 清空交易记录
     */
    public static void clear() {
        
    }
    
}
