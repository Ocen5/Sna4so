package it.uniba.sotorrent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.TableResult;


abstract class ASOQuery implements ISOQuery {
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
	public ASOQuery() throws FileNotFoundException, IOException {
		bigquery = BigQueryOptions.newBuilder().setProjectId("sna4so-237908")
				.setCredentials(ServiceAccountCredentials.fromStream(new URL(url).openStream())).build()
				.getService();
	}



	abstract public Job runQuery() throws InterruptedException;



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


	public final BigQuery getQuery() {
		return bigquery;
	}


}
