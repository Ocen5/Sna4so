package it.uniba.sotorrent.soqueries.day.component;

import it.uniba.sotorrent.soqueries.Query;
import it.uniba.sotorrent.soqueries.day.Day;

/**
 * Entity
 * This class extends Day to run query from questions table with year, month, day and limit.
 */
public class QuestionDay extends Day {

	/**
	 * The query.
	 */
	private Query query = new Query();

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param day 	The day parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
	public QuestionDay(final Integer year, final Integer month, final Integer day, final Integer lim) {

		super(year, month, day, lim);
		buildQuery();

	}

	/**
	 * This method builds the query.
	 */
	public void buildQuery() {

		Query questionday = this.getQuery();
		questionday.setSelect("owner_user_id");
		questionday.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		questionday.setWhere("extract(year from creation_date)=" + getYear()
				+ " AND " + "extract(month from creation_date)=" + getMonth()
				+ " AND " + "extract(day from creation_date)=" + getDay()
				+ " AND " + "owner_user_id > 0");
		questionday.setGroup("owner_user_id");
		questionday.setOrder("owner_user_id ASC");
		questionday.setLimit(getLim());

	}

	/**
	 * @return query That is the query QuestionDay.
	 */
	public Query getQuery() {

		return query;

	}

	/**
	 * @return method toString of the query.
	 */
	public String getQueryString() {

		return query.toString();

	}
}
