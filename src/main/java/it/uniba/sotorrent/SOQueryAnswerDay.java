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
 * Class for run query from answers table with year, month, day, limit.
 */
public final class SOQueryAnswerDay extends ASOQuery implements ISOQuery {

	/*
	 * Valid parameters for SOQueryAnswerDay.
	 */

	/**
	 * yyyy 	The year for the WHERE clause of the query.
	 * mm 		The month for the WHERE clause of the query.
	 * dd 		The day for the WHERE clause of the query.
	 * limit 	The limit of tuples.
	 */
	private Integer yyyy, mm, dd, limit;

	/**
	 * Default constructor, init variables of the query and
	 * see ASOQuery constructor for instance of BigQuery API service.
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param day 	The day parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 * 
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	**/
	public SOQueryAnswerDay(final Integer year, final Integer month, final Integer day, final Integer lim)
			throws FileNotFoundException, IOException {

		super();	//ASOQuery constructor.
		//Init variables
		yyyy = year;
		mm = month;
		dd = day;
		limit = lim;

	}

	@Override
	public Job runQuery() throws InterruptedException {

		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
				+ "owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE extract(year FROM creation_date)=@yyyy "
				+ "AND extract(month FROM creation_date)=@mm "
				+ "AND extract(day FROM creation_date)=@dd AND owner_user_id>0 "
				+ "GROUP BY owner_user_id "
				+ "ORDER BY owner_user_id ASC LIMIT @limit")
				.addNamedParameter("yyyy", QueryParameterValue.int64(yyyy))
				.addNamedParameter("mm", QueryParameterValue.int64(mm))
				.addNamedParameter("dd", QueryParameterValue.int64(dd))
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
