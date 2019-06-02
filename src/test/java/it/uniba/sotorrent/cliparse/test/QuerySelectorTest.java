package it.uniba.sotorrent.cliparse.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniba.sotorrent.cliparse.CLItoParameters;
import it.uniba.sotorrent.cliparse.QuerySelector;
import it.uniba.sotorrent.soqueries.day.component.AnswerDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionEdgeDay;
import it.uniba.sotorrent.soqueries.day.decorator.PostDay;
import it.uniba.sotorrent.soqueries.day.decorator.QuestionWeightDay;
import it.uniba.sotorrent.soqueries.taglike.component.AnswerTagLike;
import it.uniba.sotorrent.soqueries.taglike.component.QuestionTagLike;
import it.uniba.sotorrent.soqueries.taglike.decorator.PostTagLike;
import it.uniba.sotorrent.soqueries.user.component.AnswerEdgeUser;
import it.uniba.sotorrent.soqueries.user.component.QuestionEdgeUser;
import it.uniba.sotorrent.soqueries.user.decorator.WeightUser;

class QuerySelectorTest {

	private static CLItoParameters cli=new CLItoParameters();

	private QuerySelector qs;

	@BeforeEach
	void setUp() throws Exception {
		String args[]= new String[9];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="dd=23";//day
		args[3]="limit=10";//limit
		args[4]="edge=yes";//edge
		args[5]="user=1234";//user
		args[6]="weight=yes";//weight
		args[7]="type=question";//type
		args[8]="taglike=java";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	private void queryAnswertag() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=answer";//type
		args[4]="taglike=java";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryAnswerweight() {
		String args[]= new String[6];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=answer";//type
		args[4]="taglike=java";//tag
		args[5]="weight=yes";//weight
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryPostWeight() {
		String args[]= new String[6];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=post";//type
		args[4]="dd=02";//tag
		args[5]="weight=yes";//weight
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryPostEdge() {
		String args[]= new String[6];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=post";//type
		args[4]="dd=02";//tag
		args[5]="edge=yes";//edge
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryQuestiontag() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=question";//type
		args[4]="taglike=java";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryPosttag() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=post";//type
		args[4]="taglike=java";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryQuestionday() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=question";//type
		args[4]="dd=02";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryAnswerday() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=answer";//type
		args[4]="dd=02";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryQuestionEdgeday() {
		String args[]= new String[6];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=question";//type
		args[4]="edge=yes";//edge
		args[5]="dd=02";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryPostday() {
		String args[]= new String[5];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=post";//type
		args[4]="dd=02";//tag
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryQuestionWeightday() {
		String args[]= new String[7];
		args[0]="yyyy=2015";//year
		args[1]="mm=11";//month
		args[2]="limit=10";//limit
		args[3]="type=question";//type
		args[4]="dd=02";//tag
		args[5]="edge=yes";//edge
		args[6]="weight=yes";//weight
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryAnsweruser(){
		String args[]= new String[4];
		args[0]="limit=10";//limit
		args[1]="edge=yes";//edge
		args[2]="user=1234";//user
		args[3]="type=answer";//type
		this.qs=new QuerySelector(cli.parseCLI(args));
	}

	private void queryQuestionWuser() {
		String args[]= new String[5];
		args[0]="limit=10";//limit
		args[1]="edge=yes";//edge
		args[2]="user=1234";//user
		args[3]="weight=yes";//weight
		args[4]="type=question";//type
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryAnswerWuser() {
		String args[]= new String[5];
		args[0]="limit=10";//limit
		args[1]="edge=yes";//edge
		args[2]="user=1234";//user
		args[3]="weight=yes";//weight
		args[4]="type=answer";//type
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void queryQuestionEdgeuser() {
		String args[]= new String[4];
		args[0]="limit=10";//limit
		args[1]="edge=yes";//edge
		args[2]="user=1234";//user
		args[3]="type=question";//type
		this.qs=new QuerySelector(cli.parseCLI(args));
	}
	private void selectWrongQuery() {
		String args[]= new String[4];
		args[0]="limit=10";//limit
		args[1]="edge=no";//edge
		args[2]="user=1";//user
		args[3]="type=question";//type
		this.qs=new QuerySelector(cli.parseCLI(args));
		
	}

	@Test
	void test() {
		assertThrows(IllegalArgumentException.class, ()-> qs.selectQuery(),"argomenti non validi");
		queryQuestionEdgeuser();
		assertEquals(QuestionEdgeUser.class, qs.selectQuery().getClass());
		assertAll("querytag",
				() -> queryQuestiontag(),
				() -> assertEquals(QuestionTagLike.class, qs.selectQuery().getClass()),
				() -> queryAnswertag(),
				() -> assertEquals(AnswerTagLike.class, qs.selectQuery().getClass()),
				() -> queryPosttag(),
				() -> assertEquals(PostTagLike.class, qs.selectQuery().getClass())
				);
		assertAll("queryday",
				() -> queryAnswerday(),
				() -> assertEquals(AnswerDay.class, qs.selectQuery().getClass()),
				() -> queryQuestionday(),
				() -> assertEquals(QuestionDay.class, qs.selectQuery().getClass()),
				() -> queryQuestionday(),
				() -> assertEquals(QuestionDay.class, qs.selectQuery().getClass()),
				() -> queryQuestionWeightday(),
				() -> assertEquals(QuestionWeightDay.class, qs.selectQuery().getClass()),
				() -> queryPostday(),
				() -> assertEquals(PostDay.class, qs.selectQuery().getClass()),
				() -> queryQuestionEdgeday(),
				() -> assertEquals(QuestionEdgeDay.class, qs.selectQuery().getClass())
				);
		assertAll("queryuser",
				() -> queryQuestionWuser(),
				() -> assertEquals(WeightUser.class, qs.selectQuery().getClass()),
				() -> queryAnswerWuser(),
				() -> assertEquals(WeightUser.class, qs.selectQuery().getClass()),
				() -> queryQuestionEdgeuser(),
				() -> assertEquals(QuestionEdgeUser.class, qs.selectQuery().getClass()),
				() -> queryAnsweruser(),
				() -> assertEquals(AnswerEdgeUser.class, qs.selectQuery().getClass()),
				() -> queryPostWeight(),
				() ->assertThrows(IllegalArgumentException.class, ()-> qs.selectQuery()),
				() -> queryPostEdge(),
				() ->assertThrows(IllegalArgumentException.class, ()-> qs.selectQuery()),
				()->queryAnswerweight(),
				() ->assertThrows(IllegalArgumentException.class, ()-> qs.selectQuery())
				);
		selectWrongQuery();
		assertThrows(IllegalArgumentException.class, ()-> qs.selectQuery());
	}


}
