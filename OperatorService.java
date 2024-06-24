import java.util.Scanner;

public class OperatorService extends UserService{
    Scanner sc = new Scanner(System.in);
    /**
     * 显示所有卡信息
     */
    public void showCards(){
        for(Card card : cards){
            System.out.println("卡号   |   卡状态 |   卡余额");
            System.out.println(card.cno+" | "+card.cardCondition+" | "+card.balance);
        }
    }

    /**
     * 开卡
     */

    public void createCard() {
        String cno;boolean flag = true;
        while(true){
            System.out.println("输入卡号:");
            cno = sc.nextLine();
            if(CardDatas.getCardIndex(cno) > 0){//存在
                System.out.println("卡号存在!是否重新输入(y/n):");
                String ans = sc.next();
                if(ans.equals("n")){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            System.out.println("退出开卡业务!");
            return;
        }

        Card newCard = new Card(cno);
        CardDatas.addCard(newCard);
        change=true;//数据更改标识
    }

    /**
     * 冻结卡
     */
    public void frozenCard() {
        System.out.println("输入卡号:");
        String cno = sc.nextLine();
        int CardIndex = CardDatas.getCardIndex(cno);
        if(CardIndex!=-1){
            try{
                cards.get(CardDatas.getCardIndex(cno)).cardCondition = false;
                change = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("卡号不存在!");
        }
    }

    /**
     * 解冻卡
     */
    public void activateCard(){
        System.out.println("输入卡号:");
        String cno = sc.nextLine();
        int CardIndex = CardDatas.getCardIndex(cno);
        if(CardIndex!=-1){
            try{
                cards.get(CardDatas.getCardIndex(cno)).cardCondition = true;
                change = true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("卡号不存在!");
        }
    }

    @Override
    public void saveDatas(){
        if(!change) return;
        System.out.println("用户信息已经被修改,是否保存?(y/n)");
        String ans = sc.nextLine();
        if(ans.equals("y")){
            userDatas.saveUsers();
            cardDatas.saveUsers();
        }
        //恢复标记
        change = false;
    }
}
