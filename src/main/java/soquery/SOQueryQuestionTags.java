package soquery;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <entity>
 * Class for run query from questions table with year, month, taglike, limit.
 */
public final class SOQueryQuestionTags extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryQuestionTags.
	 */

	/**
	 * yyyy 	The year for the WHERE clause of the query.
	 * mm 		The month for the WHERE clause of the query.
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
	public SOQueryQuestionTags(final Integer year, final Integer month, final String tags, final Integer lim)
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
				+ "owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE extract(year from creation_date)=" + yyyy
				+ " AND extract(month from creation_date)=" + mm
				+ " AND tags like CONCAT('%' ," + taglike + ", '%') "
				+ "AND owner_user_id > 0 "
				+ "GROUP BY owner_user_id "
				+ "ORDER BY owner_user_id ASC LIMIT " + limit);
		return query;
	}
}
