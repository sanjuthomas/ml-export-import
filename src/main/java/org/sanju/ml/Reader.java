package org.sanju.ml;

import java.util.Map;

/**
 *
 * @author Sanju Thomas
 *
 * @param <T>
 */
public abstract class Reader<T> {

	protected static final int PAGE_SIZE = 10;
	protected int START = 1;

	/**
	 * Return a page from the given collection on every call.
	 * If no page is left, an empty map is returned.
	 *
	 * @return <url of the document, document>
	 */
	abstract Map<String, T> nextPage();

}
