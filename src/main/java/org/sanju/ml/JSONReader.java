package org.sanju.ml;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.document.DocumentPage;
import com.marklogic.client.document.DocumentRecord;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;
import com.marklogic.client.query.QueryManager;
import com.marklogic.client.query.StructuredQueryDefinition;

/**
 *
 * @author Sanju Thomas
 *
 */
public class JSONReader extends Reader<JsonNode>{

	private final JSONDocumentManager jsonDocumentManager;
	private final StructuredQueryDefinition sqd;
	private DocumentPage documentPage;

	/**
	 *
	 * @param properties
	 */
	public JSONReader(final Properties properties) {
		final DatabaseClient client = ClientProvider.getClient(properties, "from");
		this.jsonDocumentManager = client.newJSONDocumentManager();
		this.jsonDocumentManager.setPageLength(PAGE_SIZE);
		final QueryManager queryManager = client.newQueryManager();
		this.sqd = queryManager.newStructuredQueryBuilder().collection(properties.getProperty("from.collection"));
	}


	/**
	 *
	 * @return
	 */
	@Override
	public Map<String, JsonNode> nextPage(){
		final Map<String, JsonNode> nodeMap = new LinkedHashMap<>(PAGE_SIZE);
		if((this.START == 1) || this.documentPage.hasNextPage()){
			this.documentPage = this.jsonDocumentManager.search(this.sqd, this.START);
			for (final DocumentRecord document : this.documentPage) {
				final JacksonHandle handle = new JacksonHandle();
				document.getContent(handle);
				nodeMap.put(document.getUri(), handle.get());
			}
			this.START  += this.documentPage.getPageSize();
		}
		return nodeMap;
	}

}
