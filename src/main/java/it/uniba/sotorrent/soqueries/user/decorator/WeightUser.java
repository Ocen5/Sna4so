package it.uniba.sotorrent.soqueries.user.decorator;

import it.uniba.sotorrent.soqueries.SOQueryI;
import it.uniba.sotorrent.soqueries.user.User;

/**
 * Entity
 * This class implements SOQueryI for queries with user and limit.
 */
public class WeightUser implements SOQueryI {

	/**
	 * The edge value.
	 */
	private User edge;

	/**
	 * Constructor sets User query.
	 * 
	 * @param query The user query with edge.
	 */
	public WeightUser(final User query) {

		super();
		edge = query;

	}

	/**
	 * @return the query in string format.
	 */
	public String getQueryString() {

		edge.getQuery().setSelect(edge.getQuery().getSelect()
		+ ", COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight");
		return edge.getQuery().toString();

	}

}
