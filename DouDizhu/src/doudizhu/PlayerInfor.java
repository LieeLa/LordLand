/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author 聂娜 
 * 玩家得分情况类
 * 完成时间：2017/04/22
 */
public class PlayerInfor extends JFrame {

    private int playerid[] = new int[3];
    private int jindou[] = new int[3];
    private int score[] = new int[3];
    private String name[] = new String[3];
    private Mysqlconnection player;

    public PlayerInfor() throws SQLException {
        player = new Mysqlconnection();
        player.getinfo();
        this.playerid = player.getPlayerid();
        this.name = player.getName();
        this.jindou = player.getJindou();
        this.score = player.getScore();
    }

    //设置所有玩家的得分情况
    public void setAllInfo(int[] playerid, int[] jindou, int[] score) throws SQLException {
        player.setinfo(playerid, jindou, score);
    }

    //设置某一个玩家i的得分情况
    public void setSingelInfo(int playerid, int jindou, int score) throws SQLException {
        player.setSingelnfor(playerid, jindou, score);
    }

    //获取所有玩家ID
    public int[] getPlayerid() {
        return this.playerid;
    }

    //获取某一个玩家的ID
    public int getSingelPlayerID(int id) {
        return this.playerid[id];
    }
    
    
    //获取所有玩家的得分
    public int[] getScore() {
        return this.score;
    }

    //获取某一个玩家的得分
    public int getSingelPlayerScore(int id) {
        return this.score[id];
    }

    //获取某一个玩家的金豆数
    public int getSingelPlayerJinDou(int id) {
        return this.jindou[id];
    }

    //获取所有玩家的金豆
    public int[] getJindou() {
        return this.jindou;
    }

    //获取所有玩家的姓名
    public String[] getname() {
        return this.name;
    }

    //设置一组玩家Id
    public void setPlayerid(int[] playerid) {
        this.playerid = playerid;
    }

    //设置一组玩家金豆数
    public void setJindou(int[] JinDou) {
        this.jindou = JinDou;
    }

    //设置一组玩家得分
    public void setScore(int[] Score) {
        this.score = Score;
    }
    
    //设置一组玩家姓名
    public void setname(String[] name) {
        this.name = name;
    }
}
