package it.uniba.sotorrent.soqueries.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.uniba.sotorrent.soqueries.user.User;
import it.uniba.sotorrent.soqueries.user.component.AnswerEdgeUser;
import it.uniba.sotorrent.soqueries.user.component.QuestionEdgeUser;
import it.uniba.sotorrent.soqueries.user.decorator.WeightUser;

class UserTest {
	/**
	 * Queries used for test classes
	 */
	private QueryToTest queries = new QueryToTest();
	
	/**
	 * Parameters of queries
	 */
	private static Integer user = 86;
	private static Integer limit = 100;
	
	/**
	 * Queries which will be tested
	 */
	private static User AnswerEdgeUser;		//10
	private static User QuestionEdgeUser;	//2
	private static WeightUser AnswerWeightUser;	//11
	private static WeightUser QuestionWeightUser;	//1
	
	@BeforeAll
	static void setUp() {
		AnswerEdgeUser = new AnswerEdgeUser(user,limit);
		QuestionEdgeUser = new QuestionEdgeUser(user,limit);
		AnswerWeightUser = new WeightUser(AnswerEdgeUser);
		QuestionWeightUser = new WeightUser(QuestionEdgeUser);
	}
	
	@Test
	void test() {
		assertEquals(AnswerEdgeUser.getQueryString(),QueryToTest.getTestQuery(10),"wrong AnswerEdgeUser");
		assertEquals(QuestionEdgeUser.getQueryString(),QueryToTest.getTestQuery(2),"wrong QuestionEdgeUser");
		assertEquals(AnswerWeightUser.getQueryString(),QueryToTest.getTestQuery(12),"wrong AnswerWeightUser");
		assertEquals(QuestionWeightUser.getQueryString(),QueryToTest.getTestQuery(1),"wrong QuestionWeightUser");
	}

}
