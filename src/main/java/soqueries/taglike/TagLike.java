package soqueries.taglike;

import soqueries.SOQueryI;

public abstract class TagLike implements SOQueryI{

	private Integer year;
	private Integer month;
	private String taglike;
	private Integer lim;
	
	public TagLike(Integer year, Integer month, String taglike, Integer lim) {
		super();
		this.year = year;
		this.month = month;
		this.taglike = taglike;
		this.lim = lim;
	}

	abstract public String getQueryString();
	
	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
	}

	public String getTaglike() {
		return taglike;
	}

	public Integer getLim() {
		return lim;
	}
	
}
