package it.uniba.sotorrent.cliparse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author codd
 *
 */
public class CLItoParameters implements CLItoParametersI {

	/**
	 * A List of Attribute
	 */
	private List<Attribute> attributeSet = new ArrayList<Attribute>();

	/**
	 * Default Constructor wich build and add 9 type of Attribute : 
	 * year, month, day, taglike, user, limit, type, edge and weight
	 */
	public CLItoParameters() {

		//create attributeSet
		Attribute year = new NaturalAttribute("yyyy", 2019, 2008);
		attributeSet.add(year);

		Attribute month = new NaturalAttribute("mm", 12, 1);
		attributeSet.add(month);

		Attribute day = new NaturalAttribute("dd", 31, 1);
		attributeSet.add(day);

		Attribute taglike = new StringAttribute("taglike", new String[0]);
		attributeSet.add(taglike);

		Attribute user = new NaturalAttribute("user", 11142405, 1);
		attributeSet.add(user);

		Attribute limit = new NaturalAttribute("limit", 500, 0);
		attributeSet.add(limit);

		Attribute type = new StringAttribute("type", new String[] {"question", "answer", "post"});
		attributeSet.add(type);

		Attribute edge = new BooleanAttribute("edge");
		attributeSet.add(edge);

		Attribute weight = new BooleanAttribute("weight");
		attributeSet.add(weight);


	}

	/**
	 * @return paramSet ParameterSet which contains the input parameters to the query
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
	* 
 	* @param attribute
 	* @param value
 	* @return
 	* @throws IllegalArgumentException
 	*/
	private NaturalParameter buildNaturalParameter(final NaturalAttribute attribute, final Integer value)
			throws IllegalArgumentException {

		if (value >= attribute.getMin() && value <= attribute.getMax()) {
			return new NaturalParameter(attribute, value);

		} else {
			throw new IllegalArgumentException();
		}

	}



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
