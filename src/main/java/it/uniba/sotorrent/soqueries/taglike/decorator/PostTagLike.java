package it.uniba.sotorrent.soqueries.taglike.decorator;

import it.uniba.sotorrent.soqueries.taglike.TagLike;
import it.uniba.sotorrent.soqueries.taglike.component.AnswerTagLike;
import it.uniba.sotorrent.soqueries.taglike.component.QuestionTagLike;

/**
 * Entity
 * This class extends TagLike to run query from posts table with year, month, tags and limit.
 */
public class PostTagLike extends TagLike {

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param taglike The tag parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 */
	public PostTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);

	}

	/**
	 * @return String that is union of answer and question queries.
	 */
	public String getQueryString() {

		QuestionTagLike question = new QuestionTagLike(getYear(), getMonth(), getTaglike(), getLim());
		AnswerTagLike answer = new AnswerTagLike(getYear(), getMonth(), getTaglike(), getLim());
		answer.getQuery().setOrder("owner_user_id");
		return question.getQuery().toUnionDistinct(answer.getQuery());

	}

}
