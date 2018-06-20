package schedulerSimulation;

/**
 *
 * @author Nikita Pankratov
 */
public class Main {

    public static final int NUM_OF_QUANTA = 100;
    public static final int NUM_RUNS = 30;

    public static void main(String[] args) {
	System.out.println("The number of processes created: " + ProcUtil.NUM_OF_PROCESSES);
	System.out.println("The number of runs: " + NUM_RUNS);
	System.out.println("\nFirst come first serve");
	testRun(ScheduleCode.FCFS);
	System.out.println("\nShortest job first");
	testRun(ScheduleCode.SJF);
	System.out.println("\nShortest remaining job");
	testRun(ScheduleCode.SRT);
	System.out.println("\nRound robin");
	testRun(ScheduleCode.RR);
	System.out.println("\nHPF non preemptive");
	testRun(ScheduleCode.HPF_NONPREEMPTIVE);
	System.out.println("\nHPF preemptive");
	testRun(ScheduleCode.HPF_PREEMPTIVE);
    }

    public static void testRun(ScheduleCode code) {
	switch (code) {
	    case FCFS:
			runFCFS();
			break;
	    case SJF:
			runSJF();
			break;
	    case SRT:
			runSRT();
			break;
	    case RR:
			runRR();
			break;
	    case HPF_NONPREEMPTIVE:
			runHPF_NONPREEMPTIVE();
			break;
	    case HPF_PREEMPTIVE:
			runHPF_PREEMPTIVE();
			break;
	}
    }

    public static void runFCFS() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;


	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new FCFS(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}

	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }

    public static void runSJF() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;

	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new SJF(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}
	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }

    public static void runSRT() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;

	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new SRT(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}
	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }

    public static void runRR() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;

	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new RR(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}
	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }

    public static void runHPF_NONPREEMPTIVE() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;

	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new HPF_Nonpreemptive(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}
	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }

    public static void runHPF_PREEMPTIVE() {
	float avgResTime = 0;
	float avgThroughOutput = 0;
	float avgTurnaroundTime = 0;
	float avgWaitingTime = 0;

	for (int i = 0; i < NUM_RUNS; i++) {
	    Scheduler scheduler = new HPF_Preemptive(NUM_OF_QUANTA);
	    scheduler.runSimulation();
	    avgResTime += scheduler.calResponseTime();
	    avgThroughOutput += scheduler.calThroughOutput();
	    avgTurnaroundTime += scheduler.calTurnaroundTime();
	    avgWaitingTime += scheduler.calWaitingTime();
	}
	System.out.println("Average response time: " + (avgResTime / NUM_RUNS));
	System.out.println("Average throughput: " + (avgThroughOutput / NUM_RUNS));
	System.out.println("Average turnaround time: " + (avgTurnaroundTime / NUM_RUNS));
	System.out.println("Average waiting time: " + (avgWaitingTime / NUM_RUNS));
    }
}
