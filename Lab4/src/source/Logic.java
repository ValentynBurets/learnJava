package source;

import source.MyThread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Logic{

    /*
    public static double getFileSizeKiloBytes(File file){
        return (int) file.length();
    }
     */

    public static synchronized List<String> AnalyzeDirectory(String path){
        File directory = new File(path);
        List<String> insertedDirectoriesList = new ArrayList<>();
        int minFileSize = MyThread.getMinSize();
        String filter = MyThread.getFilter();

        if(directory.isDirectory()){
            String[] content = directory.list();

            if(content != null && content.length > 0){
                int NumbOfFiles = 0;
                int numberOfFilesWithParam = 0;
                for(String eachFile : content){
                    String MyPath = path + "\\" + eachFile;
                    File file = new File(MyPath);
                    if(file.isDirectory()){
                        insertedDirectoriesList.add(MyPath);

                    }
                    else{
                        if((int) file.length() > minFileSize){
                            NumbOfFiles++;
                        }
                        if(eachFile.toLowerCase().matches(filter)){
                            numberOfFilesWithParam++;
                        }
                    }
                }
                synchronized (System.out){
                    System.out.println(path);
                    System.out.println("Number of files with size > " + minFileSize + " is " + NumbOfFiles);
                    System.out.println("Number of files which matches param " + filter + " is " + numberOfFilesWithParam + "\n");

                }
            }
            else {
                synchronized (System.out){
                    System.out.println(path);
                    System.out.println("Folder is empty\n");
                }
            }
        }
        return insertedDirectoriesList;
    }
}