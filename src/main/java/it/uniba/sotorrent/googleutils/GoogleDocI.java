package it.uniba.sotorrent.googleutils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;

import com.google.cloud.bigquery.TableResult;

/**
 * Boundary
 * Utility interface to create, share and delete Google spreadsheet.
 * For more, refer to <a href="https://developers.google.com/sheets/api/samples/">this documentation</a>.
 * Author codd
 */
public interface GoogleDocI {

	/**
	 * Creates a new sheet on every execution.
	 * 
	 * @param title Spreadsheet title.
	 * @return The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 */
	String createSheet(String title) throws IOException;

	/**
	 * Returns the spreadsheet id by title.
	 * 
	 * @param spid The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 */
	void getSheetByTitle(String spid) throws IOException;

	/**
	 * Write results to the spreadsheet.
	 * Also, see <a href="https://developers.google.com/sheets/api/guides/values">here</a>.
	 * 
	 * @param spid The spreadsheet id.
	 * @param res The hash map of the results, with URL as key and view count as value.
	 * @throws IOException Generic I/O error.
	 */
	void writeSheet(String spid, TableResult res) throws IOException;

	/**
	 * Makes the spreadsheet readable to anyone with the link.
	 * 
	 * @param spid The spreadsheet id.
	 * @throws IOException Generic I/O error.
	 * @throws GeneralSecurityException Failed authentication.
	 * @throws URISyntaxException Malformed URI.
	 */
	void shareSheet(String spid) throws IOException, GeneralSecurityException, URISyntaxException;

}
