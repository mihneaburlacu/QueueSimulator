package model;

public class Task {

    private int id;
    private int arrivalTime;
    private int serviceTime;
    private int finishTime;

    public Task() {
        super();
    }

    public Task(int id, int arrTime, int servTime, int finishTime) {
        this.id = id;
        this.arrivalTime = arrTime;
        this.serviceTime = servTime;
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public String toString() {
        //return "(" + this.id + ", " + this.arrivalTime + ", " + this.serviceTime + ")\n";
        return "(" + this.id + ", " + this.arrivalTime + ", " + this.serviceTime + ", " + this.finishTime + ")\n";
    }
}
