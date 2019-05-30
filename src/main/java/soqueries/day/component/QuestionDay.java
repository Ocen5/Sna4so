package soqueries.day.component;

import soqueries.Query;
import soqueries.day.Day;

public class QuestionDay extends Day {
	private Query query = new Query();
	
	public QuestionDay(Integer year, Integer month, Integer day, Integer lim) {

		super(year, month, day, lim);
		buildQuery();

	}


	public void buildQuery() {

		Query questionday = this.getQuery();
		questionday.setSelect("owner_user_id");
		questionday.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		questionday.setWhere("extract(year from creation_date)=" + getYear() +
				" AND " + "extract(month from creation_date)=" + getMonth() +
				" AND " + "extract(day from creation_date)=" + getDay() +
				" AND " + "owner_user_id > 0");
		questionday.setGroup("owner_user_id");
		questionday.setOrder("owner_user_id ASC");
		questionday.setLimit(getLim());

	}
	
	public Query getQuery() {
		return query;
	}

	@Override
	public String getQueryString() {
		return query.toString();
	}
}

