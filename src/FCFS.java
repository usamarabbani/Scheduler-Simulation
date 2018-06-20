package schedulerSimulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nikita Pankratov
 */
public class FCFS extends Scheduler {

    public FCFS(int numOfQuanta) {
	super(ProcUtil.generateProcessesByArrayList(), numOfQuanta);
    }

    @Override
    public void runSimulation() {
	List<Proc> processes = getProcesses();
	List<String> result = getResult();
	LinkedList<Proc> waitingProcesses = getWaitingProcesses();
	LinkedList<Proc> doneProcesses = getDoneProcesses();
	int numOfQuanta = getNumOfQuanta();

	ProcUtil.sortByArrivalTime(processes);

	while (result.size() < numOfQuanta) {
	    findWaitingProcesses(result.size());

	    if (!waitingProcesses.isEmpty()) {
		Proc currentProc = waitingProcesses.poll();
		while (currentProc.isDone() != -1 && result.size() < numOfQuanta) {
		    result.add(currentProc.getName());
		}
		doneProcesses.add(currentProc);
	    } else if (processes.isEmpty()) {
		return;
	    } else {
		result.add("");
	    }
	}
    }

}
