package schedulerSimulation;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Nikita Pankratov
 */
public class HPF_Nonpreemptive extends Scheduler {

    public HPF_Nonpreemptive(int numOfQuanta) {
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

	while (result.size() < numOfQuanta) {
	    findWaitingProcesses(result.size());
	    ProcUtil.sortByPriority(waitingProcesses);

	    if (!waitingProcesses.isEmpty()) {
		Proc currentProc = waitingProcesses.poll();
		while (currentProc.isDone() != -1 && result.size() < numOfQuanta) {
		    result.add(currentProc.getName());
		    for (Proc proc : waitingProcesses) {
			proc.addWaitingTime();
			if (proc.getWaitingTime() % 5 == 0) {
			    proc.addPriority();
			}
		    }
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
