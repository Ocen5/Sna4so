package it.uniba.sotorrent.soqueries.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.uniba.sotorrent.soqueries.taglike.TagLike;
import it.uniba.sotorrent.soqueries.taglike.component.AnswerTagLike;
import it.uniba.sotorrent.soqueries.taglike.component.QuestionTagLike;
import it.uniba.sotorrent.soqueries.taglike.decorator.PostTagLike;;

class TagLikeTest {

	/**
	 * Queries used for test classes
	 */
	private QueryToTest queries = new QueryToTest();
	
	/**
	 * Parameters for queries
	 */
	private static Integer year = 2016;
	private static Integer month = 2;
	private static String taglike = "java";
	private static Integer limit = 100;
	
	/**
	 * Queries wich will be tested
	 */
	private static TagLike AnswerTagLike;	//11
	private static TagLike PostTagLike;		//7
	private static TagLike QuestionTagLike; //3
	
	@BeforeAll
	static void setUp() {
		AnswerTagLike = new AnswerTagLike(year,month,taglike,limit);
		PostTagLike = new PostTagLike(year,month,taglike,limit);
		QuestionTagLike = new QuestionTagLike(year,month,taglike,limit);
	}
	
	@Test
	void test() {
		assertEquals(AnswerTagLike.getQueryString(),QueryToTest.getTestQuery(11),"wrong AnswerTagLike");
		assertEquals(PostTagLike.getQueryString(),QueryToTest.getTestQuery(7),"wrong PostTagLike");
		assertEquals(QuestionTagLike.getQueryString(),QueryToTest.getTestQuery(3),"wrong QuestionTagLike");
	}

}
