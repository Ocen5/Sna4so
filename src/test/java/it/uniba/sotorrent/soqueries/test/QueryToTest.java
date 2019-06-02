package it.uniba.sotorrent.soqueries.test;

public final class QueryToTest{
	// Queries

	private static String queries[] = new String[12];

	public QueryToTest() {

		/*	parameters with this set up:
		user=86
		limit=100
		year=2016
		month=02
		day=11
		taglike=java 
		*/

		//SOQueryQuestionUsrWeight	//USER
		queries[0]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`, "
				+ "COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id=86 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";

		//SOQueryQuestionUsrEdge	//USER
		queries[1]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to` "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id=86 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";
		
		//SOQueryQuestionTags  //TAG
		queries[2]="SELECT owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 "
				+ "AND tags like CONCAT('%java%') "
				+ "AND owner_user_id > 0 GROUP BY owner_user_id "
				+ "ORDER BY owner_user_id ASC LIMIT 100";

		//SOQueryQuestionDayWeight  //DAY
		queries[3]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`, "
				+ "COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_questions` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE extract(year from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=2016 "
				+ "AND extract(month from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=2 "
				+ "AND extract(day from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=11 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id >0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";

		//SOQueryQuestionDayEdge  //DAY
		queries[4]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id as `from`"
				+ ", `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to` "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_questions` " 
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id " 
				+ "WHERE extract(year from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=2016 "
				+ "AND extract(month from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=2 "
				+ "AND extract(day from `bigquery-public-data.stackoverflow.posts_questions`.creation_date)=11 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id >0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				 +"ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";

		//SOQueryQuestionDay   //DAY
		queries[5]="SELECT owner_user_id FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 " 
				+ "AND extract(day from creation_date)=11 "
				+ "AND owner_user_id > 0 "
				+ "GROUP BY owner_user_id "
				+ "ORDER BY owner_user_id ASC LIMIT 100";	

		//SOQueryPostTags		//TAG
		queries[6]="SELECT owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 "
				+ "AND tags like CONCAT('%java%') "
				+ "AND owner_user_id > 0 "
				+ "UNION DISTINCT "
				+ "SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=2016 "
				+ "AND extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=2 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.tags like CONCAT('%java%') "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "ORDER BY owner_user_id LIMIT 100";
		
		//SOQueryPostDay   //DAY
		queries[7]=	"SELECT owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 "
				+ "AND extract(day from creation_date)=11 "
				+ "AND owner_user_id > 0 "
				+ "UNION DISTINCT SELECT owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 "
				+ "AND extract(day from creation_date)=11 "
				+ "AND owner_user_id > 0 "
				+ "GROUP BY owner_user_id ORDER BY owner_user_id ASC LIMIT 100";

		//SOQueryAnswerDay  //DAY
		queries[8]="SELECT owner_user_id "
				+ "FROM `bigquery-public-data.stackoverflow.posts_answers` "
				+ "WHERE extract(year from creation_date)=2016 "
				+ "AND extract(month from creation_date)=2 "
				+ "AND extract(day from creation_date)=11 "
				+ "AND owner_user_id > 0 "
				+ "GROUP BY owner_user_id "
				+ "ORDER BY owner_user_id ASC LIMIT 100";

		//SOQueryAnswerUsrEdge	//USER
		queries[9]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to` "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id=86 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";
	
		//SOQueryAnswerTags		//TAG
		queries[10]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+"FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE extract(year FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=2016 "
				+ "AND extract(month FROM `bigquery-public-data.stackoverflow.posts_answers`.creation_date)=2 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.tags like CONCAT('%java%') "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id ASC LIMIT 100";

		//SOQueryAnswerUsrWeight	//USER
		queries[11]="SELECT `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id  as `from`, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id as `to`, "
				+ "COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight "
				+ "FROM `bigquery-public-data.stackoverflow.posts_questions` "
				+ "INNER JOIN `bigquery-public-data.stackoverflow.posts_answers` "
				+ "ON `bigquery-public-data.stackoverflow.posts_questions`.id = "
				+ "`bigquery-public-data.stackoverflow.posts_answers`.parent_id "
				+ "WHERE `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id=86 "
				+ "AND `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id > 0 "
				+ "AND `bigquery-public-data.stackoverflow.posts_questions`.owner_user_id > 0 "
				+ "GROUP BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id "
				+ "ORDER BY `bigquery-public-data.stackoverflow.posts_answers`.owner_user_id, "
				+ "`bigquery-public-data.stackoverflow.posts_questions`.owner_user_id ASC LIMIT 100";
	}

	public static String getTestQuery(int index) {
		return queries[index-1];
	}

}
