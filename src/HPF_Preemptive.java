package schedulerSimulation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Nikita Pankratov
 */
public class HPF_Preemptive extends Scheduler {

    public HPF_Preemptive(int numOfQuanta) {
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
	int currentPosition = 0;
	int currentPriority = 1;

	while (result.size() < numOfQuanta) {
	    findWaitingProcesses(result.size());
	    ProcUtil.sortByPriority(waitingProcesses);

	    int processState;
	    if (!waitingProcesses.isEmpty()) {
		if (waitingProcesses.get(0).getPriority() < currentPriority) {
		    currentPosition = 0;
		    currentPriority = waitingProcesses.get(0).getPriority();
		    processState = waitingProcesses.get(0).isDone();
		    result.add(waitingProcesses.get(0).getName());

		    increasePriority(waitingProcesses, currentPosition);

		    if (processState == 1) {
			currentPosition++;
			if (currentPosition == waitingProcesses.size() || currentPriority < waitingProcesses.get(currentPosition).getPriority()) {
			    currentPosition = 0;
			}
		    } else {
			doneProcesses.add(waitingProcesses.remove(currentPosition));
			if (currentPosition < waitingProcesses.size()) {
			    currentPriority = waitingProcesses.get(currentPosition).getPriority();
			} else {
			    currentPosition = 0;
			}
		    }
		} else {
		    processState = waitingProcesses.get(currentPosition).isDone();
		    result.add(waitingProcesses.get(currentPosition).getName());

		    increasePriority(waitingProcesses, currentPosition);

		    if (processState == 1) {
			currentPosition++;
			if (currentPosition == waitingProcesses.size() || currentPriority < waitingProcesses.get(currentPosition).getPriority()) {
			    currentPosition = 0;
			}
		    } else {
			doneProcesses.add(waitingProcesses.remove(currentPosition));
			if (currentPosition < waitingProcesses.size()) {
			    currentPriority = waitingProcesses.get(currentPosition).getPriority();
			} else {
			    currentPosition = 0;
			}
		    }
		}
	    } else if (processes.isEmpty()) {
		return;
	    } else {
		result.add("");
	    }

	}
    }

    private void increasePriority(LinkedList<Proc> waitingProcesses, int currentPosition) {
	for (int i = 0; i < waitingProcesses.size(); i++) {
	    if (i != currentPosition) {
		waitingProcesses.get(i).addWaitingTime();
		if (waitingProcesses.get(i).getWaitingTime() % 5 == 0) {
		    waitingProcesses.get(i).addPriority();
		}
	    }
	}
    }

}
