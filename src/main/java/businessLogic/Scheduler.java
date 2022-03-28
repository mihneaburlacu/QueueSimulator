package businessLogic;

import enums.SelectionPolicy;
import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private ArrayList<Server> serversList;
    private int maxNrServers;
    public static int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler() {
        super();
    }

    public Scheduler(int maxNrServers, int maxTasksPerServer) {
        serversList = new ArrayList<Server>();
        this.maxNrServers = maxNrServers;
        Scheduler.maxTasksPerServer = maxTasksPerServer;

        for(int i = 0; i < maxNrServers; i++) {
            Server newServer = new Server(i + 1);
            serversList.add(newServer);

            Thread thread = new Thread(newServer);
            thread.setName("Server" + i);

            thread.start();
        }

    }

    public ArrayList<Server> getServers() {
        return serversList;
    }

    public void setServers(ArrayList<Server> servers) {
        this.serversList = servers;
    }

    public int getMaxNrServers() {
        return maxNrServers;
    }

    public void setMaxNrServers(int maxNrServers) {
        this.maxNrServers = maxNrServers;
    }

    public int getMaxTasksPerServer() {
        return maxTasksPerServer;
    }

    public void setMaxTasksPerServer(int maxTasksPerServer) {
        this.maxTasksPerServer = maxTasksPerServer;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy changeStrategy(SelectionPolicy policy) {
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }

        return strategy;
    }

    public void dispatchTask(Task t, Strategy s) throws Exception {
        //strategy = new ConcreteStrategyTime();
        s.addTask(serversList, t);
    }

    public int getMaxWaitingTime() {
        int nrM = 0;

        for(Server s : serversList) {
            if(s.getIntWaitingPeriod() > nrM) {
                nrM = s.getIntWaitingPeriod();
            }
        }

        return nrM;
    }
}

