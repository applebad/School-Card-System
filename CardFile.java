import java.util.ArrayList;

/**
 * 卡文件处理类
*/
public class CardFile extends FileHandler {
    public CardFile() {
        super("cards.dat","data"+FileSeparator+"cards");
    }

    public ArrayList<Card> acquire() {
        datas.clear();//读取数据前先清除datas
        read();
        if(datas == null) return null;
        ArrayList<Card> cards = new ArrayList<Card>();
        for(int i = 0; i < datas.size(); i++){
            Card card = dataFromString(datas.get(i));
            if(cards != null) cards.add(card);
        }
        return cards;
    }
    /**
     * 转换
     * @param string
     * @return go to acquire()
     */
    private Card dataFromString(String string) {
        Card card = null;
        String[] item = string.split(separator);
        card = new Card(item[0],Boolean.parseBoolean(item[1]));
        return card;
    }
}
