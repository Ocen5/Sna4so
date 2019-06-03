package it.uniba.sotorrent.soqueries.taglike.component;

import it.uniba.sotorrent.soqueries.Query;
import it.uniba.sotorrent.soqueries.taglike.TagLike;

/**
 * Entity
 * This class extends TagLike to run query from answers table with year, month, tags and limit.
 */
public class AnswerTagLike extends TagLike {

	/**
	 * The query.
	 */
	private Query query = new Query();

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param taglike The tag parameter to be inserted in the query.
	 * @param lim The limit parameter to be inserted in the query.
	**/
	public AnswerTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}

	/**
	 * This method builds the query.
	 */
	private void buildQuery() {

		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions` "
			+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
			+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
			+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)="
			+ getYear() + " AND "
			+ "extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)="
			+ getMonth()
			+ " AND " + "`bigquery-public-data.stackoverflow.posts_questions`.tags like"
			+ " CONCAT('%" + getTaglike() + "%')"
			+ " AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC");
		query.setLimit(getLim());

	}

	/**
	 * @return query That is the query AnswerTagLike.
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
