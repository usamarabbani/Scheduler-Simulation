package schedulerSimulation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * @author Nikita Pankratov
 */
public abstract class Scheduler {

    private List<Proc> processes;
    private LinkedList<Proc> waitingProcesses;
    private LinkedList<Proc> doneProcesses;

    private List<String> result;
    private int numOfQuanta;

    public Scheduler(List<Proc> processes, int numOfQuanta) {
	this.processes = processes;
	this.waitingProcesses = new LinkedList<>();
	this.doneProcesses = new LinkedList<>();

	this.result = new ArrayList<>();
	this.numOfQuanta = numOfQuanta;

    }

    public List<Proc> getProcesses() {
	return processes;
    }

    public LinkedList<Proc> getWaitingProcesses() {
	return waitingProcesses;
    }

    public LinkedList<Proc> getDoneProcesses() {
	return doneProcesses;
    }

    public List<String> getResult() {
	return result;
    }

    public int getNumOfQuanta() {
	return numOfQuanta;
    }

    public abstract void runSimulation();

    public void findWaitingProcesses(int currentQuanta) {
	List<Proc> processes = getProcesses();
	while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= currentQuanta) {
	    waitingProcesses.add(processes.remove(0));
	}
    }

    public void printPreSimulation() {
	for (Proc process : processes) {
	    System.out.println(process.toString());
	}
    }

    public void printResult() {
	StringBuffer buffer = new StringBuffer();
	for (int i = 0; i < 15; i++) {
	    buffer.append(String.format("%-5d", i));
	}
	buffer.append("\n");
	buffer.append("-------------------------------------"
		+ "------------------------------------\n");
	int counter = 0;
	for (String t : result) {
	    if (counter == 15) {
		buffer.append("\n");
		counter = 0;
	    }
	    buffer.append(String.format("%-4s", t) + " ");
	    counter++;
	}
	System.out.println(buffer.toString().trim());
    }

    public float calResponseTime() {
	float avgResponseTime = 0;
	float totalWaitingTime = 0;
	int processNum = doneProcesses.size();

	for (Proc process : doneProcesses) {
	    float started = result.indexOf(process.getName());
	    totalWaitingTime += started - process.getArrivalTime();
	}

	for (Proc process : waitingProcesses) {
	    float isStarted = result.indexOf(process.getName());
	    if (isStarted != -1) {
		processNum++;
		totalWaitingTime += isStarted - process.getArrivalTime();
	    }
	}
	avgResponseTime = totalWaitingTime / processNum;
	return avgResponseTime;
    }

    public float calWaitingTime() {
	float totalWaitingTime = 0;

	for (Proc process : doneProcesses) {
	    float started = result.indexOf(process.getName());
	    float finished = result.lastIndexOf(process.getName());
	    float inTime = (float) (finished - started - Math.ceil(process.getExpectedTime()) + 1);
	    float preTime = started - process.getArrivalTime();
	    totalWaitingTime += inTime + preTime;
	}
	return totalWaitingTime / doneProcesses.size();
    }

    public float calTurnaroundTime() {
	float totalTurnaroundTime = 0;
	for (Proc process : doneProcesses) {
	    float finished = result.lastIndexOf(process.getName());
	    totalTurnaroundTime += finished + 1 - process.getArrivalTime();
	}

	return totalTurnaroundTime / doneProcesses.size();
    }

    public float calThroughOutput() {
	return doneProcesses.size();
    }
}
