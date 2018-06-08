package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/test")
public class Part1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String title = "Calculator";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", title);
		if (request.getParameter("x") == null || request.getParameter("y") == null
				|| (request.getParameter("op") == null) || (request.getParameter("op") == null)) {
			System.out.println("===> ERORR " + "x:" + request.getParameter("x") + " y:" + request.getParameter("y")
					+ " op:" + request.getParameter("op"));

		} else if ((request.getParameter("op").equals("%2B")) || (request.getParameter("op").equals("-"))) {
			Double x = null;
			Double y = null;
			try {
				x = Double.parseDouble(request.getParameter("x"));
				y = Double.parseDouble(request.getParameter("y"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (x != null && y != null) {
				String operation = request.getParameter("op");
				Double res = null;
				String op = null;

				switch (operation) {
				case "%2B":
					res = x + y;
					op = "+";
					break;
				case "-":
					res = x - y;
					op = "-";
					break;
				}
				request.setAttribute("x", x);
				request.setAttribute("y", y);
				request.setAttribute("operate", op);
				request.setAttribute("result", res);
			} else {
				System.out.println("x:" + x + " y:" + y);
			}
		}
		request.getRequestDispatcher("culc.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", title);
		req.getRequestDispatcher("culc.jsp").forward(req, resp);
	}

}
