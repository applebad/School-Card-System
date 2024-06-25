import java.util.ArrayList;

public class MyTool<T extends Comparable<T>>{
    public MyTool(){}

    public void sortArrayList(int i, int j, ArrayList<T> input){
        if(j - i <= 0) return;
        T base = input.get(i);
        int start = i,end = j + 1;
        while(true){
            while(end > start && base.compareTo(input.get(--end))<=0){}
            if(start==end) break;
            else{
                input.set(start, input.get(end));
            }
            while(end > start && base.compareTo(input.get(++start))>=0){}
            if(start==end) break;
            else{
                input.set(end,input.get(start));
            }
        }
        input.set(end, base);
        sortArrayList(i, end-1, input);
        sortArrayList(end+1,j,input);
    }
    public void sortArrayList(ArrayList<T> input){
        sortArrayList(0,input.size()-1,input);
    }
}
