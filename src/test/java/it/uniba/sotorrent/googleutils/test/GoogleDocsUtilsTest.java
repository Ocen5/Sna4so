package it.uniba.sotorrent.googleutils.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.googleutils.GoogleBigQueryI;
import it.uniba.sotorrent.googleutils.GoogleBigQueryUtils;
import it.uniba.sotorrent.googleutils.GoogleDocUtils;
import it.uniba.sotorrent.soqueries.user.component.QuestionEdgeUser;



class GoogleDocsUtilsTest {



	private static final String URL = "http://neo.di.uniba.it/credentials/project-codd-we445rt.json";


	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.DRIVE);


	private static final String APPLICATION_NAME = "sna4so-237908";


	private static GoogleDocUtils ut= new GoogleDocUtils();
	private static Sheets sheetsService = null;

	private static TableResult res;
	private static Credential authCred;
	private static Drive driveService;
	private static String spid;

	@BeforeAll
	static void Set() throws JobException, InterruptedException, FileNotFoundException, IOException, GeneralSecurityException, URISyntaxException {
		//query fasulla per provare la scrittura sul foglio

		
		spid = ut.createSheet("non nullo");
		authCred= GoogleCredential.fromStream(new URL(URL).openStream()).toBuilder()
				.setServiceAccountScopes(SCOPES).build();

		sheetsService = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), authCred)
				.setApplicationName(APPLICATION_NAME).build();

		driveService = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), authCred)
				.setApplicationName(APPLICATION_NAME).build();

		//query fasulla per provare la scrittura sul foglio
		QuestionEdgeUser soqf= new QuestionEdgeUser(1111,10);
		GoogleBigQueryI gb =new GoogleBigQueryUtils();
		Job queryJobf= gb.runQuery(soqf.getQueryString());
		
		res= queryJobf.getQueryResults();
		try {
			ut.shareSheet(spid);
			ut.getSheetByTitle(spid);
			ut.writeSheet(sheetsService.spreadsheets().get(spid).getSpreadsheetId() , res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCreateSheet() throws IOException, InterruptedException {
		String stringa1=sheetsService.spreadsheets().get(spid).getSpreadsheetId();
		String stringa2=ut.createSheet("title") ;
		assertNotEquals(stringa1,stringa2 );
		assertNotNull(stringa2);
	}

	@Test
	void testGetSheetByTitle() throws IOException {
		assertThrows( IOException.class, () -> ut.getSheetByTitle(""));
	}

	@Test
	void testWriteSheet() throws IOException, GeneralSecurityException, URISyntaxException{
		
		assertFalse(sheetsService.spreadsheets().get(spid).isEmpty(),"Foglio vuoto");
	} 

	@Test
	void testShareSheet() throws IOException, GeneralSecurityException, URISyntaxException {
		assertThrows( IOException.class, () -> ut.shareSheet(""));
	}

	@AfterAll
	static void tearDown() throws Exception {
	spid=null;
	authCred=null;
	sheetsService=null;
	driveService=null;
	ut=null;
	res=null;	
	}

}
