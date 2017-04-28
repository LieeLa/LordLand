/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author 聂娜
 * 玩家得分列表类
 * 完成时间：2017/04/22
 */
public class InforWin extends JFrame {
     private int playerid[] = new int[3];
    private int jindou[] = new int[3];
    private int score[] = new int[3];
    private String name[] = new String[3];
    JTable table;
    //定义二维数组作为表格数据  

    //定义一维数据作为列标题  
    String[] columnTitle = {"玩家ID","姓名", "金豆数", "得分"};

    //界面初始化
    public void init() throws SQLException {
        this.setTitle("玩家得分统计");
        this.setSize(470, 300);
        this.setLocationRelativeTo(getOwner()); // 屏幕居中
        Container pane = getContentPane(); 
        pane.setLayout(new BorderLayout());  
        tableInit();
        JScrollPane jsPane = new JScrollPane(table);  
        pane.setLayout(new BorderLayout());  
        pane.add(jsPane,BorderLayout.CENTER);  
        this.setVisible(true);
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //表格初始化
    public void tableInit() throws SQLException {
        PlayerInfor playerInfor = new PlayerInfor();
        this.playerid = playerInfor.getPlayerid();
        this.name = playerInfor.getname();
        this.jindou = playerInfor.getJindou();
        this.score = playerInfor.getScore();
        Object[][] tableData = new Object[3][4];
        //显示玩家的ID、金豆数、得分
        for (int i = 0; i < 3; i++) {
            tableData[i] = new Object[]{playerid[i],this.name[i], jindou[i], score[i]};
        };
        //以二维数组和一维数组来创建一个JTable对象  
        table = new JTable(tableData, columnTitle);
        table.getTableHeader().setBackground(Color.ORANGE);
        //设置表头的文字颜色  
        table.getTableHeader().setForeground(Color.BLACK);
        //设置表头字体  
        table.getTableHeader().setFont(new java.awt.Font("隶书", 1, 20));
        //table.getTableHeader().setFont(new Font("隶书", Font.PLAIN, 14));
        // 设置行高  
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumn(columnTitle[0]).setPreferredWidth(100);  //设置第1列的宽度  
        table.getColumn(columnTitle[1]).setPreferredWidth(150);  //设置第2列的宽度  
        table.getColumn(columnTitle[2]).setPreferredWidth(100);  //设置第2列的宽度  
        table.getColumn(columnTitle[3]).setPreferredWidth(100);  //设置第2列的宽度
        table.setBackground(new Color(244, 244, 242)); //设置表格背景  
        table.setForeground(Color.BLACK);   //设置表格颜色  
        table.setFont(new java.awt.Font("隶书", 1, 20));
        //设置表格的自动调整模式  
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //设置表格的行选择模式  
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //设置是否允许选择表格行  
        table.setRowSelectionAllowed(true);
        //设置是否允许选择表格列  
        table.setColumnSelectionAllowed(true);
        //设置是否允许同时存在行选择与列选择  
        table.setCellSelectionEnabled(true);
        //将JTable对象放在JScrollPane中，并将该JScrollPane放在窗口中显示出来  
    }
}
