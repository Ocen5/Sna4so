package it.uniba.sotorrent.cliparse;

/**
 * Entity
 * This class represents the boolean values for attribute.
 * Author codd
 *
 */
class BooleanAttribute extends Attribute {

	/**
	 * True value for attribute.
	 */
	private String ifTrue = "yes";

	/**
	 * False value for attribute.
	 */
	private String ifFalse = "no";

	/**
	 * Constructor sets this attribute name and boolean values of the domain.
	 * 
	 * @param name The boolean value for the attribute.
	 */
	BooleanAttribute(final String name) {

		super(name);

	}

	/**
	 * Get true value for attribute.
	 * 
	 * @return ifTrue True value of the attribute.
	 */
	String getTrue() {

		return ifTrue;

	}

	/**
	 * Get false value for attribute.
	 * 
	 * @return ifFalse False value for the attribute.
	 */
	String getFalse() {

		return ifFalse;

	}

}
