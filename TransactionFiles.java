import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TransactionFiles {

    Map<String,ArrayList<Transaction>> transactionsMap = new HashMap<String,ArrayList<Transaction>>();

    File[] files = null;

    public TransactionFiles(){
        try {
            File file = new File("data\\transactions\\");
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
        transactionsMap.remove(cno);
    }
    
}
