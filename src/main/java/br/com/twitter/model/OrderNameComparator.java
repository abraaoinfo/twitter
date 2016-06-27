package br.com.twitter.model;

import java.util.Comparator;

import twitter4j.Status;

public class OrderNameComparator implements Comparator<Status> {

	public int compare(Status o1, Status o2) {
		
		 
		
		return o1.getUser().getName().compareToIgnoreCase(o2.getUser().getName());
	}

}
