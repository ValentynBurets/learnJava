import com.company.Algo;

import java.util.List;

class AlgoThread extends Thread {

    private static boolean initializationStatus = false;
    private static int minSize = 0;
    private static String filter = " ";
    public static List<String> directoriesPool;
    private static String rootDirectory;

    private int DirectoriesFound;
    private int ThreadNumber;
    private long startTime;

    public static int getMinSize(){
        return AlgoThread.minSize;
    }
    public int getDirectoriesFound(){return this.DirectoriesFound; }
    public static String getFilter(){
        return AlgoThread.filter;
    }
    public int getThreadNumber(){ return this.ThreadNumber; }
    public long getStartTime(){ return this.startTime; }

    public AlgoThread(String path, int minSize, String filter, int ThreadNumber) throws InterruptedException {
        this.ThreadNumber = ThreadNumber;
        DirectoriesFound = 0;
        if( !initializationStatus){
            filter = filter;
            AlgoThread.minSize = minSize;
            initializationStatus = true;
            rootDirectory = path;
            directoriesPool = Algo.AnalyzeDirectory(rootDirectory);
        }
    }

    @Override
    public void run() {
        this.startTime = System.currentTimeMillis();
        String path = "";

        while (true){
            synchronized (AlgoThread.directoriesPool){
                if(AlgoThread.directoriesPool.size() < 1) break;
                path = AlgoThread.directoriesPool.get(0);
                AlgoThread.directoriesPool.remove(path);
            }
            List<String> insertedDirectoriesList = Algo.AnalyzeDirectory(path);
            if( ! insertedDirectoriesList.isEmpty()){
                AlgoThread.directoriesPool.addAll(insertedDirectoriesList);
                this.DirectoriesFound += insertedDirectoriesList.size();
            }
        }
    }

}
