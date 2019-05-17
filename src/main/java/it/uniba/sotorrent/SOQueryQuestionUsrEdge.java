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
 * Class for run query from questions table with user, limit.
 */
public final class SOQueryQuestionUsrEdge extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryQuestionUsrEdge.
	 */

	/**
	 * usr 		The user who made the Question of the query
	 * limit 	The limit of tuples.
	 */
	private Integer usr, limit;

	/**
	 * Default constructor, init variables of the query and
	 * see ASOQuery constructor for instance of BigQuery API service.
	 * @param user 	The user param to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 * 
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	 **/
	public SOQueryQuestionUsrEdge(Integer user, Integer lim) throws FileNotFoundException, IOException {

		super();	//ASOQuery constructor.
		//Init variables
		this.usr = user;
		this.limit = lim;

	}

	@Override
	public Job runQuery() throws InterruptedException {

		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to` "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id=@usr " 
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT @limit")
				.addNamedParameter("yyyy", QueryParameterValue.int64(usr))
				.addNamedParameter("limit", QueryParameterValue.int64(limit))
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
