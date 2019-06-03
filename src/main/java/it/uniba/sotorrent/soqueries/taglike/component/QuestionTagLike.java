package it.uniba.sotorrent.soqueries.taglike.component;

import it.uniba.sotorrent.soqueries.Query;
import it.uniba.sotorrent.soqueries.taglike.TagLike;

/**
 * Entity
 * This class extends TagLike to run query from questions table with year, month, tags and limit.
 */
public class QuestionTagLike extends TagLike {

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
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
	public QuestionTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}

	/**
	 * This method builds the query.
	 */
	public void buildQuery() {

		query.setSelect("owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		query.setWhere("extract(year from creation_date)=" + getYear()
				+ " AND " + "extract(month from creation_date)=" + getMonth()
				+ " AND " + "tags like CONCAT('%" + getTaglike() + "%')"
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
	public String getQueryString() {

		return query.toString();

	}

}
