package schedulerSimulation;


import com.sun.org.apache.xerces.internal.impl.Constants;

/**
 * @author Nikita Pankratov
 */
public class Proc {

    public static final int HIGHEST_PRIORITY = 1;

    private String name;
    private float arrivalTime;
    private float expectedTime;
    private float remainingTime;

    private int priority;
    private int waitingTime;

    public Proc(String name) {
	this.name = name;
	setRandomValues();
	this.remainingTime = this.expectedTime;
	this.waitingTime = 0;
    }

    private void setRandomValues() {
	this.arrivalTime = Float.valueOf(String.format("%.2f", (Math.random() * 100)));
	double num = (Math.random() + 0.01) * 10;
	this.expectedTime = (float) (num - (num % 0.1));
	this.priority = (int) (Math.random() / 0.25) + 1;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public float getArrivalTime() {
	return arrivalTime;
    }

    public void setArrivalTime(float arrivalTime) {
	this.arrivalTime = arrivalTime;
    }

    public float getExpectedTime() {
	return expectedTime;
    }

    public void setExpectedTime(float expectedTime) {
	this.expectedTime = expectedTime;
    }

    public float getRemainingTime() {
	return remainingTime;
    }

    public void setRemainingTime(float remainingTime) {
	this.remainingTime = remainingTime;
    }

    public int getPriority() {
	return priority;
    }

    public void setPriority(int priority) {
	this.priority = priority;
    }

    public int getWaitingTime() {
	return waitingTime;
    }

    public void addWaitingTime() {
	this.waitingTime++;
    }

    public void addPriority() {
	if (this.priority > HIGHEST_PRIORITY) {
	    this.priority--;
	}
    }

    public int isDone() {
	if (this.remainingTime > 1) {
	    this.remainingTime -= 1;
	    return 1;
	} else if (this.remainingTime > 0) {
	    this.remainingTime = 0;
	    return 0;
	} else {
	    return -1;
	}
    }

    public String toString() {
	return String.format("%-4s", this.name) + " arrives at "
		+ String.format("%5.2f", this.arrivalTime) + ", "
		+ this.expectedTime + ", " + this.priority + ", " + String.format("%5.2f, ", this.remainingTime);
    }
}
