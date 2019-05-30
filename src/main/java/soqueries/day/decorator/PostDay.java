package soqueries.day.decorator;

import soqueries.day.Day;
import soqueries.day.component.AnswerDay;
import soqueries.day.component.QuestionDay;


public class PostDay extends Day {

	public PostDay(Integer year, Integer month, Integer day, Integer lim) {

		super(year, month, day, lim);

	}

	@Override
	public String getQueryString() {
		QuestionDay question= new QuestionDay(getYear() ,getMonth(),getDay(),getLim());
		AnswerDay answer =new AnswerDay(getYear() ,getMonth(),getDay(),getLim());
		return question.getQuery().toUnionDistinct(answer.getQuery());
	}



}

