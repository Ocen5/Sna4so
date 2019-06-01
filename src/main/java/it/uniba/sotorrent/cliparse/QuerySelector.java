package it.uniba.sotorrent.cliparse;

import it.uniba.sotorrent.soqueries.SOQueryI;
import it.uniba.sotorrent.soqueries.day.component.QuestionDay;
import it.uniba.sotorrent.soqueries.day.component.AnswerDay;
import it.uniba.sotorrent.soqueries.day.component.QuestionEdgeDay;
import it.uniba.sotorrent.soqueries.day.decorator.QuestionWeightDay;
import it.uniba.sotorrent.soqueries.day.decorator.PostDay;
import it.uniba.sotorrent.soqueries.taglike.component.QuestionTagLike;
import it.uniba.sotorrent.soqueries.taglike.component.AnswerTagLike;
import it.uniba.sotorrent.soqueries.taglike.decorator.PostTagLike;
import it.uniba.sotorrent.soqueries.user.component.QuestionEdgeUser;
import it.uniba.sotorrent.soqueries.user.component.AnswerEdgeUser;
import it.uniba.sotorrent.soqueries.user.decorator.WeightUser;

public class QuerySelector implements QuerySelectorI {

	private final ParameterSet parameters;

	public QuerySelector(final ParameterSet params) {
		parameters = params;
	}

	public  SOQueryI selectQuery() throws IllegalArgumentException {

		/* WARNING!
		 * For each type of query check if there aren't attribute of other query.
		 * Check your type, edge or weight query in a private method.
		 */
		if (containsParameter("limit")
				&& containsParameter("yyyy")
				&& containsParameter("mm")
				&& containsParameter("dd")
				&& !containsParameter("taglike")
				&& !containsParameter("user")) {
			return getQueryDay();
		}


		if (containsParameter("limit")
				&& containsParameter("yyyy")
				&& containsParameter("mm")
				&& containsParameter("taglike")
				&& !containsParameter("dd")
				&& !containsParameter("user")) {
			return getQueryTaglike();
		}

		if (containsParameter("limit")
				&& containsParameter("user")
				&& !containsParameter("yyyy")
				&& !containsParameter("mm")
				&& !containsParameter("dd")
				&& !containsParameter("taglike")) {
			return getQueryUser();
		} else {
			throw new IllegalArgumentException();
		}

	}


	private SOQueryI getQueryUser() throws IllegalArgumentException {

		if (containsParameter("type")) {

			switch ((String) getParameter("type").getValue()) {

			case"question" :
				if (containsParameter("edge")) {
					//edge true
					if ((Boolean) getParameter("edge").getValue()) {

						if (containsParameter("weight")) {
							//weight true
							if ((Boolean) getParameter("weight").getValue()) {
								return new WeightUser(new QuestionEdgeUser(
									(Integer) getParameter("user").getValue(),
									(Integer) getParameter("limit").getValue()));


							} else {	//weight false
								return new QuestionEdgeUser(
									(Integer) getParameter("user").getValue(),
									(Integer) getParameter("limit").getValue());
							}

						} else {		//no weight (do same weight false)
							return new QuestionEdgeUser(
								(Integer) getParameter("user").getValue(),
								(Integer) getParameter("limit").getValue());
						}

					} else {	//edge false
						throw new IllegalArgumentException();
					}
				} else {	//without edge
					throw new IllegalArgumentException();
				}


			case "answer" :
				if (containsParameter("edge")) {
					//edge true
					if ((Boolean) getParameter("edge").getValue()) {

						if (containsParameter("weight")) {
							//weight true
							if ((Boolean) getParameter("weight").getValue()) {
								return new WeightUser(new AnswerEdgeUser(
									(Integer) getParameter("user").getValue(),
									(Integer) getParameter("limit").getValue()));

							} else {	//weight false
								return new AnswerEdgeUser(
									(Integer) getParameter("user").getValue(),
									(Integer) getParameter("limit").getValue());
							}

						} else {		//no weight (do same weight false)
							return new AnswerEdgeUser(
								(Integer) getParameter("user").getValue(),
								(Integer) getParameter("limit").getValue());
						}

					} else {		//edge false
						throw new IllegalArgumentException();
					}
				} else {		//without edge
					throw new IllegalArgumentException();
				}



			default :
				throw new IllegalArgumentException();
			}
		}

		return null;
	}



	private SOQueryI getQueryTaglike() throws IllegalArgumentException {

		if (containsParameter("type")) {

			switch ((String) getParameter("type").getValue()) {

			case"question" :

				//with edge none
				if (containsParameter("edge")
						&& (Boolean) getParameter("edge").getValue())	{
					throw new IllegalArgumentException();
				}

				//with weight none
				if (containsParameter("weight")
						&& (Boolean) getParameter("weight").getValue())	{
					throw new IllegalArgumentException();
				}

				return new QuestionTagLike((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(String) getParameter("taglike").getValue(),
						(Integer) getParameter("limit").getValue());


			case "answer" :

				//with edge none
				if (containsParameter("edge")
						&& (Boolean) getParameter("edge").getValue())	{
					throw new IllegalArgumentException();
				}

				//with weight none
				if (containsParameter("weight")
						&& (Boolean) getParameter("weight").getValue())	{
					throw new IllegalArgumentException();
				}

				return new AnswerTagLike((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(String) getParameter("taglike").getValue(),
						(Integer) getParameter("limit").getValue());

			case "post" :

				//with edge none
				if (containsParameter("edge")
						&& (Boolean) getParameter("edge").getValue())	{
					throw new IllegalArgumentException();
				}

				//with weight none
				if (containsParameter("weight")
						&& (Boolean) getParameter("weight").getValue())	{
					throw new IllegalArgumentException();
				}

				return new PostTagLike((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(String) getParameter("taglike").getValue(),
						(Integer) getParameter("limit").getValue());

			default :
				throw new IllegalArgumentException();
			}
		}

		return null;
	}



	private SOQueryI getQueryDay() throws IllegalArgumentException {

		if (containsParameter("type")) {

			switch ((String) getParameter("type").getValue()) {

			case"question" :

				//edge true
				if (containsParameter("edge")) {
					if ((Boolean) getParameter("edge").getValue()) {

						//weight true
						if (containsParameter("weight")) {
							if ((Boolean) getParameter("weight").getValue()) {
								return new QuestionWeightDay(
									(Integer) getParameter("yyyy").getValue(),
									(Integer) getParameter("mm").getValue(),
									(Integer) getParameter("dd").getValue(),
									(Integer) getParameter("limit").getValue());


							} else {		//weight false
								return new QuestionEdgeDay(
									(Integer) getParameter("yyyy").getValue(),
									(Integer) getParameter("mm").getValue(),
									(Integer) getParameter("dd").getValue(),
									(Integer) getParameter("limit").getValue());
							}

						} else {		//no weight (do same weight false)
							return new QuestionEdgeDay(
								(Integer) getParameter("yyyy").getValue(),
								(Integer) getParameter("mm").getValue(),
								(Integer) getParameter("dd").getValue(),
								(Integer) getParameter("limit").getValue());
						}

					} else {		//edge false
						return new QuestionDay((Integer) getParameter("yyyy").getValue(),
							(Integer) getParameter("mm").getValue(),
							(Integer) getParameter("dd").getValue(),
							(Integer) getParameter("limit").getValue());
					}

				} else {		//without edge
					return new QuestionDay((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(Integer) getParameter("dd").getValue(),
						(Integer) getParameter("limit").getValue());
				}


			case "answer" :

				//with edge none
				if (containsParameter("edge")
						&& (Boolean) getParameter("edge").getValue()) {
					throw new IllegalArgumentException();
				}

				//with weight none
				if (containsParameter("weight")
						&& (Boolean) getParameter("weight").getValue())	{
					throw new IllegalArgumentException();
				}

				return new AnswerDay((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(Integer) getParameter("dd").getValue(),
						(Integer) getParameter("limit").getValue());

			case "post" :

				//with edge none
				if (containsParameter("edge")
						&& (Boolean) getParameter("edge").getValue()) {
					throw new IllegalArgumentException();
				}

				//with weight none
				if (containsParameter("weight")
						&& (Boolean) getParameter("weight").getValue())	{
					throw new IllegalArgumentException();
				}

				return new PostDay((Integer) getParameter("yyyy").getValue(),
						(Integer) getParameter("mm").getValue(),
						(Integer) getParameter("dd").getValue(),
						(Integer) getParameter("limit").getValue());

			default :
				throw new IllegalArgumentException();
			}
		}

		return null;
	}



	private Boolean containsParameter(final String nameParameter) {

		for (Parameter p : parameters) {
			if (p.getAttribute().toString().equals(nameParameter)) {
				return true;
			}
		}

		return false;
	}



	private Parameter getParameter(final String nameParameter) {

		for (Parameter p : parameters) {
			if (p.getAttribute().toString().equals(nameParameter)) {
				return p;
			}
		}

		throw new IllegalArgumentException();

	}

}
