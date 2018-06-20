package schedulerSimulation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import sun.misc.FpUtils;

/**
 * @author Nikita Pankratov
 */
public class ProcUtil {

    public static final int NUM_OF_PROCESSES = 1000;
    private static Comparator<Proc> arrivalC;
    private static Comparator<Proc> expectedTimeC;
    private static Comparator<Proc> priorityC;
    private static Comparator<Proc> remainingTimeC;

    static {
	arrivalC = new Comparator<Proc>() {

	    @Override
	    public int compare(Proc o1, Proc o2) {
		return Double.compare(o1.getArrivalTime(), o2.getArrivalTime());
	    }
	};

	expectedTimeC = new Comparator<Proc>() {

	    @Override
	    public int compare(Proc o1, Proc o2) {
		return Double.compare(o1.getExpectedTime(), o2.getExpectedTime());
	    }
	};

	priorityC = new Comparator<Proc>() {

	    @Override
	    public int compare(Proc o1, Proc o2) {
		return Double.compare(o1.getPriority(), o2.getPriority());
	    }
	};

	remainingTimeC = new Comparator<Proc>() {

	    @Override
	    public int compare(Proc o1, Proc o2) {
		return Double.compare(o1.getRemainingTime(), o2.getRemainingTime());
	    }
	};
    }

    public static ArrayList<Proc> generateProcessesByArrayList() {
	ArrayList<Proc> arr = new ArrayList<>();
	for (int i = 1; i <= NUM_OF_PROCESSES; i++) {
	    arr.add(new Proc("P" + i));
	}
	return arr;
    }

    public static LinkedList<Proc> generateProcessesByLinkedList() {
	LinkedList<Proc> linkedList = new LinkedList<>();
	for (int i = 1; i <= NUM_OF_PROCESSES; i++) {
	    linkedList.add(new Proc("P" + i));
	}
	return linkedList;
    }

    public static void sortByArrivalTime(List<Proc> arr) {
	Collections.sort(arr, arrivalC);
    }

    public static void sortByExpectedTime(List<Proc> arr) {
	Collections.sort(arr, expectedTimeC);
    }

    public static void sortByPriority(List<Proc> arr) {
	Collections.sort(arr, priorityC);
    }

    public static void sortByRemainingTime(List<Proc> arr) {
	Collections.sort(arr, remainingTimeC);
    }
}
