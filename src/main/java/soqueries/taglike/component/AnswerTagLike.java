package soqueries.taglike.component;

import soqueries.Query;
import soqueries.taglike.TagLike;

public class AnswerTagLike extends TagLike {
	private Query query = new Query();
	
	public AnswerTagLike(Integer year, Integer month, String taglike, Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}

	private void buildQuery() {
		query.setSelect("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions`" 
			+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers`"  
			+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id" 
			+ "= `bigquery-public-data.stackoverflow.posts_answers`.parent_id");
		query.setWhere("extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=" + getYear() +
				" AND " + "extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=" + getMonth() +
				" AND " + "`bigquery-public-data.stackoverflow.posts_questions`.tags like " 
				+ " CONCAT('%\"+ taglike +\"%')" + getTaglike() +
				" AND " + "`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0");
		query.setGroup("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id");
		query.setOrder("`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC");
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

