package it.uniba.sotorrent.soquery;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <entity>
 * Class for run query from questions table with year, month, day, limit.
 */
public class SOQueryQuestionDayWeight extends ASOQuery implements ISOQuery{

	/*
	 * Valid parameters for SOQueryQuestionDay.
	 */

	/**
	 * yyyy 	The year for the WHERE clause of the query.
	 * mm 		The month for the WHERE clause of the query.
	 * dd 		The day for the WHERE clause of the query.
	 * limit 	The limit of tuples.
	 */
	private Integer yyyy, mm, dd, limit;



	public SOQueryQuestionDayWeight(final Integer year, final Integer month, final Integer day, final Integer lim)
				throws FileNotFoundException, IOException {

			super();	//ASOQuery constructor.
			//Init variables
			yyyy = year;
			mm = month;
			dd = day;
			limit = lim;

		}


		@Override
		String getStringQuery() {
			String query = new String("SELECT "
					+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
					+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to` , "
					+ "COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight "
					+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
					+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
					+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
					+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
					+ "WHERE extract(year from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + yyyy
					+ " AND extract(month from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + mm
					+ " AND extract(day from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + dd
					+ " AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
					+ "AND `bigquery-public-data.stackoverflow.posts_questions`.answer_count > 0 "
					+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
					+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
					+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
					+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT "+ limit);
			return query;
		}
	}
