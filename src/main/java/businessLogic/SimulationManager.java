package businessLogic;

import enums.SelectionPolicy;
import model.Server;
import model.Task;
import view.View;
import view.ViewText;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SimulationManager implements Runnable{
    private static int timeLimit;
    private static int maxProcessingTime;
    private static int minProcessingTime;
    public static int numberOfServers;
    public static int numberOfTasks;
    private static int minArrTime;
    private static int maxArrTime;
    private static SelectionPolicy selectionPolicy;

    private Scheduler scheduler;
    private static View view;
    private static ViewText viewText;
    private static ArrayList<Task> generatedTasks = new ArrayList<Task>();

    private static DecimalFormat df = new DecimalFormat(".##");

    public SimulationManager() {
        this.scheduler = new Scheduler(this.numberOfServers, Scheduler.maxTasksPerServer);
        //SimulationManager.view = new View();
    }

    public static int getTimeLimit() {
        return timeLimit;
    }

    public static void setTimeLimit(int timeLimit) {
        SimulationManager.timeLimit = timeLimit;
    }

    public static int getMaxProcessingTime() {
        return maxProcessingTime;
    }

    public static void setMaxProcessingTime(int maxProcessingTime) {
        SimulationManager.maxProcessingTime = maxProcessingTime;
    }

    public static int getMinProcessingTime() {
        return minProcessingTime;
    }

    public static void setMinProcessingTime(int minProcessingTime) {
        SimulationManager.minProcessingTime = minProcessingTime;
    }

    public static int getMinArrTime() {
        return minArrTime;
    }

    public static void setMinArrTime(int minArrTime) {
        SimulationManager.minArrTime = minArrTime;
    }

    public static int getMaxArrTime() {
        return maxArrTime;
    }

    public static void setMaxArrTime(int maxArrTime) {
        SimulationManager.maxArrTime = maxArrTime;
    }

    public static ArrayList<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public static void setGeneratedTasks(ArrayList<Task> generatedTasks) {
        SimulationManager.generatedTasks = generatedTasks;
    }

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public static void generateTask(int nr) throws Exception {
        int arrivalTime;
        int serviceTime;

        for(int i = 0; i < nr; i++) {
            //generam random timpul de service
            if(maxProcessingTime < minProcessingTime) {
                throw new Exception("Min processing time is greater than Max processing time");
            }
            serviceTime = new Random().nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;

            //generam random timpul de sosire
            if(maxArrTime < minArrTime) {
                throw new Exception("Min arrival time is greater than Max arrival time");
            }
            arrivalTime = new Random().nextInt(maxArrTime - minArrTime + 1) + minArrTime;

            Task newTask = new Task(i, arrivalTime, serviceTime, 0);
            generatedTasks.add(newTask);
        }
    }

    @Override
    public void run() {
        String result = "";

        int maxNrWaitingTask = 0;
        int nrWaitingTask = 0;
        int servedTask = 0;
        int currTime = -1;
        int timeMax = 0;

        while(currTime <= SimulationManager.timeLimit) {
            if(currTime != -1 && currTime <= SimulationManager.timeLimit) {
                result = result + "Time " + currTime + ":\n";
                //System.out.println("Moment " + currTime +":");
            }

            Iterator<Task> it = SimulationManager.generatedTasks.iterator();

            while(it.hasNext()) {
                Task t = it.next();
                try {
                    if(t.getArrivalTime() == currTime) {
                        Strategy strategy = scheduler.changeStrategy(SimulationManager.selectionPolicy);
                        scheduler.dispatchTask(t, strategy);
                        servedTask++;
                        it.remove();
                        /*
                        if(servedTask == numberOfTasks) {
                            timeLimit = currTime + scheduler.getMaxWaitingTime();
                        }
                        */
                    }
                } catch(Exception exp) {
                    if(exp.getMessage().compareTo("All servers(queues) are full!") == 0) {
                        view.setFinalSecondLabelText(exp.getMessage());
                    }
                }
            }

            nrWaitingTask = 0;

            for(Server s : scheduler.getServers()) {
                nrWaitingTask = nrWaitingTask + s.getTasksQueue().size();
                if(currTime != -1) {
                    result = result + s.toString();
                    //System.out.print(s.toString());
                }
            }

            if(nrWaitingTask > maxNrWaitingTask) {
                maxNrWaitingTask = nrWaitingTask;
                timeMax = currTime;
            }

            viewText.setText(result);

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println("Thread intrerupt!");
            }

            currTime++;

        }

        double avgWaitTime = 0;

        for(Server s: scheduler.getServers()) {
            avgWaitTime = avgWaitTime + s.getAverageWaitingTime();
        }

        String resultSecond = "";
        String resultThird = "";
        resultSecond = resultSecond + "Average waiting time: " + df.format(avgWaitTime / numberOfServers) + "\n";
        resultThird = resultThird + "Maximum number of waiting tasks (clients): " + maxNrWaitingTask + " -> moment: " + timeMax + "\n";
        view.setFinalLabelText(resultThird);
        view.setFinalSecondLabelText(resultSecond);
        //System.out.println();
        //System.out.println(resultSecond);
        //System.out.println(resultThird);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
            writer.write(result);
            /*
            writer.append("\n");
            writer.append(resultSecond);
            writer.append("\n");
            writer.append(resultThird);
            writer.append("\n");
            */
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void controller() {

        SimulationManager.view.addSendListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimulationManager.view.setFinalLabelText("");
                SimulationManager.view.setFinalSecondLabelText("");

                SimulationManager.timeLimit = Integer.parseInt(SimulationManager.view.getTimeLimitTextField());
                SimulationManager.maxProcessingTime = Integer.parseInt(SimulationManager.view.getMaxProcTimeTextField());
                SimulationManager.minProcessingTime = Integer.parseInt(SimulationManager.view.getMinProcTimeTextField());
                SimulationManager.numberOfTasks = Integer.parseInt(SimulationManager.view.getNrClientsTextField());
                SimulationManager.numberOfServers = Integer.parseInt(SimulationManager.view.getNrServersTextField());
                SimulationManager.minArrTime = Integer.parseInt(SimulationManager.view.getMinArrivalTimeTextField());
                SimulationManager.maxArrTime = Integer.parseInt(SimulationManager.view.getMaxArrivalTimeTextField());
                SimulationManager.selectionPolicy = SimulationManager.view.getSelecPolicyComboBox();
                Scheduler.maxTasksPerServer = SimulationManager.numberOfTasks;

                viewText.setVisible(true);

                try{
                    generateTask(numberOfTasks);
                }catch(Exception exp){
                    exp.printStackTrace();
                }

                if(generatedTasks != null) {
                    SimulationManager sim = new SimulationManager();
                    Thread thread = new Thread(sim);
                    thread.start();
                }
                else {
                    SimulationManager.view.setFinalLabelText("Invalid values!");
                }
            }
        });
        /*
        try{
            generateTask(numberOfTasks);
        }catch(Exception exp){
            exp.printStackTrace();
        }

        if(generatedTasks != null) {
            SimulationManager sim = new SimulationManager();
            Thread thread = new Thread(sim);
            thread.start();
        }
        else {
            SimulationManager.view.setFinalLabelText("Invalid values!");
        }

         */
    }

    public static void main(String[] args) {
        SimulationManager.view = new View();
        viewText = new ViewText();
        viewText.setVisible(false);
        SimulationManager.controller();
        SimulationManager.view.setVisible(true);
    }
}
