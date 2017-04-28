/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import Singleton.MenuWin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/07
 * 游戏加载界面
 */
public class loadingWin extends JWindow implements Runnable {

    Thread splashThread;     // 进度条更新线程
    JProgressBar progress;   // 进度条

    public loadingWin() {
        Container container = getContentPane();                     // 得到容器
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  // 设置光标
       URL url = getClass().getResource("loading.png");             // 图片的位置
        if (url != null) {
            container.add(new JLabel(new ImageIcon(url)), BorderLayout.CENTER); // 增加图片
        }
        
        progress = new JProgressBar(1, 100);     // 实例化进度条
        progress.setStringPainted(true);         // 描绘文字
        progress.setString("游戏加载中,请稍候......"); // 设置显示文字
        progress.setFont(new java.awt.Font("隶书", 1, 20)); 
        
        progress.setForeground(Color.GREEN); // 设置进度条颜色
        progress.setBackground(Color.WHITE); // 设置背景色
        container.add(progress, BorderLayout.SOUTH); // 增加进度条到容器上

        Dimension screen = getToolkit().getScreenSize(); // 得到屏幕尺寸
        pack(); // 窗口适应组件尺寸
        setLocation((screen.width - getSize().width) / 2,
                (screen.height - getSize().height) / 2); // 设置窗口位置
    }

    public void start() {
        this.toFront(); // 窗口前端显示
        splashThread = new Thread(this); // 实例化线程
        splashThread.start(); // 开始运行线程
    }

    public void run() {
        setVisible(true); // 显示窗口
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(10); // 线程休眠
                progress.setValue(progress.getValue() + 1); // 设置进度条值
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        dispose(); // 释放窗口
        showFrame(); // 运行主程序
    }

    //单例模式的应用
    static void showFrame() {
          MenuWin c;
          c = MenuWin.getIntance();
          c.setVisible(true);
    }
}
