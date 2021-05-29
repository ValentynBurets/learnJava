import java.util.ArrayList;

public class ComputerFirm {

    public static void main(String[] args){
        PickUpManager PickUp = new PickUpManager();
        Computer comp = PickUp.HandleOrder(500);
        String s = comp.toString();

        //System.out.println(s);

        PickUp.SortAccessoriesByFirm();
        String s2 = PickUp.toString();
        //System.out.println(s2);

        PickUp.SortAccessoriesByName();
        String s3 = PickUp.toString();
        System.out.println(s3);

        PickUp.SortAccessoriesByPrice();
        String s4 = PickUp.toString();
        //System.out.println(s4);

        PickUp.SortAccessoriesByQuantity();
        String s5 = PickUp.toString();
        //System.out.println(s5);

    }
/*
    PickUpManager PickUp;

    ComputerFirm()
    {
        PickUp = new PickUpManager();
    }




    public void sort(){

        ArrayList<Item> videocards = new ArrayList<>();
        videocards.add(new Video("geforse 1020", 1000, "zotak", 2000, "nvidia"));
        videocards.add(new Video("hd 8090" ,1200, "hiperx", 3000, "AMD"));
        videocards.add(new Video("geforse 1070", 1900, "asusRog", 6000, "nvidiaQuadro"));

        PickUp.SortComponentByHigherPrice(videocards);

        for (var item: videocards ) {
            System.out.println(item.Name+ " ");
            System.out.println(item.price + " ");
            System.out.println(item.Firm + "\n");
        }


    }
*/
/*
    List<Computer> computers;
    List<Video> videoCards;
    List<Memory> memory;
    List<Mother> motherboards;
    List<CPU> processors;

    */
}

/*
public class OuterClass implements Comparable{
    public String           field01;
    public int              field02;
    public InnerClass       innerField;

    public class InnerClass implements Comparable {
        public int              innerField01;
        public BigDecimal       innerField02;

        @Override
        public int compareTo(Object o) {
            //...
        }
    }

    @Override
    public int compareTo(Object o) {
        OuterClass obj = (OuterClass)o;
        int res = field01.compareTo(obj.field01);

        if (res != 0) {
            return res;
        }
        return this.innerField.compareTo(obj.innerField);
    }
*/