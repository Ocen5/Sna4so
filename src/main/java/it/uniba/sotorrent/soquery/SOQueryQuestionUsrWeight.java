package it.uniba.sotorrent.soquery;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <<entity>>
 * Class for run query from questions table with user, limit.
 */
public final class SOQueryQuestionUsrWeight extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryQuestionUsrEdge.
	 */

	/**
	 * usr 		The user who made the Question of the query
	 * limit 	The limit of tuples.
	 */
	private Integer user, limit;

	/**
	 * Default constructor, init variables of the query and
	 * see ASOQuery constructor for instance of BigQuery API service.
	 * @param user 	The user param to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 * 
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	 **/
	public SOQueryQuestionUsrWeight(Integer user, Integer lim) throws FileNotFoundException, IOException {

		super();	//ASOQuery constructor.
		//Init variables
		this.user = user;
		this.limit = lim;

	}

	@Override
	String getStringQuery() {
		String query = new String("SELECT "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from` , "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`, "
				+ "COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id=" + user
				+ " AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.answer_count > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT " + limit);
		return query;
	}
}
