import jdk.jfr.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class PickUpManager {

    private static SortType sortType;
    private static PickUpManager instance;
    private computerAccessories accessories;

    public PickUpManager()
    {
        sortType = SortType.increasing;
        accessories = new computerAccessories();
        accessories.AddItem(new Video("geforse 1060", 200, "asus", 3000, VideoType.nVidia, itemCategory.gamer, 3));
        accessories.AddItem(new Video("geforse 1020", 100, "asrok", 1000, VideoType.nVidia, itemCategory.child, 1));
        accessories.AddItem(new Video("hd6670", 50, "palit", 500, VideoType.AMD, itemCategory.none, 2));

        accessories.AddItem(new CPU(cpuType.AMD, 2000, 10, 400, itemCategory.none, 5));
        accessories.AddItem(new CPU(cpuType.Intel, 3000, 60, 800, itemCategory.gamer, 2));
        accessories.AddItem(new CPU(cpuType.Motorola, 1000, 5, 100, itemCategory.none, 6));

        accessories.AddItem(new Memory(50, "Kingston", 8000, memoryType.DDR4, 4));
        accessories.AddItem(new Memory(30, "SiliconPower", 4000, memoryType.SODIM_DDR3, 5));
        accessories.AddItem(new Memory(20, "Crusial", 2000, memoryType.DDR2, 3));

    }


    public static PickUpManager getInstance() {
        if(instance == null)
            instance = new PickUpManager();
        return instance;
    }

    public static void setSortType(SortType type){
        PickUpManager.sortType = type;
    }

    //static inner class
    public static class accessoriesComparatorByName implements  Comparator<Item> {
        public int compare(Item o1, Item o2) {
            // descending order (ascending order would be: o1.getGrade()-o2.getGrade())
            int result = o1.getName().compareTo(o2.getName());
            if(sortType == SortType.increasing)
                return result;
            else
                return -result;
        }
    }

    //inner class
    class accessoriesComparatorByPrice implements Comparator<Item> {
           public int compare(Item o1, Item o2){
               int mult = 1;
               if(sortType == SortType.decreasing)
                   mult = -1;
               double  sub = o1.getPrice() - o2.getPrice();
               if(sub > 0)
                   return 1 * mult;
               if(sub < 0)
                   return -1 * mult;
               return 0;
           }
    }

    public void SortAccessoriesByName(ArrayList<Item> a){
        accessories.Sort(a, new accessoriesComparatorByName());
    }

    public void SortAccessoriesByPrice(ArrayList<Item> a){
        accessories.Sort(a, new accessoriesComparatorByPrice());
    }


    public void SortAccessoriesByName(){
        accessories.Sort(new accessoriesComparatorByName());
    }

    public void SortAccessoriesByPrice(){
        accessories.Sort(new accessoriesComparatorByPrice());
    }

    //anonymous class
    public void SortAccessoriesByFirm(ArrayList<Item> a){
        accessories.Sort(a, new Comparator<Item>(){
            public int compare(Item o1, Item o2) {
                int mult = 1;
                if (sortType == SortType.decreasing)
                    mult = -1;

                var i = (o1.getFirm().compareTo(o2.getFirm())) * mult;
                return i;
            }
        });
    }

    public void SortAccessoriesByQuantity(ArrayList<Item> a){
        accessories.Sort(a, (Item o1, Item o2) -> {
            int mult = 1;
            if(sortType == SortType.decreasing)
                mult = -1;
            return (o1.getQuantity() - o2.getQuantity()) * mult;
        });
    }

    //anonymous class
    public void SortAccessoriesByFirm(){
        accessories.Sort(new Comparator<Item>(){
            public int compare(Item o1, Item o2) {
                int mult = 1;
                if (sortType == SortType.decreasing)
                    mult = -1;

                var i = (o1.getFirm().compareTo(o2.getFirm())) * mult;
                return i;
            }
        });
    }

    public void SortAccessoriesByQuantity(){
        accessories.Sort((Item o1, Item o2) -> {
            int mult = 1;
            if(sortType == SortType.decreasing)
                mult = -1;
            return (o1.getQuantity() - o2.getQuantity()) * mult;
        });
    }


    public Computer HandleOrder(){
        return accessories.GetComputer(500);
    }

    public Computer HandleOrder(int price){
        return accessories.GetComputer(price);
    }

    @Override
    public String toString(){
        String s = accessories.Show();
        return s;
    }

}


    /*

    public ArrayList<Item> SortComponentByLowerPrice(ArrayList<Item> components)

    public ArrayList<Item> SortComponentByFirm(ArrayList<Item> components);
    public ArrayList<Item> findComputerByPrice(int price);
    */


/*
public static class Student {
    private final String name;
    private final int grade;
}

public static class GradeComparator implements Comparator<Student> {
    @Override public int compare(Student o1, Student o2) {
        // descending order (ascending order would be: o1.getGrade()-o2.getGrade())
        return o2.getGrade() - o1.getGrade();
    }
}


*/

