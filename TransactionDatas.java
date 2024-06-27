import java.util.ArrayList;
import java.util.HashMap;
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
    public void saveTransaction(){
        TransactionFiles transactionFiles = new TransactionFiles();
        transactionFiles.saveTransactionMap((Map<String, ArrayList<Transaction>>) transactionMap);//保存数据到本地
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
        for(Map.Entry<String,ArrayList<Transaction>> entry : transactionMap.entrySet()){
            TransactionFile transactionFile = new TransactionFile(entry.getKey()+".dat");
            transactionMap.put(entry.getKey(), transactionFile.acquire());
        }
        return transactionMap;
    }
    /**
     * 清空交易记录
     */
    public static void clear() {
        transactionMap = null;
    }

    /**
     * 卡存在 则生成对应实例和文件 不存在 则删除
     */
    public void createCardTransaction(ArrayList<Card> cards){
        try{
            TransactionFiles transactionFiles = new TransactionFiles();
            if(transactionMap==null){//不存在则生成
                transactionMap = new HashMap<String,ArrayList<Transaction>>();
                for(Card card : cards){
                    transactionMap.put(card.cno, null);
                    transactionFiles.mkdir(card.cno);
                }
                return;
            }
            boolean flag = false;
            for(Card card : cards){//卡存在而无消费 则生成
                flag = false;
                for(Map.Entry<String,ArrayList<Transaction>> entry : transactionMap.entrySet()){
                    if(card.cno.equals(entry.getKey())){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    transactionMap.put(card.cno, null);
                }
            } 
            flag = false;
            for(Map.Entry<String,ArrayList<Transaction>> entry : transactionMap.entrySet()){//有消费记录而无卡 则删除
                flag = false;
                for(Card card : cards){
                    if(card.cno.equals(entry.getKey())){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    transactionMap.remove(entry.getKey());
                    transactionFiles.removeTransactionByCno(entry.getKey());
                }
            }
            saveTransaction();
        }catch(Exception e){}
    }
}
