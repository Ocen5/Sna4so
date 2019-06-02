package it.uniba.sotorrent.soqueries.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import it.uniba.sotorrent.soqueries.Query;

class QueryTest {
	
	private static Query testQuery;
	
	@BeforeAll
	static void setUp() {
		testQuery = new Query();
		testQuery.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		testQuery.setWhere("extract(year from creation_date)=2016");
		testQuery.setGroup("owner_user_id");
		testQuery.setOrder("owner_user_id");
		testQuery.setLimit(100);
	}

	@Test
	void testGetFrom() {
		assertEquals(testQuery.getFrom(),"`bigquery-public-data.stackoverflow.posts_questions`");
	}

	@Test
	void testGetWhere() {
		assertEquals(testQuery.getWhere(),"extract(year from creation_date)=2016");
	}

	@Test
	void testGetGroup() {
		assertEquals(testQuery.getGroup(),"owner_user_id");
	}

	@Test
	void testGetOrder() {
		assertEquals(testQuery.getOrder(),"owner_user_id");
	}

	@Test
	void testGetLimit() {
		assertTrue(testQuery.getLimit() == 100);
	}

}
