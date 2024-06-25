import java.util.ArrayList;

public class CardDatas {
    /**
     * 卡数据
     */
    private static ArrayList<Card> cards = null;

    /**
     * 删除卡号
     * @param cno 卡号
     */
    public boolean removeCard(String cno) {
        if(isSorted()) sortCards();
        int index = getCardIndex(0, cards.size(), cno);
        if(index < 0) return false;
        cards.remove(index);
        return true;
    }

    /**
     *  cards对象数据保存到文件
    */
    public void saveCards() {
        CardFile cardFile = new CardFile();
        cardFile.save(cards);
    }
    /**
     * 读取
     * @return
     */
    public static ArrayList<Card> getCards() {
        if(cards==null){
            CardFile cardFile = new CardFile();
            cards = cardFile.acquire();
        }
        return cards;
    }
    /**
     * 按序插入
     */
    public void addCard(Card card){
        cards.add(card);
        sortCards();
    }

    public static void clear() {
        cards = null;
    }

    /**
     * 二分查找
     * @param stat
     * @param end
     * @param cno
     * @return
     */
    protected static int getCardIndex(int stat, int end, String cno){
        int mid = (stat + end) / 2;
        if(cno.equals(cards.get(mid).cno)){
            return mid;
        }else if(stat == end){
            if(cno.equals(cards.get(mid).cno)){
                return mid;
            }else{
                return -1;
            }
        }else if(Integer.parseInt(cno) > Integer.parseInt(cards.get(mid).cno)){
            return getCardIndex(mid+1, end, cno);
        }else{
            return getCardIndex(stat, mid-1, cno);
        }
    }
    protected static int getCardIndex(String cno){
        sortCards();
        if(cards.size()==0) return -1;
        return getCardIndex(0, cards.size()-1, cno);
    }

    /**
     * 冒泡排序 key = 卡号
     */
    protected static void sortCards(){
        boolean flag = true;
        for(int i = 0; i < cards.size()-1; i++){
            for(int j = 0; j < cards.size() - i - 1; j++){
                if(cards.get(j).cno.length() > cards.get(j + 1).cno.length()){
                    Card tmp = cards.get(j);
                    cards.set(j, cards.get(j+1));
                    cards.set(j, tmp);
                    flag = false;
                }else if(cards.get(j).cno.length() == cards.get(j + 1).cno.length()){
                    for(int k = 0; k < cards.get(j).cno.length(); k++){
                        if(cards.get(j).cno.charAt(k) > cards.get(j + 1).cno.charAt(k)){
                            Card tmp = cards.get(j);
                            cards.set(j, cards.get(j+1));
                            cards.set(j+1, tmp);
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(flag){
                break;
            }else{
                flag = true;
            }
        }
    }
    /**
     * 判断 cards 是否有序
     * @return true 有序 / false 无序
     */
    protected boolean isSorted(){
        boolean flag = true;
        for(int i = 0; i < cards.size() - 1; i++){
            if(Integer.parseInt(cards.get(i).cno)>Integer.parseInt(cards.get(i+1).cno)){
                flag = false;
                break;
            }
        }
        return flag;
    }
}
