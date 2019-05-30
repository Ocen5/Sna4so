package soqueries.day;


import soqueries.SOQueryI;

public abstract class Day implements SOQueryI{
	
	private Integer year;
	private Integer month;
	private Integer day;
	private Integer lim;
	//SOQuery.Query query ;
	
	public Day(Integer year, Integer month, Integer day, Integer limit) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.lim = limit;
	}

	abstract public String getQueryString();
	
	public Integer getYear() {
		return year;
	}


	public Integer getMonth() {
		return month;
	}


	public Integer getDay() {
		return day;
	}


	public Integer getLim() {
		return lim;
	}

}
