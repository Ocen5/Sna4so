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
import it.uniba.sotorrent.SOQuery;
import it.uniba.sotorrent.SOQueryAnswerDay;
import it.uniba.sotorrent.SOQueryAnswerTags;
import it.uniba.sotorrent.SOQueryPostDay;
import it.uniba.sotorrent.SOQueryQuestionDay;
import it.uniba.sotorrent.SOQueryQuestionTags;

 
/**
 * The main class for the project. It must be customized to meet the project
 * assignment specifications.
 * 
 * <b>DO NOT RENAME</b>
 */

public final class AppMain {


	private static String[] values = new String[5];

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
	IllegalArgumentException 
	{
		System.out.println("Current working dir: " + System.getProperty("user.dir"));

		getInput(args);				//values 0:yyyy		1:mm	2:dd	3:taglike	4:limit


		ISOQuery soq = null;

		switch(type) {
		case "question" :
			
			if(values[2] != null && values[3] == null) {           //Query con DAY
				System.out.println("Seleziona i primi 100 id utente che hanno fatto "
									+ "almeno una domanda in un dato anno, mese e giorno");
			soq = new SOQueryQuestionDay();									
			break;
			}
			
			if(values[2] == null && values[3] != null) {          //Query con TAGLIKE
				System.out.println("Seleziona i primi 100 id utente che hanno fatto "
									+ "almeno una domanda su un dato argomento in un dato mese e anno");
			soq = new SOQueryQuestionTags();							//SOSTITUIRE CON LA TIPOLOGIA DI QUERY APPROPRIATA E PASSARE VALUES
			break;
			}
			else {
				IllegalArgumentException valuesException = new IllegalArgumentException("Argomenti non validi.");
				throw valuesException;
			}
			
		case "answer" :
			
			if(values[2] != null && values[3] == null) {           //Query con DAY
				System.out.println("Seleziona i primi 100 id utente che hanno dato "
									+ "almeno una risposta in un dato anno, mese e giorno");
			soq = new SOQueryAnswerDay();
			break;
			}
			
			if(values[2] == null && values[3] != null) {          //Query con TAGLIKE
				System.out.println("Seleziona i primi 100 id utente che hanno dato "
									+ "almeno una risposta su un dato argomento in un dato mese e anno");
			soq = new SOQueryAnswerTags();							//SOSTITUIRE CON LA TIPOLOGIA DI QUERY APPROPRIATA E PASSARE VALUES
			break;
			}
			else {
				IllegalArgumentException valuesException = new IllegalArgumentException("Argomenti non validi.");
				throw valuesException;
			}
			
		case "post" :
			
			if(values[2] != null && values[3] == null) {           //Query con DAY
				System.out.println("Seleziona i primi 100 id utente che hanno fatto "
									+ "almeno un post in un dato anno, mese e giorno");
			soq = new SOQueryPostDay();	
			break;
			}

			if(values[2] == null && values[3] != null) {         //Query con TAGLIKE
				System.out.println("Seleziona i primi 100 id utente che hanno fatto "
									+ "almeno un post su un dato argomento in un dato mese e anno");
			soq = new SOQuery();							//SOSTITUIRE CON LA TIPOLOGIA DI QUERY APPROPRIATA E PASSARE VALUES
			break;
			} 
			else {
				IllegalArgumentException valuesException = new IllegalArgumentException("Argomenti non validi.");
				throw valuesException;
			}
			
		default :
			
			IllegalArgumentException typeException = new IllegalArgumentException("Type query non valido.");
			throw typeException;

		}


		Job job = soq.runQuery(values);
		TableResult res = soq.getResults(job);

		String name = "";								//Stringa nome Sheet
		for(int i = 0; i < args.length; i++) {
			name += args[i] + " ";
		}



		GoogleDocsUtils ut = new GoogleDocsUtils();
		String spid = ut.createSheet("SNA4SO - Query Input: " + name);
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

	/**
	 * 	 * This function sets valid input values for queries.
	 *
	 * @param args The command-line arguments.
	 * 
	 * @init values: 
	 * @0 year(yyyy)
	 * @1 month(mm)
	 * @2 day(dd)
	 * @3 taglike
	 * @4 limit
	 * 
	 * @init type
	 * 
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */
	private static void getInput(String[] args)  throws IllegalArgumentException
	{

		for(int i = 0; i < args.length; i++) {
			if(args[i].contains("yyyy=")) {
				values[0]= args[i].substring(5);
				System.out.println(values[0]);
			}
			else {
				if(args[i].contains("mm=")) {
					values[1]= args[i].substring(3);
					System.out.println(values[1]);
				}

				else {
					if(args[i].contains("dd=")) {
						values[2]= args[i].substring(3);
						System.out.println(values[2]);
					}

					else {
						if(args[i].contains("taglike=")) {
							values[3]= args[i].substring(8);
							System.out.println(values[3]);
						}
						else {
							if(args[i].contains("limit=")) {
								values[4]= args[i].substring(6);
								System.out.println(values[4]);
							}

							else {
								if(args[i].contains("type=")) {
									type = args[i].substring(5);
									System.out.println(type);
								}
								else {
									IllegalArgumentException exception = new IllegalArgumentException("Parametro di input non valido.");
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
