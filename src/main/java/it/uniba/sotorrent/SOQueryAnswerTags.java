package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryParameterValue;

public final class SOQueryAnswerTags extends ASOQuery implements ISOQuery {
	
	/**
	 * Default constructor, see ASOQuery constructor
	**/
	public SOQueryAnswerTags() throws FileNotFoundException, IOException {};
	
	

	@Override
	public Job runQuery(String[] values) throws InterruptedException {
		
		//Variabili per la query
		Integer yyyy=Integer.valueOf(values[0]), 
				mm=Integer.valueOf(values[1]),
				limit=Integer.valueOf(values[4]);
		String taglike=values[3];
				
		
		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("SELECT "
						+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
						+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
						+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers`"
						+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
						+ "WHERE  extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=@yyyy "
						+ "AND extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=@mm "
						+ "AND `bigquery-public-data.stackoverflow.posts_questions`.tags like CONCAT('%' ,@taglike, '%') "
						+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
						+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
						+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC LIMIT @limit")
				.addNamedParameter("yyyy", QueryParameterValue.int64(yyyy))
				.addNamedParameter("mm", QueryParameterValue.int64(mm))
				.addNamedParameter("limit", QueryParameterValue.int64(limit))
				.addNamedParameter("taglike",QueryParameterValue.string(taglike))
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
