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
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("卡号不存在!");
        }
    }
}
