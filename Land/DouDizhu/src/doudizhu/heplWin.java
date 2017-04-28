/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author 聂娜
 * 游戏帮助界面类
 * 2017/04/08
 */
public class heplWin extends JFrame {

    private JPanel jp;
    private JTextArea rule;
    //显示方法

    public heplWin() {
        initHelpWin();
    }

    private static class HolderClass {
        private final static heplWin instance = new heplWin();
    }

    public static heplWin getIntance() {
        return HolderClass.instance;
    }

    public void initHelpWin() {
        this.setSize(830, 620);
        this.setResizable(false);
        this.setTitle("斗地主游戏规则");
        this.setLocationRelativeTo(getOwner()); // 屏幕居中
        Image icon = new ImageIcon("images\\rulebackground.jpg").getImage();
        JScrollPane scrollPane;

        jp = new JPanel() {
            //绘制背景图片
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponents(g);
                g.drawImage(icon, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        showRule();
        scrollPane = new JScrollPane(jp);
        jp.add(rule);
        jp.setOpaque(true);
        Container cp = getContentPane();
        cp.add(scrollPane);
        ((JPanel) cp).setOpaque(false);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showRule() {
        //创建list集合
        rule = new JTextArea(65, 70);
        List list = new ArrayList();
        FileInputStream fis;
        //取得地址
        String url = "rule.txt";
        try {
            //创建输入流
            fis = new FileInputStream(url);
            //读取字节
            InputStreamReader isr = new InputStreamReader(fis);
            //读取字流
            BufferedReader br = new BufferedReader(isr);
            String line;
            try {
                //用readLine读取一行的值，如果这一行不为空的话继续执行
                while ((line = br.readLine()) != null) {
                    //如果为空的话继续
                    if (line.equals("")) {
                        continue;
                    } else {
                        list.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("读取一行数据时出错");
            }
            //关闭输入流
            try {
                fis.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件读取路径错误FileNotFoundException");
        }
        rule.setRows(50);
        //换行设置为真
        rule.setLineWrap(true);
        String text = "";
        //循环显示输出list中的内容
        for (int i = 0; i < list.size(); i++) {
            text += list.get(i) + "\r\n";
        }
        rule.setText(text);    
        rule.setOpaque(false);       //界面透明
        rule.setFont(new java.awt.Font("隶书", 1, 20));
        rule.setEditable(false);    //不可編輯  
    }

    class BackgroundLabel extends JLabel {

        Image im;

        public BackgroundLabel(Image im) {
            this.im = im;
            this.setOpaque(true);
        }

        //Draw the back ground.
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
