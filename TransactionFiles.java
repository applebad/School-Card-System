import java.io.File;
import java.io.IOException;
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
            String fileName = fl.toString().substring(fl.toString().lastIndexOf("\\")+1, fl.toString().length());
            TransactionFile transactionFile = new TransactionFile(fileName);
            String cno = "";
            cno = fileName.substring(0, fileName.lastIndexOf("."));
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
        for(Map.Entry<String,ArrayList<Transaction>> entry : transactionsMap.entrySet()){
            TransactionFile transactionFile = new TransactionFile(entry.getKey()+".dat");
            transactionFile.save(entry.getValue());
        }
        
    }

    public Map<String,ArrayList<Transaction>> getTransactionMap(){
        return transactionsMap;
    }
    public void mkdir(String cno){
        File newFile = new File(file.toString()+"\\"+cno+".dat");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
