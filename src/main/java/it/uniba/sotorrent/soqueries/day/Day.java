package it.uniba.sotorrent.soqueries.day;

import it.uniba.sotorrent.soqueries.SOQueryI;

/**
 * Entity
 * This class implements SOQueryI for queries with day.
 */
public abstract class Day implements SOQueryI {

	/**
	 * The year value.
	 */
	private Integer year;

	/**
	 * The month value.
	 */
	private Integer month;

	/**
	 * The day value.
	 */
	private Integer day;

	/**
	 * The limit value.
	 */
	private Integer lim;

	/**
	 * Constructor sets the values for the query.
	 * 
	 * @param yyyy The year value.
	 * @param mm The month value.
	 * @param dd The day value.
	 * @param limit The limit value.
	 */
	public Day(final Integer yyyy, final Integer mm, final Integer dd, final Integer limit) {

		super();
		this.year = yyyy;
		this.month = mm;
		this.day = dd;
		this.lim = limit;

	}

	/**
	 * @return the query in string format.
	 */
	public abstract String getQueryString();

	/**
	 * @return year That contains the year of the query.
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
	 * @return day That contain the day of the query.
	 */
	public Integer getDay() {

		return day;

	}

	/**
	 * @return lim To indicate the limit of query's tuples.
	 */
	public Integer getLim() {

		return lim;

	}

}
