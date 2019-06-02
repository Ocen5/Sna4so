package it.uniba.sotorrent.cliparse.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.uniba.sotorrent.cliparse.CLItoParameters;

class CLItoParametersTest {

	private static CLItoParameters cli;
	private static String args[]= new String[8];

	@BeforeAll
	static void setUp() throws Exception {

		cli= new CLItoParameters();
		//inizializzazioni valori corrette
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="dd=23";//day
		args[3]="limit=10";//limit
		args[4]="edge=yes";//edge
		args[5]="user=1234";//user
		args[6]="weight=yes";//weight
		args[7]="type=question";//type
	}

	@AfterAll
	static void tearDown() throws Exception {
		args[0]="";//year
		args[1]="";//month
		args[2]="";//day
		args[3]="";//limit
		args[4]="";//edge
		args[5]="";//user
		args[6]="";//weight
		args[7]="";//type
	}

	@Test
	void test() {

		assertNotNull(cli.parseCLI(args));
		Setlimitup();
		Exception e = new IllegalArgumentException();
		assertThrows(e.getClass(),()-> cli.parseCLI(args));
		Setlimitdown();

		Exception e2 = new IllegalArgumentException();
		assertThrows(e2.getClass(),()-> cli.parseCLI(args));



	}
	void Setlimitdown() {
		//inizializzazioni valori limit minimi
		args[0]="yyyy=2005";//year
		args[1]="mm=0";//month
		args[2]="dd=0";//day
		args[3]="limit=0";//limit
		args[4]="edge=no";//edge
		args[5]="user=0";//user
		args[6]="weight=no";//weight
		args[7]="type=no";//type

	}
	void Setlimitup() {
		//inizializzazioni valori limit massimi
		args[0]="yyyy=2025";//year
		args[1]="mm=34";//month
		args[2]="dd=34";//day
		args[3]="limit=600";//limit
		args[4]="edge=";//edge
		args[5]="user=11142406";//user
		args[6]="weight=";//weight
		args[7]="type=";//type
	}
}
