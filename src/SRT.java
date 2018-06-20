package schedulerSimulation;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @author Nikita Pankratov
 */
public class SRT extends Scheduler {

    public SRT(int numOfQuanta) {
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

	for (int i = 0; i < numOfQuanta; i++) {
	    findWaitingProcesses(i);
	    ProcUtil.sortByRemainingTime(waitingProcesses);

	    if (!waitingProcesses.isEmpty()) {
		int processState = waitingProcesses.get(0).isDone();

		if (processState == 1) {
		    result.add(waitingProcesses.get(0).getName());
		} else if (processState == 0) {
		    result.add(waitingProcesses.get(0).getName());
		    doneProcesses.add(waitingProcesses.poll());
		}
	    } else if (processes.isEmpty()) {
		return;
	    } else {
		result.add("");
	    }
	}
    }

}
