package it.uniba.sotorrent.soqueries.day;

import it.uniba.sotorrent.soqueries.SOQueryI;
/**
 * <<entity>>
 * This class implements SOQueryI for queries with day.
 */
public abstract class Day implements SOQueryI {

	private Integer year;
	private Integer month;
	private Integer day;
	private Integer lim;
	//SOQuery.Query query ;

	public Day(final Integer yyyy, final Integer mm, final Integer dd, final Integer limit) {
		super();
		this.year = yyyy;
		this.month = mm;
		this.day = dd;
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
