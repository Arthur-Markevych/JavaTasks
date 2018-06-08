package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/vote")
public class Part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static List<Vote> votes;

	private final static String title = "Vote";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", title);
		request.setAttribute("votes", votes);

		HttpSession session = request.getSession();
		if (session.isNew()) {
			List<String> voters = new ArrayList<>();
			session.setAttribute("voters", voters);
		}

		if (request.getParameter("showTable") != null) {
			request.setAttribute("showTable", true);
		} else {
			request.setAttribute("showTable", false);
		}
		request.getRequestDispatcher("vote.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", title);
		HttpSession session = request.getSession();

		if (session.isNew()) {
			List<String> voters = new ArrayList<>();
			session.setAttribute("voters", voters);
		}

		String name = request.getParameter("name").trim();
		String sport = request.getParameter("sportKind");

		if (name != null && !name.equals("")) {
			@SuppressWarnings("unchecked")
			List<String> voters = (List<String>) session.getAttribute("voters");
			if (voters.contains(name.toLowerCase())) {
				request.setAttribute("message", "Name " + name + " already used!");
			} else {
				voters.add(name.toLowerCase());

				if (sport != null && votes.contains(new Vote(sport))) {
					for (Vote v : votes) {
						if (v.equals(new Vote(sport))) {
							int count = v.getVoteCount();
							v.getNames().add(name);
							v.setVoteCount(++count);
						}
					}
				}
			}
		} else {
			request.setAttribute("message", "The name is requaried!");
		}
		request.setAttribute("votes", votes);
		request.getRequestDispatcher("vote.jsp").forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		votes = new ArrayList<>();
		String list = getServletContext().getInitParameter("list");
		String[] sportList = list.split("\\s");
		for (String s : sportList) {
			Vote vote = new Vote(s);
			votes.add(vote);
		}
	}

}
