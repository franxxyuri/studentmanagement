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

@WebServlet("/delete_sportsScore")
public class delete_sportsScore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        SportsScoreD sportsScoreD = new SportsScoreD();
        StudentD studentD = new StudentD();

        String id = request.getParameter("id");
        try {
            studentD.deleteStudent(id);
            sportsScoreD.deleteScore(id);
            response.sendRedirect("one_page_sportsscore");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
