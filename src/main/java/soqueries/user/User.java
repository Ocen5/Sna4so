package soqueries.user;

import soqueries.Query;
import soqueries.SOQueryI;
/**
 * <<entity>>
 * This class implements SOQueryI for queries with user, limit.
 */
public abstract class User implements SOQueryI {

	private Integer userId;
	private Integer lim;

	/**
	 * Default constructor, init variables of the query.
	 * @param usrId The user parameter to be inserted in the query.
	 * @param lim The limit parameter to be inserted in the query.
	**/
	public User(final Integer usrId, final Integer limit) {

		super();
		this.userId = usrId;
		this.lim = limit;

	}

	/**
	 * @return userId To indicate the user of query's tuples.
	 */
	public Integer getUserId() {

		return userId;

	}

	/**
	 * @return lim To indicate the limit of query's tuples.
	 */
	public Integer getLim() {

		return lim;

	}

	public abstract Query getQuery();

}
