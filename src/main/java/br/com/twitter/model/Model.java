package br.com.twitter.model;



import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Model {
	
//teste
	
	
	
	public static void main(String[] args) throws TwitterException {


		
	
		/*Status status = twitter.updateStatus("@CASSIANECANTORA  oremos pelo brasil");*/
           
	/*	 1. Quantidade por dia de tweets da última semana.
		 2. Quantidade por dia de retweets da última semana.
		 3. Quantidade por dia de favoritações da última semana.
		 4. Ordenar os tweets pelo nome do autor, e exibir o primeiro nome e o último nome.
		 5. Ordenar os tweets por data, e exibir a data mais recente e a menos recente
*/



	
	      List<Status> tweets = qtdTwitterUltimaSemana();
	      int i =0;
	      for (Status status : tweets) {
	    	  i++;
	    	
			 System.out.println("indice "+i+" "+" "+" nome "+status.getUser().getScreenName() +" id "+  status.getId()+"data "+  status.getCreatedAt());
		}

      
	}

public static Twitter conexao() {
	ConfigurationBuilder builder = new ConfigurationBuilder();
	builder.setOAuthConsumerKey("I2H1rHhQJIvSoVK5jXd2331Fa");
	builder.setOAuthConsumerSecret( "7kEvYuCtIqyX0SjZSSU9pOrKAdDqerG9QgsnsSjRvrOzhN3h8p");
	Configuration configuration = builder.build();
	
	TwitterFactory factory = new TwitterFactory(configuration);
	Twitter twitter = factory.getInstance();
	AccessToken accessToken = loadAccessToken();
	twitter.setOAuthAccessToken(accessToken);
	
	return twitter;
}
	
	private static  List<Status> qtdTwitterUltimaSemana() throws TwitterException{
		
		 Twitter twitter = conexao();
		 Query query =new Query("#java");
		 query.setSince("2016-05-20");
		 query.setUntil("2016-05-25");
		 query.count(100);
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
		  
		  
	      
	      return tweetsAll;
	}
	
	
	
	
	
	private static long LastSinceId(List<Status> tweets) {	
	/*	long lastID  = Long.MAX_VALUE;
		   for (Status t: tweets) {
		        if(t.getId() < lastID) 
		        	lastID = t.getId();

		    }*/

		       Status status = tweets.get(( tweets.size()-1) );
		       return status.getId()-1;
	     }
	
	

	private static AccessToken loadAccessToken(){
		
		String token = "55240367-jTHlEZAnZEcezmHo6CcCshjYTeBslpn33cMQdZzYV";
		String tokenSecret = "pju9ZFNTfsrSx8UDEx1OPKMNRF2d0AUSpadamgcLIDrsr";
		return new AccessToken(token, tokenSecret);
		}
	
	  
	/*   public static List<Twitter> buscar(String busca) throws TwitterException {  
		   Twitter twitter = new Twitter();  
	      Query query = new Query("source: " + busca);  
	      QueryResult result = twitter.search(query);  
	      return result.getTweets();  
	   }  
*/

}
