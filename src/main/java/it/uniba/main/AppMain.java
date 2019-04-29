package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.ISOQuery;
import it.uniba.sotorrent.SOQueryAnswerDay;
import it.uniba.sotorrent.SOQueryAnswerTags;
import it.uniba.sotorrent.SOQueryPostDay;
import it.uniba.sotorrent.SOQueryPostTags;
import it.uniba.sotorrent.SOQueryQuestionDay;
import it.uniba.sotorrent.SOQueryQuestionTags;


/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */

public final class AppMain {

	/*
	 * Vector with valid parameters for queries.
	 * At position:
	 * 0 yyyy
	 * 1 mm
	 * 2 dd
	 * 3 taglike
	 * 4 limit.
	 */
	private static String[] values = new String[5];

	/*
	 * Valid parameter type to choose query.
	 */
	private static String type = new String();


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
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */

	public static void main(final String[] args) throws FileNotFoundException,
	IOException,
	InterruptedException,
	GeneralSecurityException,
	URISyntaxException,
	IllegalArgumentException {
		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		getInput(args);				//values 0:yyyy		1:mm	2:dd	3:taglike	4:limit


		ISOQuery soq = null;
		String nameQuery = new String();
		String year = values[0],
				month = values[1],
				day = values[2],
				taglike = values[3],
				limit = values[4];

		switch (type) {
		case "question" :

			if (day != null && taglike == null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ "id utente che hanno fatto almeno una domanda il "
						+ year + "/" + month + "/" + day);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionDay();			
				break;
			}
			if (day == null && taglike != null) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno fatto almeno una domanda sull' argomento "
						+ taglike + " il " + year + "/" + month);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionTags();
				break;
			} else {
				IllegalArgumentException valuesException = new IllegalArgumentException(
						"Argomenti non validi.");
				throw valuesException;
			}

		case "answer" :

			if (day != null && taglike == null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ "id utente che hanno dato almeno una risposta il "
						+ year + "/" + month + "/" + day);
				System.out.println(nameQuery);
				soq = new SOQueryAnswerDay();
				break;
			}
			if (day == null && taglike != null) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno dato almeno una risposta sull' argomento "
						+ taglike + " il " + year + "/" + month);
				System.out.println(nameQuery);
				soq = new SOQueryAnswerTags();
				break;
			} else {
				IllegalArgumentException valuesException = new IllegalArgumentException(
						"Argomenti non validi.");
				throw valuesException;
			}

		case "post" :

			if (day != null && taglike == null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ "id utente che hanno fatto almeno un post il "
						+ year + "/" + month + "/" + day);
				System.out.println(nameQuery);
				soq = new SOQueryPostDay();
				break;
			}
			if (day == null && taglike != null) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno fatto almeno un post sull' argomento "
						+ taglike + " il " + year + "/" + month);
				System.out.println(nameQuery);
				soq = new SOQueryPostTags();
				break;
			} else {
				IllegalArgumentException valuesException = new IllegalArgumentException(
						"Argomenti non validi.");
				throw valuesException;
			}

		default :

			IllegalArgumentException typeException = new IllegalArgumentException(
					"Type query non valido.");
			throw typeException;

		}


		Job job = soq.runQuery(values);
		TableResult res = soq.getResults(job);

		GoogleDocsUtils ut = new GoogleDocsUtils();
		String spid = ut.createSheet("SNA4SO - " + nameQuery);
		ut.shareSheet(spid);
		ut.getSheetByTitle(spid);
		ut.writeSheet(spid, res);

	}

	/**
	 * 	 * This function sets valid input values for queries.
	 *
	 * @param args The command-line arguments.
	 * 
	 * Store values, at position:
	 * 0 year(yyyy)
	 * 1 month(mm)
	 * 2 day(dd)
	 * 3 taglike
	 * 4 limit
	 * 
	 * init type
	 * 
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */
	private static void getInput(final String[] args)  throws IllegalArgumentException {

		for (int i = 0; i < args.length; i++) {
			if (args[i].contains("yyyy=")) {
				values[0] = args[i].substring(5);
			} else {
				if (args[i].contains("mm=")) {
					values[1] = args[i].substring(3);
				} else {
					if (args[i].contains("dd=")) {
						values[2] = args[i].substring(3);
					} else {
						if (args[i].contains("taglike=")) {
							values[3] = args[i].substring(8);
						} else {
							if (args[i].contains("limit=")) {
								values[4] = args[i].substring(6);
							} else {
								if (args[i].contains("type=")) {
									type = args[i].substring(5);
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
