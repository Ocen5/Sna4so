package soqueries.user;

import soqueries.Query;
import soqueries.SOQueryI;

public abstract class User implements SOQueryI{

	private Integer userId;
	private Integer lim;
	
	public User(Integer userId, Integer limit) {
		super();
		this.userId = userId;
		this.lim = limit;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getLim() {
		return lim;
	}
	abstract public Query getQuery();
	
}
