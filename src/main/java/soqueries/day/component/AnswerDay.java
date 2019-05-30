package soqueries.day.component;


import soqueries.Query;
import soqueries.day.Day;

public class AnswerDay extends Day {
	private Query query = new Query();

	public AnswerDay(Integer year, Integer month, Integer day, Integer lim) {

		super(year, month, day, lim);
		buildQuery();

	}

	private void buildQuery() {
		query.setSelect("owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_answers`");
		query.setWhere("extract(year from creation_date)=" + getYear() +
				" AND " + "extract(month from creation_date)=" + getMonth() +
				" AND " + "extract(day from creation_date)=" + getDay() +
				" AND " + "owner_user_id > 0");
		query.setGroup("owner_user_id");
		query.setOrder("owner_user_id ASC");
		query.setLimit(getLim());
	}
	
	public Query getQuery() {
		return query;
	}
	@Override
	public String getQueryString() {
		return query.toString();
	}

}

