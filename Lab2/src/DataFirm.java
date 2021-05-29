import java.util.*;

public class DataFirm {
    public ArrayList<Person> personList;
    public Map<Position, List<Person>> personMap;
    public Map<Salary, List<Person>> salaryMap;
    private SortType type;

    public DataFirm()
    {
        personList = new ArrayList<Person>();
        personMap = new HashMap<Position, List<Person>>();
        salaryMap = new HashMap<Salary, List<Person>>();
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }
    public void addPerson(Person person){
        if(this.personList == null)
            this.personList = new ArrayList<Person>();

        if(person != null) {
            if (!this.isExist(person)) {
                this.personList.add(person);
            }
        }
    }

    public boolean isExist(Person person)
    {
        for(var item : personList) {
            if (item.getSurName().equals(person.getSurName())) {
                return true;
            }
        }

        return false;
    }

    public void deletePersonByAge(int age)
    {
        /*
        for(Person item : this.personList)
        {
            if(item.getYear() < age) {
                this.personList.remove(item);
            }
        }
         */


        ArrayList<Person> newList = new ArrayList<Person>();

        for(Person item : this.personList)
        {
            if(item.getYear() > age) {
                newList.add(item);
                //this.personList.remove(item);
            }
        }
        personList.clear();
        personList.addAll(newList);
    }

    @Override
    public String toString()
    {
        String str = new String();

        for(var item : personList)
        {
            str+= item.toString();
        }
        return  str;
    }

    public List<Person> getMap(Position pos)
    {
        return personMap.get(pos);
    }

    public void CreateMapByPosition()
    {
        for(var posItem : Position.values())
        {
            List<Person> persons = new ArrayList<>();
            for(var listItem : personList)
            {
                if(listItem.getPos().equals(posItem)){
                    persons.add(listItem);
                }
            }

            personMap.put(posItem, persons);
        }
    }

    public void CreateMapBySalary() {
        for (var posItem : Salary.values()) {
            Salary s = Salary.medium;
            List<Person> persons = new ArrayList<>();
            for (var listItem : personList) {
                Person person = new Person();
                if (listItem.getSalary() < 800) {
                    s = Salary.small;
                }
                if (listItem.getSalary() > 2000) {
                    s = Salary.big;
                }
                if (listItem.getSalary() < 2000 && listItem.getSalary() > 800) {
                    s = Salary.medium;
                }
                if (posItem.equals(s)) {
                    persons.add(listItem);
                }
            }

            salaryMap.put(posItem, persons);
        }
    }

    public String ShowMap(Position pos){
        String str = new String();

        for(var item : Position.values())
        {
            List<Person> personList = personMap.get(item);
            for(var listItem : personList){
                str += listItem.toString() + "\n";
            }
            str += "  \n";
        }
        return str;
    }

    public Person getYoungestPerson(List<Person> persons)
    {
        type = SortType.increasing;
        Collections.sort(persons, new ComparatorByAge());
        return persons.get(0);
    }


    public Person GetOldestPerson(List<Person> persons)
    {
        type = SortType.decreasing;
        Collections.sort(persons, new ComparatorByAge());
        return persons.get(0);
    }



    public Map<Position, Person> GetYoungestPersonMap()
    {
        Map<Position, Person> retMap = new HashMap<Position, Person>();
        for(var item : personMap.entrySet())
        {
            retMap.put(item.getKey(), getYoungestPerson(item.getValue()));
        }
        return retMap;
    }

    public Map<Position, Person> GetOldestPersonMap()
    {
        Map<Position, Person> retMap = new HashMap<Position, Person>();
        for(var item : personMap.entrySet())
        {
            retMap.put(item.getKey(), GetOldestPerson(item.getValue()));
        }
        return retMap;
    }

//-------------------------------------------------------------------
    public Person GetLowestSalaryPerson(List<Person> persons)
    {
        type = SortType.decreasing;
        Collections.sort(persons, new ComparatorBySalary());
        return persons.get(0);
    }


    public Map<Salary, Person> GetLowestSalaryPersonMap()
    {
        Map<Salary, Person> retMap = new HashMap<Salary, Person>();
        for(var item : salaryMap.entrySet())
        {
            retMap.put(item.getKey(), GetLowestSalaryPerson(item.getValue()));
        }
        return retMap;
    }

    public Person GetHighestSalaryPerson(List<Person> persons)
    {
        type = SortType.increasing;
        Collections.sort(persons, new ComparatorBySalary());
        return persons.get(0);
    }


    public Map<Salary, Person> GetHighestSalaryPersonMap()
    {
        Map<Salary, Person> retMap = new HashMap<Salary, Person>();
        for(var item : salaryMap.entrySet())
        {
            retMap.put(item.getKey(), GetHighestSalaryPerson(item.getValue()));
        }
        return retMap;
    }

    //inner class
    class ComparatorByAge implements Comparator<Person> {
        public int compare(Person o1, Person o2){
            int mult = 1;
            if(type == SortType.increasing)
                mult = -1;
            double  sub = o1.getYear() - o2.getYear();
            if(sub > 0)
                return 1 * mult;
            if(sub < 0)
                return -1 * mult;
            return 0;
        }
    }


    //inner class
    class ComparatorBySalary implements Comparator<Person> {
        public int compare(Person o1, Person o2){
            int mult = 1;
            if(type == SortType.increasing)
                mult = -1;
            double  sub = o1.getSalary() - o2.getSalary();
            if(sub > 0)
                return 1 * mult;
            if(sub < 0)
                return -1 * mult;
            return 0;
        }
    }

}
