import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TransactionFiles {

    Map<String,ArrayList<Transaction>> transactionsMap = new HashMap<String,ArrayList<Transaction>>();

    File[] files = null;
    File file = new File("data\\transactions\\");
    public TransactionFiles(){
        try {
            this.files = file.listFiles();//文件名
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(File fl: files){
            TransactionFile transactionFile = new TransactionFile(fl.toString());
            String cno = "";
            cno = fl.toString().substring(0, fl.toString().lastIndexOf("."));
            transactionsMap.put(cno, transactionFile.getTransaction());
        }
    }

    public void removeTransactionByCno(String cno) {
        File data = new File("data\\transactions\\"+cno+".dat");
        if(data.exists() &&data.isFile()){//是文件则删除
            data.delete();
        }
        transactionsMap.remove(cno);
    }

    public void saveTransaction(String cno,ArrayList<Transaction> transactions){
        TransactionFile transactionFile = new TransactionFile(cno);
        transactionFile.save(transactions);
    }

    public void saveTransactionMap(Map<String,ArrayList<Transaction>> newMap){
        transactionsMap = newMap;
    }

    public Map<String,ArrayList<Transaction>> getTransactionMap(){
        return transactionsMap;
    }
    
}
