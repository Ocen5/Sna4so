package soqueries.day.decorator;

import soqueries.day.Day;
import soqueries.day.component.QuestionEdgeDay;


public class QuestionWeightDay extends Day {


	public QuestionWeightDay(Integer year, Integer month, Integer day, Integer lim) {// da chiamare DayWeight

		super(year, month, day, lim);
	}


	public String getQueryString() {
		QuestionEdgeDay edge=new QuestionEdgeDay(getYear(),getMonth(),getDay(),getLim());
		edge.getQuery().setSelect(edge.getQuery().getSelect() 
				+ ", COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight");
		return edge.getQuery().toString();
	}
}

