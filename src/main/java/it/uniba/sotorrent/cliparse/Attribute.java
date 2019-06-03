package it.uniba.sotorrent.cliparse;

/**
 * Entity
 * This class represents the attribute of parameter.
 * Author codd
 *
 */
abstract class Attribute {

	/**
	 * The Attribute name.
	 */
	private String name;

	/**
	 * Constructor sets this name with parameter.
	 *
	 * @param attributeName The name of the attribute.
	 */
	Attribute(final String attributeName) {

		name = attributeName;

	}

	/**
	 * Get name of attribute.
	 *
	 * @return name The name of the attribute.
	 */
	String getAttribute() {

		return name;

	}

	/**
	 * @return name String with the name of attribute.
	 */
	public String toString() {

		return name;

	}

	/**
	 * This method checks if attribute names are equal.
	 * 
	 * @param attribute The attribute to compare.
	 * @return true if attribute in input is equal to this, false otherwise.
	 */
	public boolean equals(final Object attribute) {

		if (attribute != null) {
			return toString().equals(attribute.toString());
		}

		return false;

	}

	/**
	 * This method is supported for the benefit of hashset.
	 *
	 * @return The hashcode value for attribute.
	 */
	public int hashCode() {

	    return name.hashCode();

	  }

}
