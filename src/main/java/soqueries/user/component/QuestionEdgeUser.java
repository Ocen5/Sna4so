package soqueries.user.component;

import soqueries.Query;
import soqueries.user.User;

public class QuestionEdgeUser extends User {
	private Query query = new Query();
	
	public QuestionEdgeUser( Integer userId, Integer lim) {
		super(userId, lim);
		buildQuery();
	}
	
	public void buildQuery() {
		
		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`");
		query.setFrom(" `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id "
				+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id= " + getUserId() +
				" AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 " 
				+ " AND " + "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, " 
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC");
		query.setLimit(getLim());
	}

	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return query.toString();
	}

	@Override
	public Query getQuery() {
		// TODO Auto-generated method stub
		return query;
	}
}
