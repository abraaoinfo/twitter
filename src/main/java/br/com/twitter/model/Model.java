package br.com.twitter.model;



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
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("I2H1rHhQJIvSoVK5jXd2331Fa");
		builder.setOAuthConsumerSecret( "7kEvYuCtIqyX0SjZSSU9pOrKAdDqerG9QgsnsSjRvrOzhN3h8p");
		Configuration configuration = builder.build();
		
		TwitterFactory factory = new TwitterFactory(configuration);

		
		Twitter twitter = factory.getInstance();
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
		//Status status = twitter.updateStatus("Ola Twitter!");

		
		 Query query = new Query("source: " + "java");  
	      QueryResult result = twitter.search(query); 
	      List<Status> tweets = result.getTweets();
	      for (Status status2 : tweets) {
	    	  
			System.out.println(status2.getText()+" "+ status2.getUser().getName()+" "+status2);
		}

            
		
		
	    
	
	    
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
