package soqueries;

public class Query {

	private String select = "";
	private String from = "";
	private String where = "";

	/**
	 * @return select That contain SELECT of the query
	 */
	public final String getSelect() {

		return select;

	}

	/**
	 * @param slc That contain SELECT of the query
	 */
	public void setSelect(final String slc) {

		this.select = slc;

	}

	/**
	 * @return from That contain the table of the query
	 */
	public String getFrom() {

		return from;

	}

	/**
	 * @param frm That contain the table of the query
	 */
	public void setFrom(final String frm) {

		this.from = frm;

	}

	/**
	 * @return where That contain condition of the query
	 */
	public String getWhere() {

		return where;

	}

	/**
	 * @param whr That contain condition of the query
	 */
	public void setWhere(final String whr) {

		this.where = whr;

	}

	/**
	 * @return group That contain GROUP BY of the query
	 */
	public String getGroup() {

		return group;

	}

	/**
	 * @param grp That contain GROUP BY of the query
	 */
	public void setGroup(final String grp) {

		this.group = grp;

	}

	/**
	 * @return order That contain ORDER BY of the query
	 */
	public String getOrder() {

		return order;

	}

	/**
	 * @param ord That contain ORDER BY of the query
	 */
	public void setOrder(final String ord) {

		this.order = ord;

	}

	/**
	 * @return limit The limit of tuples.
	 */
	public Integer getLimit() {

		return limit;

	}

	/**
	 * @param lim The limit of tuples.
	 */
	public void setLimit(final Integer lim) {

		this.limit = lim;

	}

	private String having = "";
	private String group = "";
	private String order = "";
	private Integer limit = 0;

	public String toString() {
		return "SELECT " + select + " "
				+"FROM " + from + " "
				+"WHERE " + where + " "
				+"GROUP BY " + group + " "
				+"ORDER BY " + order + " "
				+"LIMIT " + limit;

	}

	public String toUnionDistinct(Query query2) {
		return "SELECT " + select 	+ " " 
				+"FROM " + from + " " 
				+"WHERE " + where + " " 
				+"UNION DISTINCT "
				+"SELECT " + query2.select 	+ " " 
				+"FROM " + query2.from + " " 
				+"WHERE " + query2.where + " " 
				+"GROUP BY " + query2.group + " " 
				+"ORDER BY " + query2.order + " " 
				+"LIMIT " + query2.limit; 
	}

}