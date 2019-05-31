package soqueries.user.component;

import soqueries.Query;
import soqueries.user.User;
/**
 * <<entity>>
 * This class extends User for run query from answers table with userId, limit.
 */
public class AnswerEdgeUser extends User {
	private Query query = new Query();

	/**
	 * Default constructor, init variables of the query.
	 * @param userId The user parameter to be inserted in the query.
	 * @param lim The limit parameter to be inserted in the query.
	**/
	public AnswerEdgeUser(final Integer userId, final Integer lim) {

		super(userId, lim);
		buildQuery();

	}

	/**
	 * This method create the query AnswerEdgeUser
	 */
	public void buildQuery() {

		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id=" + getUserId()
				+ " AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0"
				+ " AND " + "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC");
		query.setLimit(getLim());

	}

	/**
	 * @return query That is the query AnswerEdgeUser
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
