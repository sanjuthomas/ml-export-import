package org.sanju.ml;
import java.util.Properties;

import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;

/**
 *
 * @author Sanju Thomas
 *
 */
public class ClientProvider {

	public static DatabaseClient getClient(final Properties properties, final String source){

		return  DatabaseClientFactory.newClient(properties.getProperty(source + ".host"),
				Integer.valueOf(properties.getProperty(source + ".port")),
				properties.getProperty(source + ".username"),
				properties.getProperty(source + ".password"), Authentication.BASIC);
	}

}
