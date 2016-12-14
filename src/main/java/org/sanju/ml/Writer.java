package org.sanju.ml;

import java.util.Map;

/**
 *
 * @author Sanju Thomas
 *
 * @param <T>
 */
public abstract class Writer<T> {

	abstract void write(Map<String, T> page);

}
