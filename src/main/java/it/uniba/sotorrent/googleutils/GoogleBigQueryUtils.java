package it.uniba.sotorrent.googleutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

/**
 * Boundary
 * Class to run a query on Stack Overflow via Google's BigQuery service.
 */
public class GoogleBigQueryUtils implements GoogleBigQueryI {

	/**
	 * Instance of BigQuery service.
	 */
	private BigQuery bigquery;

	/**
	 * URL of credentials JSON file.
	 */
	private static final String URL = "http://neo.di.uniba.it/credentials/project-codd-we445rt.json";

	/**
	 * Default constructor, instantiates BigQuery API service.
	 * 
	 * @throws FileNotFoundException The remote JSON file with credential is 404.
	 * @throws IOException Malformed JSON file.
	 */
	public GoogleBigQueryUtils() throws FileNotFoundException, IOException {

		bigquery = BigQueryOptions.newBuilder().setProjectId("sna4so-237908")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(URL).openStream())).build()
				.getService();
	}

	/**
	 * Starts the query.
	 * 
	 * @param query Query to run.
	 * @return Job The job associated to the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	public Job runQuery(final String query) throws InterruptedException {

		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query)
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

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

	/**
	 * Returns the results from the query job.
	 * 
	 * @param queryJob The job associated to the query.
	 * @return Results as a hash map, with URL as key and view count as value.
	 * @throws JobException Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	@Override
	public final TableResult getResults(final Job queryJob) throws JobException, InterruptedException {

		TableResult result = null;
		if (queryJob != null) {
			result = queryJob.getQueryResults();
			// Print all pages of the results.
			for (FieldValueList row : result.iterateAll()) {
				for (int schemaIndex = 0; schemaIndex < result.getSchema()
						.getFields().size(); schemaIndex++) {
					String attributeName = result.getSchema().getFields()
							.get(schemaIndex).getName();
					String value = row.get(attributeName).getStringValue();
					System.out.printf("%s: %s \t\t", attributeName, value);
				}
				System.out.println("");

			}

		}

		return result;
	}

}
