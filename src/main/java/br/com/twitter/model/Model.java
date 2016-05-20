package br.com.twitter.model;



import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Relationship;
import twitter4j.Status;
import twitter4j.StatusUpdate;
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
		/*Status status = twitter.updateStatus("@CASSIANECANTORA  oremos pelo brasil");*/

		
		 Query query =new Query("#rtyhgfdser");
		 query.setSince("2016-05-15");
		 query.setUntil("2016-05-21");
		 query.count(100);


           
	



	      QueryResult result = twitter.search(query); 
	      long sinceId = result.getSinceId();
	      List<Status> tweets = result.getTweets();
	      int i =0;
	      for (Status status2 : tweets) {
	    	  i++;
	    	
			 System.out.println("indice "+i+" "+" "+" nome "+status2.getUser().getScreenName()+" geo "+status2.getGeoLocation() +" id "+  status2.getId());
		}

   //733444680352948224

   
	      query.setSinceId(733743543064395778l);
       
	      QueryResult result2 = twitter.search(query); 

	      List<Status> tweets2 = result2.getTweets();
          
          for (Status status2 : tweets2) {
	    	  i++;
			 System.out.println("indice "+i+" "+" "+" nome "+status2.getUser().getScreenName()+" geo "+status2.getGeoLocation() +" id "+  status2.getId() );
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
