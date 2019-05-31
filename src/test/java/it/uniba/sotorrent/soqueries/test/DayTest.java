package it.uniba.sotorrent.soqueries.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.uniba.sotorrent.soqueries.day.Day;
import it.uniba.sotorrent.soqueries.day.component.AnswerDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionEdgeDay;
import it.uniba.sotorrent.soqueries.day.decorator.PostDay;
import it.uniba.sotorrent.soqueries.day.decorator.QuestionWeightDay;

class DayTest {
	
	/**
	 * Queries used for test classes
	 */
	private QueryToTest queries = new QueryToTest();
	
	/**
	 * Parameters of queries
	 */
	private static Integer year = 2016;
	private static Integer month = 2;
	private static Integer day = 11;
	private static Integer limit = 100;
	
	/**
	 * Queries which will be tested
	 */
	private static Day QuestionWeightDay;		//4
	private static Day QuestionEdgeDay;			//5
	private static Day QuestionDay;				//6
	private static Day PostDay;					//8
	private static Day AnswerDay;  				//9
	
	@BeforeAll
	static void setUp() {
		AnswerDay = new AnswerDay(year,month,day,limit);
		PostDay = new PostDay(year,month,day,limit);
		QuestionDay = new QuestionDay(year,month,day,limit);
		QuestionEdgeDay = new QuestionEdgeDay(year,month,day,limit);
		QuestionWeightDay = new QuestionWeightDay(year,month,day,limit);
	}
	
	@Test
	void testGetQueryString() {
		assertEquals(PostDay.getQueryString(),QueryToTest.getTestQuery(8),"wrong PostDay");
		assertEquals(AnswerDay.getQueryString(),QueryToTest.getTestQuery(9),"wrong AnswerDay");
		assertEquals(QuestionDay.getQueryString(),QueryToTest.getTestQuery(6),"wrong QuestionDay");
		assertEquals(QuestionEdgeDay.getQueryString(),QueryToTest.getTestQuery(5),"wrong QuestionEdgeDay");
		assertEquals(QuestionWeightDay.getQueryString(),QueryToTest.getTestQuery(4),"wrong QuestionWeightDay");
	}

}
