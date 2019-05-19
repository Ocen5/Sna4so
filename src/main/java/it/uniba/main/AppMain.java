package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.CLI4SOQuery;
import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.SOQuerySelector;
import it.uniba.sotorrent.soquery.ISOQuery;


/**
 * <<control>>
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */

public final class AppMain {

	/**
	 * Private constructor. Change if needed.
	 */
	private AppMain() {

	}

	/**
	 * 	 * This is the main entry of the application.
	 *
	 * @param args The command-line arguments. 
	 * 
	 * @throws FileNotFoundException See stack trace for proper location.
	 * @throws IOException  See stack trace for proper location.
	 * @throws InterruptedException  See stack trace for proper location.
	 * @throws GeneralSecurityException  See stack trace for proper location.
	 * @throws URISyntaxException  See stack trace for proper location.
	 * @throws Exception located in CLI4SOQuery.
	 *
	 */

	public static void main(final String[] args) throws FileNotFoundException, IOException, InterruptedException, Exception
	{

		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		//Take valid values from args.
		CLI4SOQuery params = new CLI4SOQuery(args);

		//Select the right query by parameters
		SOQuerySelector selector = new SOQuerySelector(params.getParameters());

		//Get query
		ISOQuery soq = selector.getQuery();
		String nameQuery = selector.getNameQuery();

		//Run query
		Job job = soq.runQuery();

		//Get result
		TableResult res = soq.getResults(job);

		//Write sheet with results
		GoogleDocsUtils ut = new GoogleDocsUtils();
		String spid = ut.createSheet("SNA4SO - " + nameQuery);
		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);

		/*
		 * Parte provvisioria da eliminare alla fine del progetto
		 * elimina lo spreadsheet dopo aver premuto invio
		 */
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Premi invio per continuare. ");
		sc.nextLine();
		ut.deleteSheet(spid);
		System.out.println("Foglio eliminato");
		sc.close();
		 */
	}


}