package it.uniba.sotorrent.soqueries;

/**
 * Entity
 * This class contains getter and setter of the keywords SQL to compose the queries.
 * Author codd.
 */
public class Query {

	/**
	 * The argument of the SELECT statement.
	 */
	private String select = "";

	/**
	 * The argument of the FROM statement.
	 */
	private String from = "";

	/**
	 * The argument of the WHERE statement.
	 */
	private String where = "";

	/**
	 * The argument of the GROUP BY statement.
	 */
	private String group = "";

	/**
	 * The argument of the ORDER BY statement.
	 */
	private String order = "";

	/**
	 * The argument of the LIMIT statement.
	 */
	private Integer limit = 0;

	/**
	 * @return select That contain SELECT of the query.
	 */
	public final String getSelect() {

		return select;

	}

	/**
	 * @param slc That contain SELECT of the query.
	 */
	public void setSelect(final String slc) {

		this.select = slc;

	}

	/**
	 * @return from That contain the table of the query.
	 */
	public String getFrom() {

		return from;

	}

	/**
	 * @param frm That contain the table of the query.
	 */
	public void setFrom(final String frm) {

		this.from = frm;

	}

	/**
	 * @return where That contain condition of the query.
	 */
	public String getWhere() {

		return where;

	}

	/**
	 * @param whr That contain condition of the query.
	 */
	public void setWhere(final String whr) {

		this.where = whr;

	}

	/**
	 * @return group That contain GROUP BY of the query.
	 */
	public String getGroup() {

		return group;

	}

	/**
	 * @param grp That contain GROUP BY of the query.
	 */
	public void setGroup(final String grp) {

		this.group = grp;

	}

	/**
	 * @return order That contain ORDER BY of the query.
	 */
	public String getOrder() {

		return order;

	}

	/**
	 * @param ord That contain ORDER BY of the query.
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

	/**
	 * This method composes the statements of the query.
	 * 
	 * @return string concatenation.
	 */
	public String toString() {

		return "SELECT " + select + " "
				+ "FROM " + from + " "
				+ "WHERE " + where + " "
				+ "GROUP BY " + group + " "
				+ "ORDER BY " + order + " "
				+ "LIMIT " + limit;

	}

	/**
	 * This method gets the string of the UNION DISTINCT of this query
	 * with another query.
	 * 
	 * @param query2 The query to add.
	 * @return string concatenation with query2.
	 */
	public String toUnionDistinct(final Query query2) {

		return "SELECT " + select + " "
				+ "FROM " + from + " "
				+ "WHERE " + where + " "
				+ "UNION DISTINCT "
				+ "SELECT " + query2.select + " "
				+ "FROM " + query2.from + " "
				+ "WHERE " + query2.where + " "
				+ "GROUP BY " + query2.group + " "
				+ "ORDER BY " + query2.order + " "
				+ "LIMIT " + query2.limit;

	}

}
