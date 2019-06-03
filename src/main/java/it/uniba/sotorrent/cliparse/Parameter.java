package it.uniba.sotorrent.cliparse;

/**
 * Entity
 * This class represents a parameter with an attribute and a value.
 * Author codd
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
	 * 
	 * @param att The attribute of Parameter.
	 * @param val The value of Parameter.
	 */
	Parameter(final Attribute att, final Object val) {

		attribute = att;
		value = val;

	}

	/**
	 * Get attribute.
	 * 
	 * @return attribute Attribute value.
	 */
	Attribute getAttribute() {

		return attribute;

	}

	/**
	 * Gets the value of parameter.
	 * 
	 * @return value.
	 */
	Object getValue() {

		return value;

	}

	/**
	 * This method returns the parameter name, it's the attribute name.
	 * 
	 * @return String The parameter name.
	 */
	public String toString() {

		return attribute.toString();

	}

	/**
	 * This method checks if parameter names are equal.
	 * 
	 * @param parameter The parameter to compare.
	 * @return boolean True when passed parameter name equals to this parameter name.
	 */
	public boolean equals(final Object parameter) {

		if (parameter != null) {
			return (toString().equals(parameter.toString()));
		}

		return false;

	}

	/**
	 * This method is supported for the benefit of hashset.
	 * 
	 * @return int The hashcode value for parameter.
	 */
	public int hashCode() {

		return attribute.hashCode();

	  }

}
