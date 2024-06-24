import java.util.ArrayList;
import java.util.Map;


public class TransactionDatas {
    private static Map<String,ArrayList<Transaction>> transactionMap = null;

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
        TransactionFiles transactionFiles = new TransactionFiles();
        transactionFiles.saveTransactionMap((Map<String, ArrayList<Transaction>>) transactionMap);
    }
    /**
     * 获取交易记录
     * @return
     */
    public static Map<String,ArrayList<Transaction>> getTransactions() {
        if(transactionMap==null){
            TransactionFiles transactionFiles = new TransactionFiles();//通过构造方法获取文件中的数据
            transactionMap = transactionFiles.getTransactionMap();//存到内存
        }
        return transactionMap;
    }
    /**
     * 清空交易记录
     */
    public static void clear() {
        
    }
    
}
