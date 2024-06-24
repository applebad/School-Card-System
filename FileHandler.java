import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.File;
public class FileHandler {
    /**
     * 数据项之间的分隔符
     */
    protected static String separator = "\t";
    /**
     * 文件路径分隔符
     */
    protected static String FileSeparator = "\\";
    /**
     * 数据保存的文件名
     */
    protected String filename = null;
    /**
     * 数据保存的路径
     */
    protected String fileContent = null;
    /**
     * 每条记录数据以字符串的形式保存
     */
    protected ArrayList<String> datas;

    /**
     * 用于格式化时间
     */
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public FileHandler(String filename, String fileContent){
        this.filename = filename;
        this.fileContent = fileContent;
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
            File file = new File(fileContent+FileSeparator+filename);
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            reader = new BufferedReader(fr);
            String line = null;
            while((line = reader.readLine()) != null){
                datas.add(line);//每条记录保存为一个字符串对象
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

    /***
     * 将数据保存到文件中
     */
    public void write(){
        //内容
        String content = listToString();
        if(content == null) return;
        FileWriter writer = null;
        try{
            writer = new FileWriter(fileContent+FileSeparator+filename);
            writer.write(content);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    /***
     * datas对象->字符串
     * @return content:字符串(转换后)
     */
    public String listToString(){
        if(datas == null) return null;
        String content = "";
        for(int i = 0;i < datas.size();i++){
            content += datas.get(i);
        }
        return content;
    }
}
