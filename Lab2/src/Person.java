import java.util.Comparator;

public class Person {
    private String SurName;
    private Position pos;
    private int Salary;
    private int Year;

    public Person(String name, Position pos, int year, int salary){
        this.SurName = name;
        this.pos = pos;
        this.Salary = salary;
        this.Year = year;
    }

    public Person(){
        this.SurName = "";
        this.pos = Position.employee;
        this.Salary = 0;
        this.Year = 0;
    }

    public int getSalary() {
        return Salary;
    }

    public int getYear() {
        return Year;
    }

    public Position getPos() {
        return pos;
    }

    public String getSurName() {
        return SurName;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setYear(int year) {
        Year = year;
    }

    public static Position getPosition(String str) {
        switch (str){
            case "manager" :
                return Position.manager;
            case "director" :
                return Position.director;
            case "employee" :
                return Position.employee;
            case "lawyer" :
                return Position.lawyer;
            case "accountant" :
                return Position.accountant;
        }
        return Position.employee;
    }

    @Override
    public String toString()
    {
        return "\n" + this.getSurName() + " " + this.getPos() + " year - "
                + this.getYear() + " salary - "
                + this.getSalary();
    }



    /*
    class accessoriesComparatorByPrice implements Comparator<Person> {
        public int compare(Person o1, Person o2){
            if(o1.getSurName().equals(o2.getSurName()))
                return 1;
            else
                return 0;
        }
    }
    */

}
