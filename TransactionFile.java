public class TransactionFile extends FileHandler{

    public TransactionFile(String filename){
        super(filename, "data"+FileSeparator+"transactions");
    }
    
}
