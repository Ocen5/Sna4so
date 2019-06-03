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
 * Control
 * The main class of SNA4SO.
 * Author codd.
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
	 * 
	 */
	public static void main(final String[] args) {

		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		//Build query
		String query = "";

		try {
			//Take valid values from args.
			CLItoParametersI parse = new CLItoParameters();
			ParameterSet params = parse.parseCLI(args);

			//Select the right query by parameters
			QuerySelectorI selector = new QuerySelector(params);

			//Get query
			SOQueryI soq = selector.selectQuery();
			query = soq.getQueryString();

		} catch (IllegalArgumentException e) {
			System.err.println("Input parameters inserted are not valid!");
			System.out.println("Valids parameters are : \n"
					+ "For query with days:'yyyy=[2008-2019]', "
					+ "'mm=[1-12]', 'dd=[1-31]', 'limit=[0-500]', "
					+ "'type=[question-answer-post]', "
					+ "'edge=[yes-no]', 'weight=[yes-no]' \n"
					+ "For query with taglike:'yyyy=[2008-2019]', "
					+ "'mm=[1-12]', 'taglike=[any tag]', 'limit=[0-500]', "
					+ "'type=[question-answer-post]', "
					+ "'edge=[yes-no]', 'weight=[yes-no]' \n"
					+ "For query with user:'user=[1-11142405]', "
					+ "'limit=[0-500]', 'type=[question-answer]', "
					+ "'edge=[yes]', 'weight=[yes-no]' \n"
					);
		}

		//Take the result of query job
		TableResult res = null;

		try {
			//BigQuery Service
			GoogleBigQueryI bigquery = new GoogleBigQueryUtils();

			//Run query
			Job job = bigquery.runQuery(query);

			//Get result
			res = bigquery.getResults(job);

		} catch (FileNotFoundException e1) {
			System.err.println("Connection to credential file failed!");

		} catch (IOException e1) {
			System.err.println("Connection to google bigquery service failed failed!");

		} catch (InterruptedException e) {
			System.err.println("Connection to google bigquery service failed failed!");

		} catch (BigQueryException e) {
			System.err.println("Query Error!");
			System.out.println(query);
			e.printStackTrace();

		} catch (JobException e) {
			System.out.println("Error when waiting query job!");

		}


		//Set name sheet
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
			buf.append(args[i]);
		}
		String sheetName = buf.toString();


		//Write sheet with results
		GoogleDocI ut = new GoogleDocUtils();

		try {
			String spid = ut.createSheet("SNA4SO - " + sheetName);

			ut.shareSheet(spid);
			ut.getSheetByTitle(spid);
			ut.writeSheet(spid, res);

		} catch (IOException e) {
			System.err.println("Failed to read the credential!");
			e.printStackTrace();

		} catch (GeneralSecurityException e) {
			System.err.println("Failed to connect with Google spreadsheet!");

		} catch (URISyntaxException e) {
			System.err.println("The URL syntax is malformed!");
			e.printStackTrace();

		}
	}

}
