package soqueries;

import soqueries.day.component.AnswerDay;
import soqueries.day.component.QuestionDay;
import soqueries.day.component.QuestionEdgeDay;
import soqueries.day.decorator.PostDay;
import soqueries.day.decorator.QuestionWeightDay;
import soqueries.taglike.component.AnswerTagLike;
import soqueries.taglike.component.QuestionTagLike;
import soqueries.taglike.decorator.PostTagLike;
import soqueries.user.component.AnswerEdgeUser;
import soqueries.user.component.QuestionEdgeUser;
import soqueries.user.decorator.WeightUser;

/**
 * 
 * @author Codd
 *
 */
public class APPMain {

	public APPMain() {
	}

	public static void main(final String[] args) {

		QuestionDay query1 = new QuestionDay(2016, 11, 02, 100);
		System.out.println(query1.getQueryString());
		System.out.println("queryday");

		AnswerDay query2 = new AnswerDay(2016, 11, 02, 100);
		System.out.println(query2.getQueryString());
		System.out.println("answerday");

		QuestionEdgeDay query3 = new QuestionEdgeDay(2016, 11, 02, 100);
		System.out.println(query3.getQueryString());
		System.out.println("QuestionEdge");

		PostDay query4 = new PostDay(2016, 11, 02, 100);
		System.out.println(query4.getQueryString());
		System.out.println("Postday");

		QuestionWeightDay query5 = new QuestionWeightDay(2016, 11, 02, 100);
		System.out.println(query5.getQueryString());
		System.out.println("QuestionWeight");

		AnswerTagLike query6 = new AnswerTagLike(2016, 11, "java", 100);
		System.out.println(query6.getQueryString());
		System.out.println("Answertag");

		QuestionTagLike query7 = new QuestionTagLike(2016, 11, "java", 100);
		System.out.println(query7.getQueryString());
		System.out.println("QuestionTag");

		PostTagLike query8 = new PostTagLike(2016, 11, "java", 100);
		System.out.println(query8.getQueryString());
		System.out.println("Post tag");

		AnswerEdgeUser query9 = new AnswerEdgeUser(11213, 18);
		System.out.println(query9.getQueryString());
		System.out.println("answerusredge");

		QuestionEdgeUser query10 = new QuestionEdgeUser(11213, 18);
		System.out.println(query10.getQueryString());
		System.out.println("questionusredge");

		WeightUser query11 = new WeightUser(query10);
		System.out.println(query11.getQueryString());
		System.out.println("questionuserweight");

		WeightUser query12 = new WeightUser(query9);
		System.out.println(query12.getQueryString());
		System.out.println("answerusrweight");

	}

}
