package dao;

import vo.SportsScore;

import java.sql.*;
import java.util.ArrayList;

public class SportsScoreD {

    private Connection conn = null;

    public boolean insertScore(String id, String project, String score, String ranking) throws Exception {
        initConnection();

        String sql = "insert into score(id, project, score, ranking) values(?, ?, ?,  ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, project);
        ps.setString(3, score);
        ps.setString(4, ranking);

        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    public boolean deleteScore(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from score where id='" + id + "'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i == 1;
    }

    public void updateScoreInfo(String id, String project, String score, String ranking) throws Exception {

        initConnection();
        String sql = "update score set project=?, score=?, ranking=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, project);
        ps.setString(2, score);
        ps.setString(3, ranking);
        ps.setString(4, id);
        ps.executeUpdate();
        closeConnection();
    }

    public SportsScore findWithId(String id) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from score where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        SportsScore spscore = getScore(rs);
        closeConnection();
        return spscore;
    }

    public ArrayList<SportsScore> getOnePage(int page, int size) throws Exception {
        ArrayList<SportsScore> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM score limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page - 1) * size);
        ps.setInt(2, size);
        ResultSet rs = ps.executeQuery();
        getMoreScore(al, rs);
        closeConnection();
        return al;
    }

    public int getScoreCount() throws Exception {
        initConnection();
        String sql = "select count(*) from score";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    private SportsScore getScore(ResultSet rs) throws SQLException {
        SportsScore spscore = null;
        if (rs.next()) {
            spscore = new SportsScore();
            spscore.setId(rs.getString("id"));
            spscore.setproject(rs.getString("project"));
            spscore.setscore(rs.getString("score"));
            spscore.setranking(rs.getString("ranking"));
        }
        return spscore;
    }

    private void getMoreScore(ArrayList<SportsScore> al, ResultSet rs) throws SQLException {
        while (rs.next()) {

            SportsScore spscore = new SportsScore();
            spscore.setId(rs.getString("id"));
            spscore.setproject(rs.getString("project"));
            spscore.setscore(rs.getString("score"));
            spscore.setranking(rs.getString("ranking"));
            al.add(spscore);
        }
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sport_manage?useSSL=false", "root", "123456");
    }

    private void closeConnection() throws Exception {
        conn.close();
    }


}
