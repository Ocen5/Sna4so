package soqueries.user.decorator;

import soqueries.SOQueryI;
import soqueries.user.User;
/**
 * <<entity>>
 * This class implements SOQueryI for queries with user, limit.
 */
public class WeightUser implements SOQueryI {

	private User edge;
	public WeightUser(final User query) {

		super();
		edge = query;

	}

	/**
	 * @return String with field weight of query
	 */
	public String getQueryString() {

		edge.getQuery().setSelect(edge.getQuery().getSelect()
		+ ", COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight");
		return edge.getQuery().toString();

	}

}
