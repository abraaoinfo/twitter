package br.com.twitter.conexao;

import java.util.Properties;

import br.com.twitter.util.PropertiesUtil;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Connection {
	
	
	private  static Connection connection;
	
	private Properties prop;
	
	
	
	 private Connection() {
		 prop = PropertiesUtil.getProp();
	}
	 
	 
	
	 public static Twitter  conexaoTwitter(){
		 
		 if(connection == null){
			 
			 connection = new Connection();
		 }
		 
		 return connection.conexao();
		 
	 }
	 
	
	   private Twitter conexao() {
		 	
		ConfigurationBuilder builder = new ConfigurationBuilder();
			
		builder.setOAuthConsumerKey( prop.getProperty("oauth.consumerKey"));   //"I2H1rHhQJIvSoVK5jXd2331Fa" 
		builder.setOAuthConsumerSecret(prop.getProperty("oauth.consumerSecret") );  //"7kEvYuCtIqyX0SjZSSU9pOrKAdDqerG9QgsnsSjRvrOzhN3h8p"
		Configuration configuration = builder.build();	
		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();
		AccessToken accessToken = loadAccessToken();
		twitter.setOAuthAccessToken(accessToken);
		
		return twitter;
	}
	
	
	    private  AccessToken loadAccessToken(){
		
		String token = prop.getProperty("oauth.accessToken");       //"55240367-jTHlEZAnZEcezmHo6CcCshjYTeBslpn33cMQdZzYV";
		String tokenSecret =  prop.getProperty("oauth.accessTokenSecret");  // "pju9ZFNTfsrSx8UDEx1OPKMNRF2d0AUSpadamgcLIDrsr";
		return new AccessToken(token, tokenSecret);
		}
	
	

}
