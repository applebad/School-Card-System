import java.text.ParseException;
import java.util.ArrayList;

public class TransactionFile extends FileHandler{

    public TransactionFile(String fileName){
        super(fileName, "data"+FileSeparator+"transactions");
    }
    
    /**
     * 读取文件数据保存到transaction中
     * @return transaction
     */
    public ArrayList<Transaction> acquire(){
        datas.clear();//读取数据前先清除datas
        read();
        if(datas == null) return null;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        for(int i = 0; i < datas.size(); i++){
            Transaction transaction = dataFromString(datas.get(i));
            if(transactions != null) transactions.add(transaction);
        }
        return transactions;
    }
    
    /**
     * 将读取的字符串数据转化成用户对象
     * @param line 数据行
     * @return
     */
    private Transaction dataFromString(String line) {
        Transaction transaction = null;
        //按照separator 分隔符分割
        String[] item = line.split(separator);
        try {
            transaction = new Transaction(sdf.parse(item[0]), item[1], Float.parseFloat(item[2]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    public ArrayList<Transaction> getTransaction() {
        return acquire();
    }
    
}
