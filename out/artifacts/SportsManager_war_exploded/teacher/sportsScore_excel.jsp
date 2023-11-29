<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StudentD" %>
<%@ page import="vo.SportsScore" %>
<%@ page import="dao.SportsScoreD" %>

<%@ page contentType="application/msexcel" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>main</title>
    </head>
    <body>
        <%
            out.clearBuffer();
            response.setHeader("Content-Disposition", "attachment;filename=excel.xls");
        %>
        <table align="center" border="1">
            <tr>
                <th height="35">学号</th>
                <th>姓名</th>
                <th>专业</th>
                <th>项目</th>
                <th>成绩</th>
                <th>排名</th>
            </tr>
            <%
                try {
                    SportsScoreD scoD = new SportsScoreD();
                    StudentD stuD = new StudentD();
                    ArrayList<SportsScore> stus = scoD.getOnePage(1, 10000);
                    for (SportsScore stu : stus) {
                        String name = stuD.findWithId(stu.getId()).getName();
                        String major = stuD.findWithId(stu.getId()).getMajor();
            %>
            <tr>
                <td align="center"><%=stu.getId()%></td>
                <td align="center"><%=name%></td>
                <td align="center"><%=major%></td>
                <td align="center"><%=stu.getproject()%></td>
                <td align="center"><%=stu.getscore()%></td>
                <td align="center"><%=stu.getranking()%></td>
            </tr>
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>
        </table>
    </body>
</html>

