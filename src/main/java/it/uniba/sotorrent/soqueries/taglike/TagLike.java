package it.uniba.sotorrent.soqueries.taglike;

import it.uniba.sotorrent.soqueries.SOQueryI;

/**
 * Entity
 * This class implements SOQueryI for queries on tags.
 */
public abstract class TagLike implements SOQueryI {

	/**
	 * The year value.
	 */
	private Integer year;

	/**
	 * The month value.
	 */
	private Integer month;

	/**
	 * The tag value.
	 */
	private String taglike;

	/**
	 * The limit value.
	 */
	private Integer lim;

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param yyyy	The year parameter to be inserted in the query.
	 * @param mm	The month parameter to be inserted in the query.
	 * @param tag 	The tag parameter to be inserted in the query.
	 * @param limit The limit parameter to be inserted in the query.
	**/
	public TagLike(final Integer yyyy, final Integer mm, final String tag, final Integer limit) {

		super();
		this.year = yyyy;
		this.month = mm;
		this.taglike = tag;
		this.lim = limit;

	}

	/**
	 * @return the query in string format.
	 */
	public abstract String getQueryString();

	/**
	 * @return year That contain the year of the query.
	 */
	public Integer getYear() {

		return year;

	}

	/**
	 * @return month That contain the month of the query.
	 */
	public Integer getMonth() {

		return month;

	}

	/**
	 * @return tag To indicate the tags of query's tuples.
	 */
	public String getTaglike() {

		return taglike;

	}

	/**
	 * @return limit To indicate the limit of query's tuples.
	 */
	public Integer getLim() {

		return lim;

	}

}
