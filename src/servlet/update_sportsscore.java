package servlet;


import dao.SportsScoreD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_sportsscore")
public class update_sportsscore extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        SportsScoreD ScoreD = new SportsScoreD();

        String[] id = request.getParameterValues("id");
        String[] project = request.getParameterValues("project");
        String[] score = request.getParameterValues("score");
        String[] ranking = request.getParameterValues("ranking");

        try {
            for (int i = 0; i < id.length; i++) {
                ScoreD.updateScoreInfo(id[i], project[i], score[i], ranking[i]);
            }
            response.sendRedirect("one_page_sportsscore");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
