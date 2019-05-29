package it.uniba.sotorrent.googleutils.test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.cloud.bigquery.TableResult;

import it.uniba.sotorrent.googleutils.GoogleDocUtils;



class GoogleDocsUtilsTest {



	GoogleDocUtils ut= new GoogleDocUtils();


	private Spreadsheet spreadsheet;


	@BeforeEach
	void Set() {

		this.ut= new GoogleDocUtils();
		this.spreadsheet = new Spreadsheet();
		try {
			this.ut.createSheet("title");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testCreateSheet() throws IOException {
		String stringa1=spreadsheet.getSpreadsheetId();
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
		TableResult res=null;
		ut.writeSheet(spreadsheet.getSpreadsheetId(), res);
		assertTrue(spreadsheet.isEmpty(),"Foglio non vuoto");
	} 

	@Test
	void testShareSheet() throws IOException, GeneralSecurityException, URISyntaxException {
		assertThrows( IOException.class, () -> ut.shareSheet(""));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
