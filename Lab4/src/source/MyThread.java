package source;

import java.util.List;

class Multi3 implements Runnable {
    public void run() {
        System.out.println("thread is running...");
    }
}

class SampleThread extends Thread {
    public int processingCount = 0;

    SampleThread(int processingCount) {
        this.processingCount = processingCount;
    }

    @Override
    public void run() {
        while (processingCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            processingCount--;
        }

    }
}

class MyThread extends Thread {

    private static int minSize = 0;
    private static String filter = " ";
    public static List<String> directoriesPool;
    private static String rootDirectory;

    private int DirectoriesFound;
    private int ThreadNumber;
    private long startTime;

    MyThread(){
        this.ThreadNumber = 0;
        DirectoriesFound = 0;
        filter = " ";
        MyThread.minSize = 100;
        rootDirectory = "C:/";
        directoriesPool = Logic.AnalyzeDirectory(rootDirectory);
    }

    MyThread(String path, int minSize, String filter, int ThreadNumber){
        this.ThreadNumber = ThreadNumber;
        this.DirectoriesFound = 0;
        this.filter = filter;
        this.minSize = minSize;
        this.rootDirectory = path;
        this.directoriesPool = Logic.AnalyzeDirectory(rootDirectory);

    }

    @Override
    public void run() {
        this.startTime = System.currentTimeMillis();
        String path = "";

        while (true){
            synchronized (MyThread.directoriesPool){
                if(MyThread.directoriesPool.size() < 1) break;
                path = MyThread.directoriesPool.get(0);
                MyThread.directoriesPool.remove(path);
            }

            List<String> insertedDirectoriesList = Logic.AnalyzeDirectory(path);
            if( ! insertedDirectoriesList.isEmpty()){
                MyThread.directoriesPool.addAll(insertedDirectoriesList);
                this.DirectoriesFound += insertedDirectoriesList.size();
            }
        }
    }


    public static int getMinSize(){
        return MyThread.minSize;
    }
    public int getDirectoriesFound(){return this.DirectoriesFound; }
    public static String getFilter(){
        return MyThread.filter;
    }
    public int getThreadNumber(){ return this.ThreadNumber; }
    public long getStartTime(){ return this.startTime; }

}