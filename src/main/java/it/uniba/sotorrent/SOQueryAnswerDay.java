package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;

/*
 * Class for run query from answers table with year, month, day, limit.
 */
public final class SOQueryAnswerDay extends ASOQuery implements ISOQuery {

	/**
	 * Default constructor, see ASOQuery constructor.
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	**/
	public SOQueryAnswerDay() throws FileNotFoundException, IOException {

	}

	@Override
	public Job runQuery(final String[] values) throws InterruptedException {

		//Variabili per la query
		Integer yyyy = Integer.valueOf(values[0]),
				mm = Integer.valueOf(values[1]),
				dd = Integer.valueOf(values[2]),
				limit = Integer.valueOf(values[4]);

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
