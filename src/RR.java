package schedulerSimulation;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * @author Nikita Pankratov
 */
public class RR extends Scheduler {

    public static final int TIME_SLICE = 1;

    public RR(int numOfQuanta) {
	super(ProcUtil.generateProcessesByLinkedList(), numOfQuanta);
    }

    @Override
    public void runSimulation() {
	List<Proc> processes = getProcesses();
	List<String> result = getResult();
	LinkedList<Proc> waitingProcesses = getWaitingProcesses();
	LinkedList<Proc> doneProcesses = getDoneProcesses();
	int numOfQuanta = getNumOfQuanta();

	ProcUtil.sortByArrivalTime(processes);
	int currentProcess = 0;

	for (int i = 0; i < numOfQuanta; i++) {
	    findWaitingProcesses(i);

	    if (++currentProcess >= waitingProcesses.size()) {
		currentProcess = 0;
	    }

	    if (!waitingProcesses.isEmpty()) {
		int processState = waitingProcesses.get(currentProcess).isDone();

		if (processState == 1) {
		    result.add(waitingProcesses.get(currentProcess).getName());

		} else if (processState == 0) {
		    result.add(waitingProcesses.get(currentProcess).getName());
		    doneProcesses.add(waitingProcesses.remove(currentProcess));
		}
	    } else if (processes.isEmpty()) {
		return;
	    } else {
		result.add("");
	    }
	}
    }
}
