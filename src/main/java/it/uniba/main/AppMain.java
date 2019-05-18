package it.uniba.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.GoogleDocsUtils;
import it.uniba.sotorrent.ISOQuery;
import it.uniba.sotorrent.SOQueryAnswerDay;
import it.uniba.sotorrent.SOQueryAnswerTags;
import it.uniba.sotorrent.SOQueryAnswerUsrEdge;
import it.uniba.sotorrent.SOQueryPostDay;
import it.uniba.sotorrent.SOQueryPostTags;
import it.uniba.sotorrent.SOQueryQuestionDay;
import it.uniba.sotorrent.SOQueryQuestionDayEdge;
import it.uniba.sotorrent.SOQueryQuestionDayWeight;
import it.uniba.sotorrent.SOQueryQuestionTags;
import it.uniba.sotorrent.SOQueryQuestionUsrEdge;
import it.uniba.sotorrent.SOQueryQuestionUsrWeight;


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


		ISOQuery soq = null;
		String nameQuery = new String();



		//choose the query type and run the query
		switch (type) {
		case "question" :

			if (dd != null && taglike == null && !edge && user== null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno fatto almeno una domanda il "
						+ yyyy + "/" + mm + "/" + dd);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionDay(yyyy, mm, dd, limit);
				break;
			}
			if (dd == null && taglike != null && !edge ) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno fatto almeno una domanda sull' argomento "
						+ taglike + " il " + yyyy + "/" + mm);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionTags(yyyy, mm, taglike, limit);
				break;
			}														//Query con edge con question
			if(edge && user== null && !weight) {
				nameQuery = new String("Seleziona le prime "
						+ limit
						+ " coppie (from,to) relative a domande poste il "
						+ yyyy + "/" + mm + "/" + dd );
				System.out.println(nameQuery);
				soq = new SOQueryQuestionDayEdge(yyyy, mm, dd, limit);
				break;
			}if (edge && user!= null && !weight ) {          //Query con edge user
				nameQuery = new String("Seleziona le prime "
						+ limit
						+ " coppie (from,to) relative a domande poste dall'utente "
						+ user);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionUsrEdge(user,limit);
				break;
			}if (edge && user== null &&weight ) {          //Query con edge user
				nameQuery = new String("Seleziona le prime "
						+ limit
						+ " triple (from,to,weight) relative a domande poste il \"\r\n" + 
						+ yyyy + "/" + mm + "/" + dd); 
				System.out.println(nameQuery);
				soq = new SOQueryQuestionDayWeight(yyyy, mm, dd, limit);
				break;
			}if (edge && user!= null &&weight ) {          //Query con weight user
				nameQuery = new String("Seleziona le prime "
						+ limit
						+ " triple (from,to,weight) relative a domande poste dall'utente "
						+ user);
				System.out.println(nameQuery);
				soq = new SOQueryQuestionUsrWeight(user,limit);
				break;
			}else {
				IllegalArgumentException valuesException = new IllegalArgumentException(
						"Argomenti non validi.");
				throw valuesException;
			}

		case "answer" :

			if (dd != null && taglike == null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ "id utente che hanno dato almeno una risposta il "
						+ yyyy + "/" + mm + "/" + dd);
				System.out.println(nameQuery);
				soq = new SOQueryAnswerDay(yyyy, mm, dd, limit);
				break;
			}
			if (dd == null && taglike != null) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno dato almeno una risposta sull' argomento "
						+ taglike + " il " + yyyy + "/" + mm);
				System.out.println(nameQuery);
				soq = new SOQueryAnswerTags(yyyy, mm, taglike, limit);
				break;
			}
			if (edge && user!= null ) {          //Query con edge user

				nameQuery = new String("Seleziona le prime "
						+ limit
						+ " coppie (from,to) relative a domande poste dall'utente "
						+ user);
				System.out.println(nameQuery);
				soq = new SOQueryAnswerUsrEdge(user,limit);
				break;
			}else {
				IllegalArgumentException valuesException = new IllegalArgumentException(
						"Argomenti non validi.");
				throw valuesException;
			}

		case "post" :

			if (dd != null && taglike == null) {           //Query con DAY
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ "id utente che hanno fatto almeno un post il "
						+ yyyy + "/" + mm + "/" + dd);
				System.out.println(nameQuery);
				soq = new SOQueryPostDay(yyyy, mm, dd, limit);
				break;
			}
			if (dd == null && taglike != null) {          //Query con TAGLIKE
				nameQuery = new String("Seleziona i primi "
						+ limit
						+ " id utente che hanno fatto almeno un post sull' argomento "
						+ taglike + " il " + yyyy + "/" + mm);
				System.out.println(nameQuery);
				soq = new SOQueryPostTags(yyyy, mm, taglike, limit);
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
