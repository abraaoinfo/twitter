package br.com.twitter.model;



import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.twitter.conexao.Connection;
import br.com.twitter.util.OrderNameComparator;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class TwitterSearchLastweek {
	
	private Twitter twitter;
	
	private List<Status> status;
	
	
	private TwitterSearchLastweek() {
	}
	 
	
	public static TwitterSearchLastweek getInstance(String hastTag) throws TwitterException {
		TwitterSearchLastweek twitterSearchOperation = new TwitterSearchLastweek();
		twitterSearchOperation.setTwitter(Connection.conexaoTwitter());
		
		twitterSearchOperation.twitterSearchLastweek(hastTag);
		return twitterSearchOperation;
	}
	
	
	
	public static void main(String[] args) throws TwitterException, IOException {
		
		 TwitterSearchLastweek instance = TwitterSearchLastweek.getInstance("#java8");

		 instance.twittersOrderNameUserLastweek();
		 instance.twittersOrderDateLastweek();
	
	
	}
	
	private  void twitterSearchLastweek(String hastTag) throws TwitterException{
		 Query query = queryUltimaSemana(hastTag);
		
		 List<Status> tweets = new ArrayList<Status>();
		 List<Status>  tweetsAll = new ArrayList<Status>();
		 QueryResult result = twitter.search(query); 
	     tweets = result.getTweets();
	     tweetsAll.addAll(tweets);
		
		  while(tweets.size() >0){
			  long lastSinceId = LastSinceId(tweets);
	         query.setMaxId(lastSinceId);
	         QueryResult search = twitter.search(query);
	         tweets = search.getTweets();
	         if(tweets.size() >0)
	            tweetsAll.addAll(tweets);
	         
		 }
		  
		  Collections.unmodifiableCollection(tweetsAll);
		    
		setStatus(tweetsAll);
	
	}
	
	public Query queryUltimaSemana(String hastTag){
		 LocalDate hoje = LocalDate.now();
		 LocalDate amanha = hoje.plusDays(1);
		 LocalDate primeiroDiaSemana = hoje.minusDays(7);
		return new Query(hastTag).since(primeiroDiaSemana.toString()).until(amanha.toString()).count(100);
	}
	
		
	
	public  int amountTwitterLastweek() {	
	  return status.size();
	}
	
	
	public  List<Status> twittersOrderDateLastweek() {   
		
        for (Status st : status) {			
			System.out.println("Data :"+st.getCreatedAt());		
		}
	      return status;
	}
	
	public List<Status> twittersOrderNameUserLastweek(){
		List<Status> sts = new ArrayList<Status>(status);
		Collections.sort(sts, new OrderNameComparator());		
		for (Status st : sts) {	
			System.out.println("Nome e Ultimo Nome :" +returnNomeUltimoNome(st.getUser()));
			
		}
		
		return sts;
	}
	
	
	private String returnNomeUltimoNome(User user) {
		
		String[] split = user.getName().split(" ");
		
		if(split.length >= 3){

			return split[0]+" "+split[split.length -1];
		}else{
			return user.getName();
		}
	     
	
	}


	public int  amountRetweetLastweek(){
		int countRetweet =0;
		
		for (Status st : status) {			
			countRetweet =+	st.getRetweetCount();			
		}
		
		return countRetweet;
	}
	
	public int  amountFavoriteLastweek(){
		int countFavorite =0;
		
		for (Status st : status) {			
			countFavorite =+	st.getFavoriteCount();			
		}
		
		return countFavorite;
	}
	
	
	private  long LastSinceId(List<Status> tweets) {	
         Status status = tweets.get(( tweets.size()-1) );
		 return status.getId()-1;
	 }


	public Twitter getTwitter() {
		return twitter;
	}


	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}


	public List<Status> getStatus() {
		return status;
	}


	public void setStatus(List<Status> status) {
		this.status = status;
	}


}
