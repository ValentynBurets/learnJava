
package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
public class ReadFile {
    private File readFile;
    private Scanner reader;
    private Stream fileData=null;
    ReadFile (String fileName) throws FileNotFoundException {
        readFile=new File(fileName);
        reader=new Scanner(readFile);
    }
    public String ReadDataFromFile()throws FileNotFoundException{
        reader=new Scanner(readFile);
        ArrayList<String> inputdata=new ArrayList<String>();
        try {

            while(reader.hasNextLine()){
                inputdata.add(reader.nextLine());
            }
        }catch (Exception exception){

            return null;
        }
        fileData=inputdata.stream();
        return inputdata.toString();
    }
    public void ChangeFile(String filename) throws FileNotFoundException{
        readFile=new File(filename);
        reader=new Scanner(readFile);
    }
}