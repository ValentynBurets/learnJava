import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class computerAccessories {
    private ArrayList<Item> items;

    public computerAccessories(){
        items = new ArrayList<>();
    }

    public void AddItem(Item i) {
        items.add(i);
    }

    public void Sort(ArrayList<Item> accessories, Comparator<Item> compare)
    {
        Collections.sort(accessories, compare);
    }

    public void Sort(Comparator<Item> compare)
    {
        Collections.sort(items, compare);
    }

    public Computer GetComputer(int price){

        Memory m = null;
        Video v = null;
        CPU c = null;

        double c_price = price / 2;
        double v_price = ((price / 2) * 0.7);
        double m_price = price / 2 - v_price;

        for(var item : items) {
            if(item instanceof Memory)
            {
                if(item.getPrice() < m_price){
                    if(m == null){
                        m = (Memory) item;
                    }
                }
            }
            if(item instanceof Video)
            {
                if(item.getPrice() < v_price){
                    if(v == null){
                        v = (Video) item;
                    }
                }

            }
            if(item instanceof CPU)
            {
                if(item.getPrice() < c_price){
                    if(c == null){
                        c = (CPU) item;
                    }
                }

            }
        }
        Computer comp = new Computer(m, v, c);

        return comp;
    }

    public String Show() {
        String s = new String();

        for (var item: items){
            s += item.toString();
            s += "\n";
        }

        return s;

    }



}


