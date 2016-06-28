package br.com.twitter.main;

import java.io.IOException;

import br.com.twitter.model.TwitterSearchLastweek;
import twitter4j.TwitterException;

public class Execute {
	
	
	
	public static void main(String[] args) throws TwitterException {
			
			 TwitterSearchLastweek instance = TwitterSearchLastweek.getInstance("#java8");
			 instance.amountTwitterLastweek();
			 instance.amountRetweetLastweek();
			 instance.amountFavoriteLastweek();
		     instance.twittersOrderNameUserLastweek();
			 instance.twittersOrderDateLastweek();
}
	
	
	
	

}
