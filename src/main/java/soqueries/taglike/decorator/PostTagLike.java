package soqueries.taglike.decorator;

import soqueries.taglike.TagLike;
import soqueries.taglike.component.AnswerTagLike;
import soqueries.taglike.component.QuestionTagLike;

public class PostTagLike extends TagLike{
	
	public PostTagLike(Integer year, Integer month, String taglike, Integer lim) {
		super(year, month, taglike, lim);
	}

	@Override
	public String getQueryString() {
		QuestionTagLike question= new QuestionTagLike(getYear() ,getMonth(),getTaglike(),getLim());
		AnswerTagLike answer =new AnswerTagLike(getYear() ,getMonth(),getTaglike(),getLim());
		return question.getQuery().toUnionDistinct(answer.getQuery());
	}
}
