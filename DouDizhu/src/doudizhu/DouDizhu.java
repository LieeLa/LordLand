/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 聂娜 
 * 游戏入口类 
 * 完成时间：2017/04/07
 */
public class DouDizhu {

    public static void main(String[] args) {
        //启动加载界面
        loadingWin splash = new loadingWin();
        splash.start(); 
    }
}
