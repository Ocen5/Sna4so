package it.uniba.sotorrent.soqueries.day.component;

import it.uniba.sotorrent.soqueries.Query;
import it.uniba.sotorrent.soqueries.day.Day;

/**
 * Entity
 * This class extends Day to run query from questions table with year, month, day and limit (edge).
 */
public class QuestionEdgeDay extends Day {

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
	public QuestionEdgeDay(final Integer year, final Integer month, final Integer day, final Integer lim) {

		super(year, month, day, lim);
		buildQuery();

	}

	private void buildQuery() {

		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_answers` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_questions` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("extract(year from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)="
				+ getYear() + " AND " + "extract("
				+ "month from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)="
				+ getMonth() + " AND " + "extract("
				+ "day from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)="
				+ getDay()
				+ " AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0"
				+ " AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id >0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC");
		query.setLimit(getLim());
	}

	/**
	 * @return query That is the query QuestionEdgeDay.
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
