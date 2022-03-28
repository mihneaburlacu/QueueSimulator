package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import businessLogic.*;

import static java.lang.Thread.sleep;

public class Server implements Runnable{

    private int ID;
    private BlockingQueue<Task> tasksQueue;
    private AtomicInteger waitingPeriod;
    public static double averageWaitingTime;
    //private int capacity;

    public Server() {
        super();
    }

    public Server(int ID, int waitingPer) {
        this.ID = ID;
        this.waitingPeriod = new AtomicInteger(0);
        this.tasksQueue = new ArrayBlockingQueue<Task>(Scheduler.maxTasksPerServer);
    }

    public Server(int ID) {
        this.ID = ID;
        this.waitingPeriod = new AtomicInteger(0);
        this.tasksQueue = new ArrayBlockingQueue<Task>(Scheduler.maxTasksPerServer);
    }

    /*
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    */

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public int getIntWaitingPeriod() {
        return this.waitingPeriod.intValue();
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public BlockingQueue<Task> getTasksQueue() {
        return tasksQueue;
    }

    public void setAverageWaitingTime(double avgWaitingTime) {
        Server.averageWaitingTime = avgWaitingTime;
    }

    public double getAverageWaitingTime() {
        return Server.averageWaitingTime;
    }

    public void addTask(Task t){
        this.tasksQueue.add(t);
        t.setFinishTime(t.getArrivalTime() + t.getServiceTime() + this.waitingPeriod.intValue());
        this.waitingPeriod.set(t.getServiceTime() + this.waitingPeriod.intValue());
    }

    @Override
    public void run() {
        Task auxTask;
        int nrTasks = 0;
        double totalWaitTime = 0;

        while (true) {
            try {
                auxTask = tasksQueue.peek(); //peek() - returneaza head-ul cozii

                if(auxTask != null) {
                    nrTasks = nrTasks + 1;

                    totalWaitTime = totalWaitTime + (auxTask.getFinishTime() - auxTask.getArrivalTime());
                    Server.averageWaitingTime = totalWaitTime / nrTasks;

                    sleep(1000 * auxTask.getServiceTime()); //1000 pentru o secunda

                   this.waitingPeriod.set(this.waitingPeriod.intValue() - auxTask.getServiceTime());
                   tasksQueue.poll(); //poll() - sterge elementul din head
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    public String toString() {
        String result = "";

        result = result + "Server number " + this.ID + " ";
        result = result + "\n";

        for(Task t: tasksQueue) {
            result = result + t.toString();
        }

        return result;
    }
    */
     public String toString() {
         StringBuilder s = new StringBuilder();
         s.append("Server number " + this.ID + "\n");

         if(tasksQueue.size() == 0) {
             s.append("Server closed\n");
         }

         for(Task t : tasksQueue) {
             s.append(t.toString());
         }

         return s.toString();
     }

}
