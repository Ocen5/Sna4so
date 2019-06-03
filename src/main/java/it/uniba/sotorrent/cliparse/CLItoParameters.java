package it.uniba.sotorrent.cliparse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Control
 * This class implements CLItoParametersI. It takes parameters for the
 * query, parses them and finally give back the query.
 * Author codd
 *
 */
public class CLItoParameters implements CLItoParametersI {

	/**
	 * The List of Attributes.
	 */
	private List<Attribute> attributeSet = new ArrayList<Attribute>();

	/**
	 * Constructor which builds and adds 9 types of Attribute:
	 * year, month, day, taglike, user, limit, type, edge and weight.
	 */
	public CLItoParameters() {

		//create attributeSet
		final Integer maxYear = 2019, minYear = 2008;
		Attribute year = new NaturalAttribute("yyyy", maxYear, minYear);
		attributeSet.add(year);

		final Integer maxMonth = 12, minMonth = 1;
		Attribute month = new NaturalAttribute("mm", maxMonth, minMonth);
		attributeSet.add(month);

		final Integer maxDay = 31, minDay = 1;
		Attribute day = new NaturalAttribute("dd", maxDay, minDay);
		attributeSet.add(day);

		Attribute taglike = new StringAttribute("taglike", new String[0]);
		attributeSet.add(taglike);

		final Integer maxUser = 11142405, minUser = 1;
		Attribute user = new NaturalAttribute("user", maxUser, minUser);
		attributeSet.add(user);

		final Integer maxLimit = 500, minLimit = 0;
		Attribute limit = new NaturalAttribute("limit", maxLimit, minLimit);
		attributeSet.add(limit);

		Attribute type = new StringAttribute("type", new String[] {"question", "answer", "post"});
		attributeSet.add(type);

		Attribute edge = new BooleanAttribute("edge");
		attributeSet.add(edge);

		Attribute weight = new BooleanAttribute("weight");
		attributeSet.add(weight);


	}

	/**
	 * This method parses the parameter got from CLI.
	 * 
	 * @param args The command line inputs.
	 * @return paramSet ParameterSet which contains the input parameters to the query.
	 * @throws IllegalArgumentException Wrong parameter passed from CLI.
	 */
	public ParameterSet parseCLI(final String[] args) throws IllegalArgumentException {

		ParameterSet paramSet = new ParameterSet();

		String paramName = "";
		String paramValue = "";

		//for each args
		for (Integer index = 0; index < args.length; index++) {

			//get index of "="
			Integer equal = args[index].indexOf("=");

			//if "=" is not first or last char
			if (equal > 0) {
				if (equal < args[index].length()) {
					//get param name
					paramName = args[index].substring(0, equal);
					//get param value
					paramValue = args[index].substring(equal + 1, args[index].length());
				} else {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException();
			}

			Boolean noSuchAttribute = true;
			//for each attribute in set
			for (Attribute attribute : attributeSet) {
				if (attribute.toString().equals(paramName)) {

					//if is a natural attribute
					if (attribute instanceof NaturalAttribute) {
						paramSet.add(buildNaturalParameter(
						(NaturalAttribute) attribute, Integer.valueOf(paramValue)));
					}

					//if is a String attribute
					if (attribute instanceof StringAttribute) {
						paramSet.add(buildStringParameter(
								(StringAttribute) attribute, paramValue));
					}

					//if is a boolean attribute
					if (attribute instanceof BooleanAttribute) {
						paramSet.add(buildBooleanParameter(
								(BooleanAttribute) attribute, paramValue));
					}

					noSuchAttribute = false;
				}
			}

			if (noSuchAttribute) {
				throw new IllegalArgumentException();
			}

		} //end check argument

		return paramSet;
	}

	/**
	* This method checks passed value and builds the natural parameter.
	* 
 	* @param attribute The natural attribute.
 	* @param value The natural value.
 	* @return NaturalParameter The natural parameter parses.
 	* @throws IllegalArgumentException Wrong value passed from CLI.
 	*/
	private NaturalParameter buildNaturalParameter(final NaturalAttribute attribute, final Integer value)
			throws IllegalArgumentException {

		if (value >= attribute.getMin() && value <= attribute.getMax()) {
			return new NaturalParameter(attribute, value);

		} else {
			throw new IllegalArgumentException();
		}

	}

	/**
	 * This method checks passed value and builds the string parameter.
	 * 
	 * @param attribute The string attribute.
	 * @param value value The string value.
	 * @return StringParameter The string parameter parses.
	 * @throws IllegalArgumentException Wrong value passed from CLI.
	 */
	private StringParameter buildStringParameter(final StringAttribute attribute, final String value)
			throws IllegalArgumentException {

		Iterator<String> iter = attribute.iterator();

		if (!iter.hasNext()) {
			return new StringParameter(attribute, value);
		}

		while (iter.hasNext()) {
			if (iter.next().equals(value)) {
				return new StringParameter(attribute, value);
			}
		}

		throw new IllegalArgumentException();

	}

	/**
	 * This method checks passed value and builds the boolean parameter.
	 * 
	 * @param attribute The boolean attribute.
	 * @param value The boolean value.
	 * @return BooleanParameter The boolean parameter parses.
	 * @throws IllegalArgumentException Wrong value passed from CLI.
	 */
	private BooleanParameter buildBooleanParameter(final BooleanAttribute attribute, final String value)
			throws IllegalArgumentException {

		if (attribute.getTrue().equals(value)) {
			return new BooleanParameter(attribute, true);
		}

		if (attribute.getFalse().equals(value)) {
			return new BooleanParameter(attribute, false);
		}

		throw new IllegalArgumentException();

	}

}
