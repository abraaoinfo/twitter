package br.com.twitter.model;

import java.util.ArrayList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Teste {
	
	
	static void  setup() throws TwitterException {

		  

		 Twitter twitter = Model.conexao();
		  
		  Query query = new Query("#java");
			 query.setSince("2016-05-20");
			 query.setUntil("2016-05-25");
			 query.count(100);

		  long lastID = Long.MAX_VALUE;
		  QueryResult result = twitter.search(query);
		  List<Status> tweets =  result.getTweets() ;
		  ArrayList<Status> tweetsAll = new ArrayList<Status>();
		  while (tweets.size () > 0) {
		    
		    try {
		
		      for (Status t: tweets) 
		        if(t.getId() < lastID) 
		        	lastID = t.getId();
		      
		      QueryResult result1 = twitter.search(query);
		      tweets = result1.getTweets();
		      tweetsAll.addAll(result1.getTweets());

		    }

		    catch (TwitterException te) {
		     System.out.println("Couldn't connect: " + te);
		    }; 
		    query.setMaxId(lastID-1);
		  }

		  for (int i = 0; i < tweets.size(); i++) {
		    Status t = (Status) tweets.get(i);

		    GeoLocation loc = t.getGeoLocation();

		    String user = t.getUser().getScreenName();
		    String msg = t.getText();
		    String time = "";
		    if (loc!=null) {
		      Double lat = t.getGeoLocation().getLatitude();
		      Double lon = t.getGeoLocation().getLongitude();
		      System.out.println(i + " USER: " + user + " date: " + t.getCreatedAt() + " located at " + lat + ", " + lon);
		    } 
		    else 
		    	System.out.println(i + " USER: " + user + " date: " + t.getCreatedAt());
		  }
		}
	
	public static void main(String[] args) throws TwitterException {
		
		setup();
		
	}

}
