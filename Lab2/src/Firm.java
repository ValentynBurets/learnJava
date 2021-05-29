import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Firm {
    public static void main(String[] args) throws IOException {
        DataFirm firm = new DataFirm();

            System.out.println("Write from file \n Enter file name: ");

            String[] filePath = new String[2];
            filePath[0] = "firm.txt";
            filePath[1] = "firm1.txt";

        System.out.println("Read from files: " );
        for(var item : filePath){
            System.out.println(item);
        }


            for(int i = 0; i < filePath.length; i ++)
            {
                try {
                    File myObj = new File(filePath[i]);
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        if(data.equals(""))
                            break;
                        String[] parts = data.split(" ");

                        Person person = new Person(parts[0], Person.getPosition(parts[1]), Integer.parseInt(parts[2]),  Integer.parseInt(parts[3]));

                        firm.addPerson(person);

                        System.out.println(parts[0] + "  " + parts[1] + "  " + parts[2] + "  " + parts[3] + "\n");
                    }

                    myReader.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                       e.printStackTrace();
                    }
                }


        System.out.println("\n Delete persons who age less then - \n");

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);

        int age = in.nextInt();

        firm.deletePersonByAge(age);
        System.out.println(firm.toString());

        firm.CreateMapByPosition();


        Map<Position, Person> youngestPersons= firm.GetYoungestPersonMap();

        System.out.println("\n youngest Persons for all positions\n");
        for(var item: youngestPersons.entrySet())
        {
            System.out.println(item.getKey() + " - " + youngestPersons.get(item.getKey()).toString());
        }

        Map<Position, Person> oldestPersons = firm.GetOldestPersonMap();

        System.out.println("\n oldest Persons for all positions\n");
        for(var item: oldestPersons.entrySet())
        {
            System.out.println(item.getKey() + " - " + oldestPersons.get(item.getKey()).toString());
        }


        firm.CreateMapBySalary();

        System.out.println("\n Small salary is < 800\n medium 800 < salary < 2000\nbig salary > 2000\n ");

        Map<Salary, Person> lowestSalaryPersons= firm.GetLowestSalaryPersonMap();

        System.out.println("\n lowest salary for all positions\n");
        for(var item: lowestSalaryPersons.entrySet())
        {
            System.out.println(item.getKey() + " - " + lowestSalaryPersons.get(item.getKey()).toString());
        }


        Map<Salary, Person> highestSalaryPersons= firm.GetHighestSalaryPersonMap();

        System.out.println("\n highest salary for all positions\n");
        for(var item: highestSalaryPersons.entrySet())
        {
            System.out.println(item.getKey() + " - " + highestSalaryPersons.get(item.getKey()).toString());
        }

    }
}