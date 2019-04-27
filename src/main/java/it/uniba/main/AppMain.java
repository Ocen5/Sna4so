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

/**
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

		String[] values = getInput(args);				//0:yyyy	1:mm	2:dd	3:taglike	4:limit		5:type


		ISOQuery soq = null;

		if(values[2] != null && values[3] == null)            //Query con DAY
			soq = new SOQuery();							//SOSTITUIRE CON LA TIPOLOGIA DI QUERY APPROPRIATA E PASSARE VALUES



		if(values[2] == null && values[3] != null)           //Query con TAGLIKE
			soq = new SOQuery();							//SOSTITUIRE CON LA TIPOLOGIA DI QUERY APPROPRIATA E PASSARE VALUES



		Job job = soq.runQuery();
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
	 * 	 * This function returns valid input values for queries.
	 *
	 * @param args The command-line arguments.
	 * 
	 * @return values: 
	 * @0 year(yyyy)
	 * @1 month(mm)
	 * @2 day(dd)
	 * @3 taglike
	 * @4 limit
	 * @5 type
	 * 
	 * @throws IllegalArgumentException See stack trace for proper location.
	 */
	private static String[] getInput(String[] args)  throws IllegalArgumentException
	{

		String[] values = new String[6];


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
									values[5]= args[i].substring(5);
									System.out.println(values[5]);
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

	return values;

}


}
