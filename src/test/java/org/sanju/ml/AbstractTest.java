package org.sanju.ml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.junit.Before;

/**
 *
 * @author Sanju Thomas
 *
 */
public class AbstractTest {

	protected Properties properties;

	@Before
	public void setUp(){
		this.properties =  new Properties();
		Reader reader = null;
		try {
			reader = new FileReader(new File("./src/main/resources/server.properties"));
			this.properties.load(reader);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
