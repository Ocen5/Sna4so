package it.uniba.sotorrent.cliparse;

/**
 * <<Entity>>
 * This class represents the attribute of parameter.
 * @author codd
 *
 */
abstract class Attribute {

	/**
	 * The Attribute name.
	 */
	private String name;

	/**
	 * Constructor sets this name with parameter.
	 * @param name The name of the attribute
	 */
	Attribute(final String attributeName) {

		name = attributeName;

	}

	/**
	 * Get name of attribute.
	 * @return name The name of the attribute
	 */
	String getAttribute() {

		return name;

	}

	/**
	 * @return name String with the name of attribute
	 */
	public String toString() {

		return name;

	}

	/**
	 * @return true if attribute in input is equal to this, false otherwise
	 */
	public boolean equals(final Object o) {

		if (o != null) {
			return toString().equals(o.toString());
		}

		return false;

	}

	/**
	 * @return name.hashCode() the hash code computed on the name of attribute
	 */
	public int hashCode() {
	    return name.hashCode();
	  }

}
