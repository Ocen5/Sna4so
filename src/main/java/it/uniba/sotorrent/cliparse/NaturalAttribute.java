package it.uniba.sotorrent.cliparse;

/**
 * Entity
 * This class represents the integer domain of an attribute.
 * Author codd
 *
 */
class NaturalAttribute extends Attribute {

	/**
	 * Max value of domain of attribute.
	 */
	private Integer max;

	/**
	 * Min value of domain of attribute.
	 */
	private Integer min;

	/**
	 * Constructor sets this attribute name and the domain of range.
	 * 
	 * @param name The name of the attribute.
	 * @param maxValue Max value of the integer attribute.
	 * @param minValue Min value of the integer attribute.
	 */
	NaturalAttribute(final String name, final Integer maxValue, final Integer minValue) {

		super(name);
		max = maxValue;
		min = minValue;

	}

	/**
	 * The max domain of range.
	 * 
	 * @return max Max value of the attribute.
	 */
	Integer getMax() {

		return max;

	}

	/**
	 * The min domain of range.
	 * 
	 * @return min Min value of the attribute.
	 */
	Integer getMin() {

		return min;

	}

}
