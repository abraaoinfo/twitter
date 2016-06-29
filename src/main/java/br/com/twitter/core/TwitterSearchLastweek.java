package br.com.twitter.core;



import java.text.SimpleDateFormat;
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
	
	private String hastTag;
	
	
	private TwitterSearchLastweek() {
	}
	 
	
	public static TwitterSearchLastweek getInstance(final String hastTag) throws TwitterException {
		
		TwitterSearchLastweek twitterSearchOperation = new TwitterSearchLastweek();
		twitterSearchOperation.setTwitter(Connection.conexaoTwitter());	
		twitterSearchOperation.setHastTag(hastTag);
		twitterSearchOperation.twitterSearchLastweek();
		return twitterSearchOperation;
	}
	
	
	

	private  void twitterSearchLastweek() throws TwitterException{
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
		
	  System.out.println("\nQuantidade de twitter com a hastTag "+hastTag+" da ultima semana :"+status.size()+"\n");		
	  return status.size();
	}
	
	
	public  List<Status> twittersOrderDateLastweek() {   
		System.err.println("\n"+"-------------------------------------Twitter Ordenados por Data-----------------------------------------------------------"+"\n" +"\n");
        SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yyyy");
		for (Status st : status) {			
			System.out.println("Data :"+ fd.format(st.getCreatedAt()) +" \nTwitters "+st.getText());	
			System.out.println("_____________________________________________________________________________________________________");
		 }
		  System.err.println("-------------------------------------Fim da lista do twitters Ordenados por Data-----------------------------------------------------------"+"\n" +"\n");
	      return status;
	}
	
	public List<Status> twittersOrderNameUserLastweek(){
		List<Status> sts = new ArrayList<Status>(status);
		Collections.sort(sts, new OrderNameComparator());		
		System.err.println("-------------------------------------Twitter Ordenados por Nome-----------------------------------------------------------"+"\n" +"\n");

		for (Status st : sts) {	
			
			System.out.println("Nome: "
		     +returnNomeUltimoNome(st.getUser()) +"\nTwitters : "+st.getText());
			 System.out.println("_____________________________________________________________________________________________________");
			 System.out.println("");
			
		}
		
		 System.err.println("\n"+"------------------------------Fim da Lista dos twitter Ordenados por nome------------------------------------------------------------------"+"\n");
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
		System.out.println();
		for (Status st : status) {			
			countRetweet += st.getRetweetCount();			
		}
		System.out.println("Quantidade de retweet com a hastTag "+hastTag+" da ultima semana: "+countRetweet);
		System.out.println();
	
		
		return countRetweet;
	}
	
	public int  amountFavoriteLastweek(){
		int countFavorite =0;
		System.out.println();
		for (Status st : status) {			
			countFavorite +=	st.getFavoriteCount();			
		}
		System.out.println("Quantidade de favoritos com a hastTag "+hastTag+" da ultima semana: "+countFavorite);
		
		System.out.println();
		
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


	public String getHastTag() {
		return hastTag;
	}


	public void setHastTag(String hastTag) {
		this.hastTag = hastTag;
	}


}
