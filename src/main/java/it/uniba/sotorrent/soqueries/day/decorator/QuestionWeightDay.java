package it.uniba.sotorrent.soqueries.day.decorator;

import it.uniba.sotorrent.soqueries.day.Day;
import it.uniba.sotorrent.soqueries.day.component.QuestionEdgeDay;

/**
 * Entity
 * This class extends Day to run query from questions table with year, month, day and limit (weight).
 */
public class QuestionWeightDay extends Day {

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param day 	The day parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	**/
public QuestionWeightDay(final Integer year, final Integer month, final Integer day, final Integer lim) {

		super(year, month, day, lim);

	}

	/**
	 * @return String with field weight of query.
	 */
	public String getQueryString() {

		QuestionEdgeDay edge = new QuestionEdgeDay(getYear(), getMonth(), getDay(), getLim());
		edge.getQuery().setSelect(edge.getQuery().getSelect()
		 + ", COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight");
		return edge.getQuery().toString();

	}

}
