package soqueries.day.component;


import soqueries.Query;
import soqueries.day.Day;

public class QuestionEdgeDay extends Day {
	private Query query = new Query();
	
	public QuestionEdgeDay(Integer year, Integer month, Integer day, Integer lim) {

		super(year, month, day, lim);
		buildQuery();

	}

	private void buildQuery() {


		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id as `from`, " 
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions` " 
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id"
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("extract(year from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + getYear() +
				" AND " + "extract(month from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + getMonth() +
				" AND " + "extract(day from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=" + getDay() +
				" AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id >0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC");
		query.setLimit(getLim());
	}


	public String getQueryString() {

		return query.toString();
	}
	public Query getQuery() {
		return query;
	}

}

