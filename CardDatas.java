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
        int index = getCardIndex(0, cards.size(), cno);
        if(index < 0) return false;
        cards.remove(index);
        return true;
    }

    public void saveUsers() {
        // TODO Auto-generated method stub
        
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
    public static void addCard(Card card){
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
        }else if(Integer.parseInt(cno) > Integer.parseInt(cards.get(mid).cno)){
            return getCardIndex(mid+1, end, cno);
        }else{
            return getCardIndex(stat, mid-1, cno);
        }
    }
    protected static int getCardIndex(String cno){
        return getCardIndex(0, cards.size(), cno);
    }

    /**
     * 冒泡排序 key = 卡号
     */
    protected static void sortCards(){
        boolean flag = true;
        for(int i = 0; i < cards.size(); i++){
            for(int j = 0; j < cards.size() - i; i++){
                if(Integer.parseInt(cards.get(i).cno)>Integer.parseInt(cards.get(j).cno)){
                    Card tmp = cards.get(i);
                    cards.set(i, cards.get(j));
                    cards.set(j, tmp);
                    flag = false;
                }
            }
            if(flag){
                break;
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
