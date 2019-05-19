package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Scanner;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.SOQuerySelector;
import it.uniba.sotorrent.soquery.ISOQuery;


/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */

public final class AppMain {

	/**
	 * Valid parameters for queries.
	 * 
	 * yyyy 	The year for the WHERE clause of the query.
	 * mm 		The month for the WHERE clause of the query.
	 * dd 		The day for the WHERE clause of the query.
	 * limit 	The limit of tuples.
	 * user		The id of the user of a question/answer.
	 */
	private static Integer yyyy, mm, dd, limit,user;

	/**
	 * type 	The type to choose the query.
	 * taglike 	The argument of posts for the WHERE clause of the query.
	 */
	private static String type, taglike;

	/**
	 * edge 	The type to visualize the graphs.
	 * weight 	The command to visualize the weight of the graphs.
	 */

	private static Boolean edge, weight = false;

	/**
	 * Private constructor. Init varibles from args input.
	 * 
	 * @param args The command-line arguments.
	 * 
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */
	private AppMain(final String[] args)  throws IllegalArgumentException {

		for (int i = 0; i < args.length; i++) {
			if (args[i].contains("yyyy=")) {
				yyyy = Integer.valueOf(args[i].substring(5));
			} else {
				if (args[i].contains("mm=")) {
					mm = Integer.valueOf(args[i].substring(3));
				} else {
					if (args[i].contains("dd=")) {
						dd = Integer.valueOf(args[i].substring(3));
					} else {
						if (args[i].contains("taglike=")) {
							taglike = args[i].substring(8);
						} else {
							if (args[i].contains("limit=")) {
								limit = Integer.valueOf(args[i].substring(6));
							} else {
								if (args[i].contains("type=")) {
									type = args[i].substring(5);
								} else {
									if (args[i].contains("edge=")) {
										if (args[i].substring(5).equals("yes")) 
											edge = true;
									} else {
										if (args[i].contains("user=")) {
											user= Integer.valueOf(args[i].substring(5));
										}else {
											if (args[i].contains("weight=")) {
												if (args[i].substring(7).equals("yes")) 
													weight = true;
											} else {
												IllegalArgumentException exception = new IllegalArgumentException(
														"Parametro di input non valido.");
												throw exception;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
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
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */

	public static void main(final String[] args) throws FileNotFoundException,
	IOException,
	InterruptedException,
	GeneralSecurityException,
	URISyntaxException,
	IllegalArgumentException {
		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		//Take valid values from args, see AppMain constructor for further information.
		new AppMain(args);
		
		//selector prende in input una HashMap vuota temporanea finchè non sarà implementata la classe CLI4SOQuery
		SOQuerySelector selector= new SOQuerySelector(new HashMap<String,Object>() ) ;
		ISOQuery soq = selector.getQuery();
		String nameQuery = selector.getNameQuery();

		Job job = soq.runQuery();
		TableResult res = soq.getResults(job);

		GoogleDocsUtils ut = new GoogleDocsUtils();
		String spid = ut.createSheet("SNA4SO - " + nameQuery);
		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);

		/*
		 * Parte provvisioria da eliminare alla fine del progetto
		 * elimina lo spreadsheet dopo aver premuto invio
		 */

		Scanner sc = new Scanner(System.in);
		System.out.print("Premi invio per continuare. ");
		sc.nextLine();
		ut.deleteSheet(spid);
		System.out.println("Foglio eliminato");
		sc.close();
	}


}