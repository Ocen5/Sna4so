package it.uniba.sotorrent.soquery;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <<entity>>
 * Class for run query from questions table with year, month, taglike, limit.
 */
public final class SOQueryPostTags extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryPostTags.
	 */

	/**
	 * yyyy 	The year for the WHERE clause of the query.
	 * mm 		The month for the WHERE clause of the query.
	 * taglike 	The argument of posts for the WHERE clause of the query.
	 * limit 	The limit of tuples.
	 */
	private Integer yyyy, mm, limit;

	/**
	 * taglike 	The argument of posts for the WHERE clause of the query.
	 */
	private String taglike;

	/**
	 * Default constructor, init variables of the query and
	 * see ASOQuery constructor for instance of BigQuery API service.
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param tags 	The taglike parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 * 
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	**/
	public SOQueryPostTags(final Integer year, final Integer month, final String tags, final Integer lim)
			throws FileNotFoundException, IOException {

		super();	//ASOQuery constructor.
		//Init variables
		yyyy = year;
		mm = month;
		taglike = tags;
		limit = lim;

	}

	@Override
	String getStringQuery() {
		String query = new String("SELECT "
				+ "`bigquery-public-data.stackoverflow.stackoverflow_posts`.owner_user_id as owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.stackoverflow_posts` "
				+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.stackoverflow_posts`.creation_date)=" + yyyy
				+ " AND extract(month FROM `bigquery-public-data.stackoverflow.stackoverflow_posts`.creation_date)=" + mm
				+ " AND `bigquery-public-data.stackoverflow.stackoverflow_posts`.tags like "
				+ "CONCAT('%"+ taglike +"%') "
				+ "AND `bigquery-public-data.stackoverflow.stackoverflow_posts`.owner_user_id  > 0 "
				+ "UNION DISTINCT "
				+ "SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers`  "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=" + yyyy
				+ " AND extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=" + mm
				+ " AND `bigquery-public-data.stackoverflow.posts_questions`.tags like "
				+ "CONCAT('%"+ taglike +"%') "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  > 0 "
				+ "ORDER BY owner_user_id ASC LIMIT " + limit);
		return query;
	}
}
