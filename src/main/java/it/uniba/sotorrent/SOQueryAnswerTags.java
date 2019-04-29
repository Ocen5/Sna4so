package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;

/**
 * Class for run query from questions table with year, month, taglike, limit.
 */
public final class SOQueryAnswerTags extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryAnswerTags.
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
	public SOQueryAnswerTags(final Integer year, final Integer month, final String tags, final Integer lim)
			throws FileNotFoundException, IOException {

		super();	//ASOQuery constructor.
		//Init variables
		yyyy = year;
		mm = month;
		taglike = tags;
		limit = lim;

	}


	@Override
	public Job runQuery() throws InterruptedException {

		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
						+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
						+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
						+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers`"
						+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
						+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
						+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=@yyyy "
						+ "AND extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=@mm "
						+ "AND `bigquery-public-data.stackoverflow.posts_questions`.tags like "
						+ "CONCAT('%' ,@taglike, '%') "
						+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
						+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
						+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC "
						+ "LIMIT @limit")
				.addNamedParameter("yyyy", QueryParameterValue.int64(yyyy))
				.addNamedParameter("mm", QueryParameterValue.int64(mm))
				.addNamedParameter("limit", QueryParameterValue.int64(limit))
				.addNamedParameter("taglike", QueryParameterValue.string(taglike))
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = getQuery().create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		queryJob = queryJob.waitFor();

		// Check for errors
		if (queryJob == null) {
			throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
			// You can also look at queryJob.getStatus().getExecutionErrors() for all
			// errors, not just the latest one.
			throw new RuntimeException(queryJob.getStatus().getError().toString());
		}
		return queryJob;
	}

}
