/**
 * 
 */
package it.uniba.sotorrent;

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


public final class SOQuery implements ISOQuery {
	/**
	 * Instance of BigQuery service.
	 */
	private BigQuery bigquery;
	/**
	 * URL of credentials JSON file.
	 */
	private static final String url = "http://neo.di.uniba.it/credentials/project-codd-we445rt.json";

	/**
	 * Default constructor, instantiates BigQuery API service.
	 * @throws FileNotFoundException The remote JSON file with credential is 404.
	 * @throws IOException Malformed JSON file.
	 */
	public SOQuery() throws FileNotFoundException, IOException {
		bigquery = BigQueryOptions.newBuilder().setProjectId("sna4so-237908")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(url).openStream())).build()
				.getService();
	}

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


	@Override
	public final TableResult getResults(final Job queryJob) throws JobException, InterruptedException {

        TableResult result = null;
        if (queryJob != null) {
            result = queryJob.getQueryResults();
            // Print all pages of the results.
            for (FieldValueList row : result.iterateAll()) {
                for (int schemaIndex = 0; schemaIndex < result.getSchema().getFields().size(); schemaIndex++) {
                    String attributeName = result.getSchema().getFields().get(schemaIndex).getName();
                    String value = row.get(attributeName).getStringValue();
                    System.out.printf("%s: %s \t\t" ,attributeName, value);
                }
                System.out.println("");

            }
        }
        return result;
    }

}
