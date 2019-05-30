package soqueries.user.decorator;

import soqueries.SOQueryI;
import soqueries.user.User;

public class WeightUser implements SOQueryI {

	private User edge;
	public WeightUser(User query) {

		super();
		edge=query;

	}
	@Override
	public String getQueryString() {
		edge.getQuery().setSelect(edge.getQuery().getSelect() +
				", COUNT(`bigquery-public-data.stackoverflow.posts_answers`.owner_user_id) as weight");
		return edge.getQuery().toString();
	}

}

