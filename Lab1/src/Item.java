

import java.util.Comparator;
import java.lang.Integer;

public abstract class Item {
    protected String Name;
    protected String Firm;
    protected int price;
    protected itemCategory cat;
    protected int quantity;

    public int getPrice() {
        return price;
    }

    public String getFirm() {
        return Firm;
    }

    public String getName() {
        return Name;
    }

    public void setFirm(String firm) {
        Firm = firm;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public int getQuantity(){return this.quantity;}

    public Item(){
        this("Item", "Firm,", 100, itemCategory.none, 1);
    }

    public Item(String name, String firm, int price, itemCategory cat, int quantity){
        this.Name = name;
        this.cat = cat;
        this.Firm = firm;
        this.price = price;
        this.quantity = quantity;
    }
    
    public int compareTo(Item p){
        int i = Name.compareTo(p.getName());
        return i;
    }

    @Override
    public int hashCode(){
        return cat.hashCode() + (int)price;
    }


    /*
    public static Comparator<Item> PriceComparator = new Comparator<Item>(){

        public int compare(Item o1, Item o2) {
            Integer ob1 = o1.getPrice();
            Integer ob2 = o2.getPrice();

            //ascending order
            return ob1.compareTo(ob2);
            //return o1.getPrice().compareTo(o2.getPrice());

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };


     */
}
