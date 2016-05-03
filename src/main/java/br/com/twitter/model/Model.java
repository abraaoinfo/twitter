package br.com.twitter.model;



import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Model {
	

	
	public static void main(String[] args) throws TwitterException {
		
		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("I2H1rHhQJIvSoVK5jXd2331Fa");
		builder.setOAuthConsumerSecret( "7kEvYuCtIqyX0SjZSSU9pOrKAdDqerG9QgsnsSjRvrOzhN3h8p");
		Configuration configuration = builder.build();
		
		TwitterFactory factory = new TwitterFactory(configuration);

		
		Twitter twitter = factory.getInstance();
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
		Status status = twitter.updateStatus("Olá Twitter!");
		System.out.println("Tweet postado com sucesso! [" + status.getText() + "].");
	    
	
	    
	}
	
	
	private static AccessToken loadAccessToken(){
		String token = "55240367-jTHlEZAnZEcezmHo6CcCshjYTeBslpn33cMQdZzYV";
		String tokenSecret = "pju9ZFNTfsrSx8UDEx1OPKMNRF2d0AUSpadamgcLIDrsr";
		return new AccessToken(token, tokenSecret);
		}


}
