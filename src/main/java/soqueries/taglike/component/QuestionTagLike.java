package soqueries.taglike.component;

import soqueries.Query;
import soqueries.taglike.TagLike;
/**
 * <<entity>>
 * This class extends TagLike for run query from questions table with year, month, taglike, limit.
 */
public class QuestionTagLike extends TagLike {

	private Query query = new Query();

	/**
	 * Default constructor, init variables of the query.
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param taglike The taglike parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
	public QuestionTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}

	/**
	 * This method create the query QuestionTagLike
	 */
	public void buildQuery() {

		query.setSelect("owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		query.setWhere("extract(year from creation_date)=" + getYear()
				+ " AND " + "extract(month from creation_date)=" + getMonth()
				+ " AND " + "tags like CONCAT('%\"" + getTaglike() + "\"%')"
				+ " AND " + "owner_user_id > 0");
		query.setGroup("owner_user_id");
		query.setOrder("owner_user_id ASC");
		query.setLimit(getLim());

	}

	/**
	 * @return query That is the query QuestionTagLike.
	 */
	public Query getQuery() {

		return query;

	}

	/**
	 * @return method toString of the query.
	 */
	@Override
	public String getQueryString() {

		return query.toString();

	}
}
