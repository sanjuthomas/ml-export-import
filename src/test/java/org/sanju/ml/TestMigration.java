package org.sanju.ml;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.document.DocumentPage;
import com.marklogic.client.document.JSONDocumentManager;

import junit.framework.Assert;

/**
 *
 * @author Sanju Thomas
 *
 */
public class TestMigration extends AbstractTest{

	private JSONReader jsonReader;
	private JSONWriter jsonWriter;

	@Override
	@Before
	public void setUp(){
		super.setUp();
		this.jsonReader = new JSONReader(super.properties);
		this.jsonWriter = new JSONWriter(super.properties);
	}


	/**
	 * Migrate just one page, that is 10 documents.
	 */
	@Test
	public void shouldMigrate(){
		final JSONDocumentManager jsonDocumentManager = ClientProvider.getClient(this.properties, "to").newJSONDocumentManager();
		final Map<String, JsonNode> page = this.jsonReader.nextPage();
		this.jsonWriter.write(page);
		final String [] uris = page.keySet().toArray(new String [] {});
		final DocumentPage document = jsonDocumentManager.read(uris);
		Assert.assertEquals(10, document.getPageSize());
		jsonDocumentManager.delete(uris);
		final DocumentPage deleted = jsonDocumentManager.read(uris);
		Assert.assertEquals(0, deleted.getPageSize());
	}

}
