package it.uniba.sotorrent.soqueries.user.decorator;

import it.uniba.sotorrent.soqueries.SOQueryI;
import it.uniba.sotorrent.soqueries.user.User;

/**
 * <<entity>>
 * This class implements SOQueryI for queries with user, limit.
 */
public class WeightUser implements SOQueryI {

	/**
	 * The edge user query needed for add the weight of edge
	 */
	private User edge;
	
	/**
	 * Constructor that set User parameter
	 * @param query the user query with edge
	 */
	
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
