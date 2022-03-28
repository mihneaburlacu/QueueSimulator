package businessLogic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    public ConcreteStrategyTime() {
        super();
    }

    @Override
    public void addTask(List<Server> servers, Task t) throws Exception{
        Server minServer = null;
        int nrMin = Integer.MAX_VALUE;

        for(Server s : servers) {
            if(s.getTasksQueue().size() < Scheduler.maxTasksPerServer) {
                if(s.getIntWaitingPeriod() < nrMin) {
                    minServer = s;
                    nrMin = s.getIntWaitingPeriod();
                }
            }
        }

        if(nrMin == Integer.MAX_VALUE) {
            throw new Exception("All servers(queues) are full!");
        }
        else {
            minServer.addTask(t);
        }

    }
}
