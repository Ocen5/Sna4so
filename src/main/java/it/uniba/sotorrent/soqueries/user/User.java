package it.uniba.sotorrent.soqueries.user;

import it.uniba.sotorrent.soqueries.Query;
import it.uniba.sotorrent.soqueries.SOQueryI;

/**
 * Entity
 * This class implements SOQueryI for queries with user and limit.
 */
public abstract class User implements SOQueryI {

	/**
	 * The user id value.
	 */
	private Integer userId;

	/**
	 * The limit value.
	 */
	private Integer lim;

	/**
	 * Constructor initializes variables for the query.
	 * 
	 * @param usrId The user parameter to be inserted in the query.
	 * @param limit The limit parameter to be inserted in the query.
	**/
	public User(final Integer usrId, final Integer limit) {

		super();
		this.userId = usrId;
		this.lim = limit;

	}

	/**
	 * @return userId Indicates the user of query's tuples.
	 */
	public Integer getUserId() {

		return userId;

	}

	/**
	 * @return limit Indicates the limit of query's tuples.
	 */
	public Integer getLim() {

		return lim;

	}

	/**
	 * Inherited query needs this method for decorators.
	 * 
	 * @return the builded query.
	 */
	public abstract Query getQuery();

}
