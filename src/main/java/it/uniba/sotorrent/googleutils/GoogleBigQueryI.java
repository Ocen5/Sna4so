package it.uniba.sotorrent.googleutils;

import com.google.cloud.bigquery.JobException;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.TableResult;


/**
 * Boundary
 * Interface to run a query on Stack Overflow via Google's BigQuery service.
 * Use this interface to run new query.
 * Author codd
 */
public interface GoogleBigQueryI {

	/**
	 * Starts the query.
	 * 
	 * @param query Query to run.
	 * @return The job for the query.
	 * @throws InterruptedException Raised on timeouts.
	 */
	Job runQuery(String query) throws InterruptedException;

	/**
	 * Returns the results from the query job.
	 * 
	 * @param job The job associated to the query.
	 * @return Results as a hash map, with URL as key and view count as value.
	 * @throws JobException Generic error occurred.
	 * @throws InterruptedException Raised on timeouts.
	 */
	TableResult getResults(Job job) throws JobException, InterruptedException;

}

