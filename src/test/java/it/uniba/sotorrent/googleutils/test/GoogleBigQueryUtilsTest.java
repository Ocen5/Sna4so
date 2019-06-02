package it.uniba.sotorrent.googleutils.test;

import it.uniba.sotorrent.googleutils.GoogleBigQueryUtils;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

class GoogleBigQueryUtilsTest {
	/**
	 * Instance of BigQuery service.
	 */
	static private BigQuery bigquery;
	/**
	 * Query used for test methods
	 */
	static private String testQuery;

	/**
	 * URL of credentials JSON file.
	 */
	private static final String URL = "http://neo.di.uniba.it/credentials/project-codd-we445rt.json";

	/**
	 * Instance of ASOQuery used for test methods
	 */
	static GoogleBigQueryUtils test;

	/**
	 * Job used for test methods
	 */
	static Job testJob;

	@BeforeAll
	static void setUp() throws MalformedURLException, IOException, InterruptedException {
		test = new GoogleBigQueryUtils();

		bigquery = BigQueryOptions.newBuilder().setProjectId("sna4so-237908")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(URL).openStream())).build()
				.getService();

		testQuery = "SELECT "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers`"
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`"
				+ ".creation_date)=2016"
				+ " AND extract(month FROM `bigquery-public-data."
				+ "stackoverflow.posts_answers`.creation_date)=02"
				+ " AND `bigquery-public-data.stackoverflow.posts_questions`.tags like "
				+ "CONCAT('%java%') "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC "
				+ "LIMIT 100";

		// Use standard SQL syntax for queries.
		// See: https://cloud.google.com/bigquery/sql-reference/
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(testQuery)
				.setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		testJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		testJob = testJob.waitFor();
	}
	
	@Test
	void testRunQuery() throws JobException, InterruptedException {
		Job inTest;
		System.out.println("test");
		assertThrows(RuntimeException.class, () -> test.runQuery(null));
		inTest = test.runQuery(testQuery);
		assertTrue(inTest.exists());
		assertTrue(inTest.isDone());
		assertNotNull(inTest.getBigQuery());
		assertNotNull(test.getResults(inTest));
	}

	@Test
	void testGetResults() throws JobException, InterruptedException {
		TableResult result = testJob.getQueryResults();
		assertEquals(result,test.getResults(testJob));
	}

}
