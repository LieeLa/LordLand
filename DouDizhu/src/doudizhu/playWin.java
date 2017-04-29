 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import Adapter.Adapter_Card;
import Adapter.Operation;
import BuilderPattern.Model1;
import Flyweight.AbstractButton;
import Flyweight.BntFactory;
import Flyweight.Buttonlocation;
import Iterator.ConcreteList;
import Iterator.Iterator;
import Memento.Caretaker;
import Memento.Originator;
import Singleton.MenuWin;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author 聂娜 
 * 完成时间：2017/04/23
 * 游戏界面
 */
public class playWin extends JFrame implements ActionListener {

    public Container container = null;           // 定义容器
    JMenuItem back, exit;                        // 定义菜单按钮
    JButton landlord[] = new JButton[2];         //抢地主按钮
    JButton publishCard[] = new JButton[2];      //出牌按钮
    JLabel dizhu;                                //地主图标
    List<Card> currentList[] = new ArrayList[3]; // 当前的出牌
    List<Card> playerList[] = new ArrayList[3];  // 定义3个玩家表
    List<Card> lordList;                         //地主牌
    JTextField time[] = new JTextField[3];       //计时器
    JTextField cardLeft = new JTextField(40);    //牌的张数显示
    BackgroundLabel bgp;                //背景图片容器
    ImageIcon background;               //背景图片
    JPanel imagePanel;                  //背景图片容器
    Card card[] = new Card[56];         // 定义54张牌
    Time t;                             //定时器（线程）
    boolean nextPlayer = false;         //转换角色
    private Operation operation = Adapter_Card.getIntance();
    int dizhuFlag;        //地主标志
    int turn;             //出牌轮流标志

    //获取定时器
    public JTextField getTime(int i) {
        return this.time[i];
    }

    //获取玩家牌
    public List<Card> getPlayerList(int i) {
        return this.playerList[i];
    }

    //获取地主标志
    public int getDizhuFlag() {
        return this.dizhuFlag;
    }

    //获取地主图标
    public JLabel getDizhu() {
        return this.dizhu;
    }

    //获取地主的牌
    public List<Card> getLordList() {
        return this.lordList;
    }
    
    private static int playerid[] = new int[3];    //玩家的ID
    private static int jindou[] = new int[3];      //玩家的金豆数
    private static int score[] = new int[3];       //玩家的得分
    Caretaker mc = new Caretaker();     //备忘录中的负责人类
    Originator originator = new Originator();    //备忘录模式中的原发器
    PlayerInfor playerInfor = new PlayerInfor();    //每位玩家的得等情况

    private int finishFlag;

    //新的游戏开始的时候，每位玩家的金豆数减少5
    public void mimusJindou() throws SQLException {
        this.playerid = playerInfor.getPlayerid();
        this.jindou = playerInfor.getJindou();
        this.score = playerInfor.getScore();
        originator.setInfor(playerInfor);
        mc.setMemento(originator.createMemento());
        for (int i = 0; i < 3; i++) {
            this.jindou[i] -= 5;
        }
        //将新的玩家金豆数写进数据库
        playerInfor.setAllInfo(playerid, jindou, score);
    }
    
    //初始化
    public playWin() throws SQLException {
        Init();// 初始化
        SetMenu();// 创建菜单 按钮(抢地主，发牌,计时器)
        this.setVisible(true);
        CardInit();//发牌
        mimusJindou();   //对每位玩家的金豆数-5
        startplay();   //开始游戏
    }

    //获取玩家得分实例
    public PlayerInfor getInfor() {
        return this.playerInfor;
    }
     
    //开始游戏
    public void startplay() {
        getLord(); //发完牌开始抢地主
        time[1].setVisible(true);
        //线程安全性,把非主线程的UI控制放到里面
        SwingUtilities.invokeLater(new NewTimer(this, 10));
    }

    //将地主图标显示到相应的位置
    public void getLord() {
        //System.out.println(CardType.c0.toString());
        for (int i = 0; i < 2; i++) {
            landlord[i].setVisible(true);
        }
    }

    //初始化牌
    // 发牌洗牌
    public void CardInit() {
        int count = 1;
        /**
         * 初始化牌： 1.选花色 2.选数值
         */
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 13; j++) {
                if ((i == 5) && (j > 2)) {
                    break;
                } else {
                    card[count] = new Card(this, i + "-" + j, false);
                    card[count].setLocation(350, 50);
                    container.add(card[count]);
                    count++;
                }
            }
        }

        //打乱顺序，把牌交換100次
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            //生成0-54之間的整數，因為可能產生0，所以random的基數選擇54
            int a = random.nextInt(54) + 1;
            int b = random.nextInt(54) + 1;
            Card k = card[a];
            card[a] = card[b];
            card[b] = k;
        }

        //开始发牌
        for (int i = 0; i < 3; i++) {
            playerList[i] = new ArrayList<Card>(); //玩家牌，電腦玩家2個
        }
        lordList = new ArrayList<Card>();//地主牌三张
        int t = 0;
        for (int i = 1; i <= 54; i++) {
            if (i >= 52)//地主牌
            {
                operation.move(card[i], card[i].getLocation(), new Point(300 + (i - 52) * 80, 10));
                lordList.add(card[i]);
                continue;
            }
            switch ((t++) % 3) {
                case 0:
                    //左边玩家
                    operation.move(card[i], card[i].getLocation(), new Point(50, 60 + i * 5));
                    playerList[0].add(card[i]);
                    break;
                case 1:
                    //当前玩家
                    operation.move(card[i], card[i].getLocation(), new Point(180 + i * 7, 450));
                    playerList[1].add(card[i]);
                    card[i].turnFront(); //显示正面
                    break;
                case 2:
                    //右边玩家
                    operation.move(card[i], card[i].getLocation(), new Point(700, 60 + i * 5));
                    playerList[2].add(card[i]);
                    break;
            }
            //card[i].turnFront(); //显示正面
            container.setComponentZOrder(card[i], 0);
        }
        //发完牌排序，从大到小
        for (int i = 0; i < 3; i++) {
            operation.order(playerList[i]);
            operation.rePosition(this, playerList[i], i);//重新定位
        }
        //发完牌后，将地主头像显示到相应的位置
        dizhu = new JLabel(new ImageIcon("images/dizhu.gif"));
        dizhu.setVisible(false);
        dizhu.setSize(40, 40);
        container.add(dizhu);
        Model1 model = operation.getModel(playerList[0]);
        if (model.getA1().size() == 0) {
            System.out.println("None");
        }
    }

    //界面初始化
    public void Init() {
        this.setTitle("斗地主，聂娜，14281014");
        this.setSize(830, 620);
        setResizable(false);
        setLocationRelativeTo(getOwner()); // 屏幕居中
        container = this.getContentPane();
        container.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();

        bgp = new BackgroundLabel((new ImageIcon("images\\back2.jpeg")).getImage());
        bgp.setBounds(0, 0, 830, 620);
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        bgp.setVisible(true);
        getLayeredPane().add(bgp, new Integer(Integer.MIN_VALUE));  //背景置于底层
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 创建菜单 功能按钮
    public void SetMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu game = new JMenu("游戏");

        back = new JMenuItem("返回");
        exit = new JMenuItem("退出");

        back.addActionListener(this);
        exit.addActionListener(this);

        game.add(back);
        game.add(exit);
        jMenuBar.add(game);
        this.setJMenuBar(jMenuBar);

        //享元模式的应用
        AbstractButton loard, noloard, play, noplay;
        BntFactory ButtonFactory;
        ButtonFactory = BntFactory.getInstance();
        loard = ButtonFactory.getFlyweightButton("loard");
        noloard = ButtonFactory.getFlyweightButton("noloard");
        play = ButtonFactory.getFlyweightButton("play");
        noplay = ButtonFactory.getFlyweightButton("noplay");
        landlord[0] = loard.initButton(new Buttonlocation(0, 75));
        landlord[1] = noloard.initButton(new Buttonlocation(1, 75));
        publishCard[0] = play.initButton(new Buttonlocation(0, 60));
        publishCard[1] = noplay.initButton(new Buttonlocation(1, 60));
        //享元模式应用结束

        for (int i = 0; i < 2; i++) {
            container.add(landlord[i]);
            landlord[i].addActionListener(this);
            container.add(publishCard[i]);
            publishCard[i].addActionListener(this);
        }

        /*简单工厂模式的应用
        SimpleFactory_Button button = new SimpleFactory_Button();
        landlord[0] = button.setButton("抢地主", 0);
        landlord[1] = button.setButton("不抢", 1);
        publishCard[0] = button.setButton("出牌", 0);
        publishCard[1] = button.setButton("不要", 1);
        for (int i = 0; i < 2; i++) {
            container.add(landlord[i]);
            landlord[i].addActionListener(this);
            container.add(publishCard[i]);
            publishCard[i].addActionListener(this);
        }
         */
        //时钟按钮设置
        for (int i = 0; i < 3; i++) {
            time[i] = new JTextField("倒计时:");
            time[i].setVisible(false);
            time[i].setOpaque(false);
            time[i].setEditable(false);
            time[i].setBorder(new EmptyBorder(0, 0, 0, 0));
            time[i].setFont(new java.awt.Font("隶书", 1, 25));
            container.add(time[i]);
        }
        time[0].setBounds(130, 230, 140, 30);
        time[1].setBounds(370, 360, 140, 30);
        time[2].setBounds(580, 230, 140, 30);
       
        Border b1 = BorderFactory.createLineBorder(Color.black, 2);
        Border b2 = BorderFactory.createRaisedBevelBorder();
        cardLeft.setBorder(BorderFactory.createCompoundBorder(b1, b2));
        cardLeft.setBounds(320, 200, 230, 25);
        cardLeft.setFont(new java.awt.Font("隶书", 1, 20));
        cardLeft.setEditable(false);
        cardLeft.setVisible(false);
        container.add(cardLeft);

        for (int i = 0; i < 3; i++) {
            currentList[i] = new ArrayList<Card>();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //退出游戏 
        if (e.getSource() == exit) {
            this.dispose();
        }
        //返回到登录界面
        if (e.getSource() == back) {
            System.out.println(finishFlag);
            if (finishFlag == 0) {
                playerInfor = originator.restoreMemento(mc.getMemento());
                playerid = playerInfor.getPlayerid();
                jindou = playerInfor.getJindou();
                score = playerInfor.getScore();
                try {
                    playerInfor.setAllInfo(playerid, jindou, score);
                } catch (SQLException ex) {
                    Logger.getLogger(playWin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.dispose();
            t.isRun = false; //时钟终结
            //单例模式应用
            MenuWin c;
            c = MenuWin.getIntance();
            c.setVisible(true);
        }
        if (e.getSource() == landlord[0]) {
            time[1].setText("抢地主");
            t.isRun = false; //时钟终结
        }
        if (e.getSource() == landlord[1]) {
            time[1].setText("不抢");
            t.isRun = false; //时钟终结
        }

        //如果是不要
        if (e.getSource() == publishCard[1]) {
            this.nextPlayer = true;
            currentList[1].clear();
            time[1].setText("不要");
        }

        //适配器模式的应用，如果是出牌按钮
        if (e.getSource() == publishCard[0]) {
            List<Card> c = new ArrayList<Card>();
            ConcreteList list = new ConcreteList(playerList[1]);
            Iterator ite = list.createIterator();
            while (ite.hasNext()) {
                //点选出牌
                Card card;
                card = (Card) ite.next();
                if (card.clicked) {
                    c.add(card);
                }
            }
            int flag = 0;

            //如果玩家主动出牌
            if (time[0].getText().equals("不要") && time[2].getText().equals("不要")) {
                if (operation.jugdeType(c) != CardType.c0) {
                    flag = 1;//表示可以出牌
                }
            }//如果玩家跟牌
            else {
                flag = operation.checkCards(c, currentList);
            }
            //判断是否符合出牌
            if (flag == 1) {
                currentList[1] = c;
                playerList[1].removeAll(currentList[1]);//移除走的牌
                //定位出牌
                Point point = new Point();
                point.x = (770 / 2) - (currentList[1].size() + 1) * 15 / 2;;
                point.y = 300;
                for (int i = 0, len = currentList[1].size(); i < len; i++) {
                    Card card = currentList[1].get(i);
                    operation.move(card, card.getLocation(), point);
                    point.x += 15;
                }
                //抽完牌后重新整理牌
                operation.rePosition(this, playerList[1], 1);
                time[1].setVisible(false);
                this.nextPlayer = true;
            }

        }
    }

    //设置游戏结束标志
    public void setFinishFlag(int flag) {
        this.finishFlag = flag;
    }
}

class NewTimer implements Runnable {

    playWin main;
    int i;

    public NewTimer(playWin m, int i) {
        this.main = m;
        this.i = i;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        main.t = new Time(main, 10);//从10开始倒计时
        main.t.start();
    }
}
