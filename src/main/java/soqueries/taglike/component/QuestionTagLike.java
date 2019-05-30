package soqueries.taglike.component;

import soqueries.Query;
import soqueries.taglike.TagLike;

public class QuestionTagLike extends TagLike {
	private Query query = new Query();

	public QuestionTagLike(Integer year, Integer month, String taglike, Integer lim) {

		super(year, month, taglike, lim);
		buildQuery();

	}


	public void buildQuery() {

		query.setSelect("owner_user_id");
		query.setFrom("`bigquery-public-data.stackoverflow.posts_questions`");
		query.setWhere("extract(year from creation_date)=" + getYear() +
				" AND " + "extract(month from creation_date)=" + getMonth() +
				" AND " + "tags like CONCAT('%\"+ taglike +\"%') " + getTaglike() +
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

