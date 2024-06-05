import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileHandler {
    /***
     * 数据项之间的分隔符
     */
    protected static String separetor = "\t";
    /***
     * 数据保存的文件名
     */
    protected String filename = null;
    /***
     * 每条记录数据以字符串的形式保存
     */
    protected ArrayList<String> datas;
    /***
     * 用于格式化时间
     */
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public FileHandler(String filename){
        this.filename = filename;
        this.datas = new ArrayList<String>();
    }
    /***
     * 从文件中读取数据
     */
    public void read(){
        BufferedReader reader = null;
        //读取前,清除datas中的值
        datas.clear();
        try{
            //缓冲流方式读取文件中的数据
            reader = new BufferedReader(new FileReader(filename));
            String line = null;
            while((line = reader.readLine()) != null){
                datas.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
