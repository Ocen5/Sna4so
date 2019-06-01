package it.uniba.sotorrent.cliparse;


/**
 * <<Entity>>
 * This class represents a parameter with an attribute and a value.
 * @author codd
 *
 */
abstract class Parameter {

	/**
	 * The attribute of parameter.
	 */
	private Attribute attribute;

	/**
	 * The value of parameter.
	 */
	private Object value;

	/**
	 * Constructor sets this Parameter.
	 * @param attribute of Parameter.
	 * @param value of Parameter.
	 */
	Parameter(final Attribute att, final Object val) {

		attribute = att;
		value = val;

	}

	/**
	 * Get attribute.
	 * @return attribute Attribute value
	 */
	Attribute getAttribute() {

		return attribute;

	}

	/**
	 * Gets the value of parameter.
	 * @return value
	 */
	Object getValue() {
		return value;
	}


	public String toString() {

		return attribute.toString();

	}


	//serve solo a parameter set (per l'hashset)
	public boolean equals(final Object o) {

		if (o != null) {
			return (toString().equals(o.toString()));
		}

		return false;

	}


	public int hashCode() {
	    return attribute.hashCode();
	  }

}
