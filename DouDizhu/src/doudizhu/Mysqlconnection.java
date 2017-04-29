/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;

/**
 *
 * @author 聂娜
 * 数据库连接类
 * 完成时间：2017/04/22
 */
public class Mysqlconnection {

    private int playerid[] = new int[3];
    private int jindou[] = new int[3];
    private int score[] = new int[3];
    private String name[] = new String[3];
    Connection conn = null;
    CallableStatement stmt;
    ResultSet rs;

    //连接数据库
    public Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lordinformation?"
                    + "useUnicode=true&characterEncoding=gbk&useSSL=false", "root", "199655");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "Rout Error");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage() + "JBDC Driver Error");
        }
        return this.conn;
    }

    // 关闭数据库连接
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                this.conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //获取玩家得分情况
    public void getinfo() throws SQLException {
        connection();
        stmt = conn.prepareCall("{call proc_QueryInfor()}");
        stmt.executeQuery();
        ResultSet rs = stmt.getResultSet();
        int i = 0;
        while (rs.next()) {
            this.playerid[i] = rs.getInt(1);
            this.jindou[i] = rs.getInt(2);
            this.score[i] = rs.getInt(3);
            this.name[i] = rs.getString(4);
            i++;
        }
        close();
    }

    //设置玩家得分情况
    public void setinfo(int[] playerid, int[] jindou, int[] score) throws SQLException {
        connection();
        stmt = conn.prepareCall("{call proce_UpdatePlayerInfor(?,?,?)}");
        for (int i = 0; i < 3; i++) {
            stmt.setInt(1, playerid[i]);
            stmt.setInt(2, jindou[i]);
            stmt.setInt(3, score[i]);
            stmt.executeUpdate();
        }
        close();
    }

    //设置某一个玩家得分情况
    public void setSingelnfor(int playerid, int jindou, int score) throws SQLException {
        connection();
        stmt = conn.prepareCall("{call proce_UpdatePlayerInfor(?,?,?)}");
        stmt.setInt(1, playerid);
        stmt.setInt(2, jindou);
        stmt.setInt(3, score);
        stmt.executeUpdate();
        close();
    }

    //获取玩家ID
    public int[] getPlayerid() {
        return this.playerid;
    }

    //获取玩家金豆数
    public int[] getJindou() {
        return this.jindou;
    }

    //获取玩家得分
    public int[] getScore() {
        return this.score;
    }

    //获取玩家姓名
    public String[] getName() {
        return this.name;
    }
}
