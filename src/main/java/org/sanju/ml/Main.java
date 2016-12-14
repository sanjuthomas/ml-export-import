package org.sanju.ml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author Sanju Thomas
 *
 */
public class Main {

	private static Properties properties;

	static {
		properties = new Properties();
		Reader reader = null;
		try {
			reader = new FileReader(new File("./src/main/resources/server.properties"));
			properties.load(reader);
		} catch (final IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (final IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void main(final String[] args) {
		int page = 1;
		final JSONReader jsonReader = new JSONReader(properties);
		final JSONWriter jsonWriter = new JSONWriter(properties);
		Map<String, JsonNode> nodes =  new HashMap<>();
		while((page == 1) || (!nodes.isEmpty())){
			nodes = jsonReader.nextPage();
			jsonWriter.write(nodes);
			page += 1;
		}
	}

}
