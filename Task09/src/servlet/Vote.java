package servlet;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper for vote results.
 * 
 * @author Arthur Markevich
 *
 */
public class Vote {

	private String sport;

	private int voteCount;

	private List<String> names;

	public Vote(String sport) {
		this.sport = sport;
		this.names = new ArrayList<>();
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	@Override
	public boolean equals(Object obj) {
		Vote vote = (Vote) obj;
		return this.sport.equals(vote.sport);
	}

}
