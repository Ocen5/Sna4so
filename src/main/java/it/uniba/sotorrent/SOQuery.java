/**
 * 
 */
package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;


import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;


public final class SOQuery extends ASOQuery implements ISOQuery {
	
	
	public SOQuery() throws FileNotFoundException, IOException {};
	/**Default costructor, call ASOQuery constructor**/
	

	@Override
	public Job runQuery() throws InterruptedException {
		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
						+ "CONCAT('https://stackoverflow.com/questions/', "
						+ "CAST(id as STRING)) as url, "
						+ "view_count "
						+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
						+ "WHERE tags like '%google-bigquery%' "
						+ "ORDER BY favorite_count DESC LIMIT 10")
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
