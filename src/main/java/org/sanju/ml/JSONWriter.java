package org.sanju.ml;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.JacksonHandle;

/**
 *
 * @author Sanju Thomas
 *
 */
public class JSONWriter extends Writer<JsonNode>{

	private final String collectionName;
	private final JSONDocumentManager jsonDocumentManager;

	public JSONWriter(final Properties properties){
		this.collectionName = properties.getProperty("to.collection");
		final DatabaseClient client = ClientProvider.getClient(properties, "to");
		this.jsonDocumentManager = client.newJSONDocumentManager();

	}

	@Override
	public void write(final Map<String, JsonNode> page) {

		final DocumentMetadataHandle metadataHandle = new DocumentMetadataHandle().withCollections(this.collectionName);
		for(final Entry<String, JsonNode> entry : page.entrySet()){
			this.jsonDocumentManager.write(entry.getKey(), metadataHandle, new JacksonHandle(entry.getValue()));
		}
	}

}
