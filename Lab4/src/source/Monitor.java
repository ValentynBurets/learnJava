package source;

import source.MyThread;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


class Monitor implements Runnable {
    private List<MyThread> threads;
    private int refreshTime;
    private JTextArea ThreadInfoTextArea;

    public Monitor(JTextArea ThreadInfoTextArea, List<MyThread> threads, int refreshTime){
        this.refreshTime = refreshTime;
        this.threads = threads;
        this.ThreadInfoTextArea = ThreadInfoTextArea;
    }

    @Override
    public void run() {
        boolean ContinueCircle = true;

        while (ContinueCircle)
        {
            ContinueCircle = threadExist(threads);

            for (MyThread itVar : threads) {
                StartMonitoring(itVar);
            }
        }

    }

    private void StartMonitoring(MyThread thread){

        //while(thread.isAlive()){

            ThreadInfoTextArea.append(this.GetStatus(thread));

            try {
                Thread.sleep(refreshTime);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
        
    }



    /*
    @Override
    public void run() {
        int i = 1;
        for (MyThread itVar : threads)
        {
            StartMonitoring(itVar, i);
            i++;
        }

    }

    private void StartMonitoring(MyThread thread, int i){
        String strInfo = new String();
        while(thread.isAlive()){
            strInfo = this.GetStatus(thread, i);
            ThreadInfoTextArea.append(strInfo);
            try {
                Thread.sleep(refreshTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        strInfo = this.GetStatus(thread, i);
        ThreadInfoTextArea.append("\n" + i + " thread has finished work");
    }

     */

    private String GetStatus(MyThread thread){
        String Status = thread.getThreadNumber() + " thread is " + thread.getState().toString() +
                "\nnDirectories: " + thread.getDirectoriesFound() + "\n" +
                "Time: " + (System.currentTimeMillis() - thread.getStartTime()) + "\n";
        return Status;
    }

    private boolean threadExist(List<MyThread> threads) {
        for(MyThread item : threads) {
            if(item.isAlive())
                return true;
        }
        return false;
    }
}
