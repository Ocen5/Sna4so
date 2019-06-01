package it.uniba.sotorrent.cliparse;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * <<Entity>>
 * This class represents the set of parameter for attribute.
 * @author codd
 *
 */
public class ParameterSet implements Iterable<Parameter> {

	/**
	 * Parameters for attribute.
	 */
	private Set<Parameter> parameters = new HashSet<Parameter>();

	/**
	 * Add parameters to an hashSet.
	 * @param p
	 * @return parameters
	 */
	void add(final Parameter param) {

		parameters.add(param);

	}

	/**
	 * Iterator for parameters of attribute.
	 * @return parameters
	 */
	public Iterator<Parameter> iterator() {

		return parameters.iterator();

	}


}
