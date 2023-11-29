package servlet;


import dao.SportsScoreD;
import dao.StudentD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add_SportsScore")
public class add_SportsScore extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        StudentD studentD = new StudentD();
        SportsScoreD sportsScoreD = new SportsScoreD();

        String id = request.getParameter("id");
        String project = request.getParameter("project");
        String score = request.getParameter("score");
        String ranking = request.getParameter("ranking");


        try {
            sportsScoreD.insertScore(id, project, score, ranking);
        } catch (Exception e) {
            out.print(e);
        }
        response.sendRedirect("one_page_sportsscore");
    }
}
