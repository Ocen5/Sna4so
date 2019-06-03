package it.uniba.sotorrent.soqueries.day.decorator;

import it.uniba.sotorrent.soqueries.day.Day;
import it.uniba.sotorrent.soqueries.day.component.AnswerDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionDay;

/**
 * Entity
 * This class extends Day to run query from posts table with year, month, day and limit.
 */
public class PostDay extends Day {

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param day 	The day parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
	public PostDay(final Integer year, final Integer month, final Integer day, final Integer lim) {

		super(year, month, day, lim);

	}

	/**
	 * @return String that is the union of answer and question queries.
	 */
	public String getQueryString() {

		QuestionDay question = new QuestionDay(getYear(), getMonth(), getDay(), getLim());
		AnswerDay answer = new AnswerDay(getYear(), getMonth(), getDay(), getLim());
		return question.getQuery().toUnionDistinct(answer.getQuery());

	}

}
