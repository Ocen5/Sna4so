package it.uniba.sotorrent.cliparse;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Entity
 * This class represents the valid values of the domain of string attribute.
 * Author codd
 */
class StringAttribute extends Attribute implements Iterable<String> {

	/**
	 * Valid values of string attribute.
	 */
	private TreeSet<String> validValues;

	/**
	 * Constructor sets this attribute name and valid values of the domain.
	 * 
	 * @param name The name of the attribute.
	 * @param values Valid values for the attribute.
	 */
	StringAttribute(final String name, final String[] values) {

		super(name);
		validValues = new TreeSet<String>();

		for (int i = 0; i < values.length; i++) {
			validValues.add(values[i]);
		}

	}

	/**
	 * Iterator for valid values.
	 * 
	 * @return an iterator over the valid values in this set.
	 */
	public Iterator<String> iterator() {

		return validValues.iterator();

	}

}
