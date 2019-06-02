package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.googleutils.GoogleBigQueryI;
import it.uniba.sotorrent.googleutils.GoogleBigQueryUtils;
import it.uniba.sotorrent.googleutils.GoogleDocI;
import it.uniba.sotorrent.googleutils.GoogleDocUtils;

import it.uniba.sotorrent.cliparse.CLItoParameters;
import it.uniba.sotorrent.cliparse.CLItoParametersI;
import it.uniba.sotorrent.cliparse.ParameterSet;
import it.uniba.sotorrent.cliparse.QuerySelector;
import it.uniba.sotorrent.cliparse.QuerySelectorI;
import it.uniba.sotorrent.soqueries.SOQueryI;


/**
 * <<Control>>
 * The main class of SNA4SO.
 * 
 * <b>DO NOT RENAME</b>
 */

public final class AppMain {

	/**
	 * Default constructor.
	 */
	private AppMain() {

	}

	/**
	 * This is the main entry of the application.
	 *
	 * @param args The command-line arguments.
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws URISyntaxException 
	 * @throws GeneralSecurityException 
	 * 
	 */
	public static void main(final String[] args) throws IOException, InterruptedException, GeneralSecurityException, URISyntaxException {

		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		//Build query
		String query = "";

		//Take valid values from args.
		CLItoParametersI parse = new CLItoParameters();
		ParameterSet params = parse.parseCLI(args);

		//Select the right query by parameters
		QuerySelectorI selector = new QuerySelector(params);

		//Get query
		SOQueryI soq = selector.selectQuery();
		query = soq.getQueryString();

		//Take the result of query job
		TableResult res = null;

		//BigQuery Service
		GoogleBigQueryI bigquery = new GoogleBigQueryUtils();

		//Run query
		Job job = bigquery.runQuery(query);

		//Get result
		res = bigquery.getResults(job);


		//Set name sheet
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			buf.append(args[i]);
		}
		String sheetName = buf.toString();


		//Write sheet with results
		GoogleDocI ut = new GoogleDocUtils();

		String spid = ut.createSheet("SNA4SO - " + sheetName);

		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);


	}

}
