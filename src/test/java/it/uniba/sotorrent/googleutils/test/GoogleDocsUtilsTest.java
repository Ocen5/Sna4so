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
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.googleutils.GoogleDocUtils;
import it.uniba.sotorrent.soquery.SOQueryQuestionUsrEdge;



class GoogleDocsUtilsTest {



	private static final String URL = "http://neo.di.uniba.it/credentials/project-codd-we445rt.json";


	private static final List<String> SCOPES = Arrays.asList(SheetsScopes.DRIVE);


	private static final String APPLICATION_NAME = "sna4so-237908";


	private GoogleDocUtils ut= new GoogleDocUtils();
	private Sheets sheetsService = null;

	private TableResult res;
	private Credential authCred;
	private Drive driveService;
	private String spid;

	@BeforeEach
	void Set() throws JobException, InterruptedException, FileNotFoundException, IOException, GeneralSecurityException, URISyntaxException {
		//query fasulla per provare la scrittura sul foglio

		
		spid = ut.createSheet("non nullo");
		this.authCred= GoogleCredential.fromStream(new URL(URL).openStream()).toBuilder()
				.setServiceAccountScopes(SCOPES).build();

		this.sheetsService = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), authCred)
				.setApplicationName(APPLICATION_NAME).build();

		this.driveService = new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(),
				JacksonFactory.getDefaultInstance(), authCred)
				.setApplicationName(APPLICATION_NAME).build();

		//query fasulla per provare la scrittura sul foglio
		SOQueryQuestionUsrEdge soqf= new SOQueryQuestionUsrEdge(1111,10);
		Job queryJobf = soqf.runQuery();
		this.res= queryJobf.getQueryResults();
		try {
			this.ut.shareSheet(spid);
			this.ut.getSheetByTitle(spid);
			this.ut.writeSheet(sheetsService.spreadsheets().get(spid).getSpreadsheetId() , res);
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

	@AfterEach
	void tearDown() throws Exception {
	spid=null;
	authCred=null;
	sheetsService=null;
	driveService=null;
	ut=null;
	res=null;	
	}

}
