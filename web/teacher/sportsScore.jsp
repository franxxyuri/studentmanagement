<%@ page import="vo.Teacher" %>
<%@ page import="vo.SportsScore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StudentD" %>
<%@ page import="vo.SportsScore" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    ArrayList<SportsScore> stus = (ArrayList<SportsScore>) session.getAttribute("onePageScore");
    int sumIndex = (int) session.getAttribute("sumScoreIndex");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=teacher.getId()%>.jpeg"/>
            <h1><%=teacher.getId()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li><a href="../one_page_student">学生管理</a></li>
                <li class="current_page_item"><a href="./sportsScore.jsp">运动会成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生运动会信息管理</h2>
            <button class="btn-add">添加学生运动会成绩</button>
            <input type="button" class="btn-add1" onclick="location.href='sportsScore_excel.jsp';" value="导出EXCEL">
            <hr/>
        </div>
        <form method="post" action="../update_sportsscore" style="height: 525px; margin-top: 20px">


            <input type="submit" class="btn-add1" style="float: right;margin-bottom: 30px" value="修改">


            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="800" frame="box" align="center">
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
                            StudentD stuD = new StudentD();
                            for (SportsScore stu : stus) {
                                String name = stuD.findWithId(stu.getId()).getName();
                                String major = stuD.findWithId(stu.getId()).getMajor();
                    %>
                    <tr>
                        <td height="35"><%=stu.getId()%>
                        </td>
                        <td><%=name%>
                        </td>
                        <td><%=major%>
                        </td>
                        <td><input value="<%=stu.getproject()%>" name="project" class="table-input"></td>
                        <td><input value="<%=stu.getscore()%>" name="score" class="table-input"></td>
                        <td><input value="<%=stu.getranking()%>" name="ranking" class="table-input"></td>
                        <input value="<%=stu.getId()%>" name="id" type="hidden">
                        <td><a class="btn-delete" onclick="return confirm('确定要删除吗?');"
                               href=<%="'../delete_sportsScore?id=" + stu.getId() + "'"%>>删除</a>&nbsp;&nbsp;<a
                                href="../one_page_sportsscore?id=<%=stu.getId()%>">查看成绩</a>
                        </td>
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </table>
            </div>
        </form>
        <%
            if (sumIndex > 1) {
        %>
        <div id="index">
            <a href="../one_page_sportsscore?index=1">首页</a>
            <%
                for (int i = 1; i <= sumIndex; i++) {
            %>
            <a href="../one_page_sportsscore?index=<%=i%>">第<%=i%>页</a>
            <%
                }
            %>
            <a href="../one_page_sportsscore?index=<%=sumIndex%>">尾页</a>
        </div>
        <%
            }
        %>
    </div>
</div>

<%--添加学生信息对话框--%>
<div id="add-dialog" title="添加学生信息">
    <form id="add-form" method="post">
        学号:<input name="id" type="text"><br>
        项目:<input name="project" type="text"><br>
        成绩:<input name="score" type="text"><br>
        排名:<input name="ranking" type="text"><br>
        <hr>
        <input style="float: right" type="submit" value="取消" onclick="function x() {
          $('#add-dialog').dialog('close');
        }">
        <input style="float: right; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='../add_SportsScore'">
    </form>
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
</html>

