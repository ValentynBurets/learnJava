package source;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainView {
    private JButton DoButton;
    private JPanel panelMain;
    private JSpinner threadCountSpinner;
    private JCheckBox useSStractCheck;
    private JSpinner sizeSpiner;
    private JFormattedTextField filterTextField;
    private JFormattedTextField PathTextField;
    private JTextArea ThreadInfoTextArea;
    private JButton clearTextBox;
    private JSpinner RefreshTimeSpinner;
    public int threadCount;
    public boolean useSuperstructure;
    public String filter;
    public int minSize;
    public String PathText;
    public int refreshTime;

    public MainView() {

        SpinnerModel value = new SpinnerNumberModel(200, 100, 10000, 100);
        sizeSpiner.setModel(value);
        value = new SpinnerNumberModel(1, 1, 10, 1);
        threadCountSpinner.setModel(value);
        value = new SpinnerNumberModel(1000, 500, 100000, 500);
        RefreshTimeSpinner.setModel(value);

        DoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                threadCount = Integer.parseInt(threadCountSpinner.getValue().toString());
                useSuperstructure = useSStractCheck.isSelected();
                filter = filterTextField.getText();
                minSize = Integer.parseInt(sizeSpiner.getValue().toString());
                PathText = PathTextField.getText();
                refreshTime = Integer.parseInt(RefreshTimeSpinner.getValue().toString());
                JOptionPane.showMessageDialog(null, "Thread Count = " + threadCount +
                        "\n refresh time - " + refreshTime +
                        "\nIs checked " + useSuperstructure + " filter " + filter + "\n PathText = " + PathText);
                try {
                    DoAction(ThreadInfoTextArea, PathText, threadCount,minSize, filter, refreshTime);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                //ThreadInfoTextArea.setText();

            }
        });


        clearTextBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ThreadInfoTextArea.setText(" ");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("App");
        var view = new MainView();
        frame.setContentPane(view.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

/*
        MyThread d = new MyThread("C:/", 100, "filter", 1);

        Multi3 m1 = new Multi3();
        Thread t1 =new Thread(m1);
        t1.start();
*/

        //DoAction(frame, view.PathText, view.threadCount,view.minSize, view.filter, 500);

    }
    //-----------------------------------

    public static void DoAction(JTextArea ThreadInfoTextArea, String PathText, int threadCount, int minSize, String filter, int RefreshTime) throws InterruptedException {

        /*
        Thread t2 = new SampleThread(1);
        t2.start();
        t2.join();
*/

        List<MyThread> ThreadList = new ArrayList<>();

        for(int i = 0; i < threadCount; i++){
            ThreadList.add(new MyThread(PathText, minSize, filter, i + 1));
        }

        long TimeOfWorkStarts = System.currentTimeMillis();

        for(int i = 0; i < threadCount; i++){
            ThreadList.get(i).start();
        }

        Monitor ExpMonitor = new Monitor(ThreadInfoTextArea, ThreadList, RefreshTime);
        Thread ExpMonitorThread = new Thread(ExpMonitor);
        ExpMonitorThread.start();

        for(int i = 0; i < threadCount; i++){
            try {
                ThreadList.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            ExpMonitorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long TimeOfWokEnds = System.currentTimeMillis();
        System.out.println("Time spent: " + (TimeOfWokEnds - TimeOfWorkStarts) + " milliseconds" );
    }


}
