package soqueries.taglike.component;

import soqueries.Query;
import soqueries.taglike.TagLike;
/**
 * <<entity>>
 * This class extends TagLike for run query from answers table with year, month, taglike, limit.
 */
public class AnswerTagLike extends TagLike {

	private Query query = new Query();

	/**
	 * Default constructor, init variables of the query.
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param taglike The taglike parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
	public AnswerTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}

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
			+ " CONCAT('%\"" + getTaglike() + "\"%')"
			+ " AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC");
		query.setLimit(getLim());

	}

	/**
	 * @return query That is the query AnswerTagLike
	 */
	public Query getQuery() {

		return query;

	}

	/**
	 * @return method toString of the query
	 */
	@Override
	public String getQueryString() {

		return query.toString();

	}
}
