package soqueries.taglike.decorator;

import soqueries.taglike.TagLike;
import soqueries.taglike.component.AnswerTagLike;
import soqueries.taglike.component.QuestionTagLike;
/**
 * <<entity>>
 * This class extends TagLike for run query from posts table with year, month, taglike, limit.
 */
public class PostTagLike extends TagLike {

	/**
	 * Default constructor, init variables of the query.
	 * @param year	The year parameter to be inserted in the query.
	 * @param month	The month parameter to be inserted in the query.
	 * @param taglike The taglike parameter to be inserted in the query.
	 * @param lim 	The limit parameter to be inserted in the query.
	 */
	public PostTagLike(final Integer year, final Integer month, final String taglike, final Integer lim) {

		super(year, month, taglike, lim);

	}

	/**
	 * @return String that is union of two queries
	 */
	public String getQueryString() {

		QuestionTagLike question = new QuestionTagLike(getYear(), getMonth(), getTaglike(), getLim());
		AnswerTagLike answer = new AnswerTagLike(getYear(), getMonth(), getTaglike(), getLim());
		return question.getQuery().toUnionDistinct(answer.getQuery());

	}
}
