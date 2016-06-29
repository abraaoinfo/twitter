package br.com.twitter.main;

import br.com.twitter.core.TwitterSearchLastweek;
import twitter4j.TwitterException;

public class Execute {
	
	
	
	public static void main(String[] args) throws TwitterException {
			
			 TwitterSearchLastweek instance = TwitterSearchLastweek.getInstance("#java8");
			 
			 instance.amountTwitterLastweek();
			 
			// instance.amountRetweetLastweek();
			 
			// instance.amountFavoriteLastweek();
			 
		  // instance.twittersOrderNameUserLastweek();
		     
			//instance.twittersOrderDateLastweek();
}
	
	
	
	

}
