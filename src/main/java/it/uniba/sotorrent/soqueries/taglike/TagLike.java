package it.uniba.sotorrent.soqueries.taglike;

import it.uniba.sotorrent.soqueries.SOQueryI;
/**
 * <<entity>>
 * This class implements SOQueryI for queries with tags.
 */
public abstract class TagLike implements SOQueryI {

	private Integer year;
	private Integer month;
	private String taglike;
	private Integer lim;

	/**
	 * Default constructor, init variables of the query.
	 * @param yyyy	The year parameter to be inserted in the query.
	 * @param mm	The month parameter to be inserted in the query.
	 * @param day 	The day parameter to be inserted in the query.
	 * @param limit The limit parameter to be inserted in the query.
	**/
	public TagLike(final Integer yyyy, final Integer mm, final String tag, final Integer limit) {

		super();
		this.year = yyyy;
		this.month = mm;
		this.taglike = tag;
		this.lim = limit;

}

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
	 * @return taglike To indicate the tags of query's tuples.
	 */
	public String getTaglike() {

		return taglike;

	}

	/**
	 * @return lim To indicate the limit of query's tuples.
	 */
	public Integer getLim() {

		return lim;

	}
}
