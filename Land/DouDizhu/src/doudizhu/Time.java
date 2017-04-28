/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doudizhu;

import Adapter.*;
import BuilderPattern.Model1;
import Observer.*;
import State.*;
import Visitor.*;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author 聂娜
 * 游戏运行主线程类
 * 完成时间：2017/04/23
 */
public class Time extends Thread {

    private playWin main;        //关联游戏界面
    boolean isRun = true;     //时钟运行标志
    private int i = 10;    //计时初值
    private Operation operation = Adapter_Card.getIntance();
    private PlayerInfor playerInfo;   //玩家得分对象
    private int lordturn;        //当前出牌方
    private int lordWin = 0;     //地主胜利标志

    public Time(playWin m, int i) {
        this.main = m;
        this.i = i;
        this.playerInfo = main.getInfor();
    }

    @Override
    public void run() {
        while (i > -1 && isRun) {
            main.time[1].setText("倒计时:" + i--);
            second(1);      // 等一秒
        }
        if (i == -1)// 正常终结，说明超时
        {
            main.time[1].setText("不抢");
        }
        main.landlord[0].setVisible(false);
        main.landlord[1].setVisible(false);
        turnOn(false);

        for (Card card2 : main.playerList[1]) {
            card2.canClick = true;// 可被点击
        }

        //状态模式的应用
        State_setLord setlord = new State_setLord();
        //玩家抢地主
        setlord.setState(main, new Lord_Player());     
        second(1);
        main.time[2].setVisible(true);
        for (int k = 2; k > 0; k--) {
            main.time[2].setText("倒计时:" + k);
            second(1);      // 等一秒
        }
        setlord.setState(main, new Lord_Compute2());   //电脑2以0.5的概率抢地主，
        second(1);
        main.time[0].setVisible(true);    //电脑0以0.5的概率抢地主
        for (int k = 2; k > 0; k--) {
            main.time[0].setText("倒计时:" + k);
            second(1);      // 等一秒
        }
        setlord.setState(main, new Lord_Compute0());
        second(1);
        this.lordturn = setlord.lordFinal(main);
        main.dizhuFlag = this.lordturn;
        //System.out.println(this.lordturn);
        //状态模式结束

        for (int i = 0; i < 3; i++) {
            main.time[i].setText("不要");
            main.time[i].setVisible(false);
        }

        //观察者模式应用
        Observer_Cardleft player[] = null;
        Observer_Subject sub = new ConcreteSuject();  //加入观察者
        Observer_Cardleft player0, player1, player2;
        player0 = new ConcreteObserver(main.playerList[0], 0);
        player1 = new ConcreteObserver(main.playerList[1], 1);
        player2 = new ConcreteObserver(main.playerList[2], 2);
        sub.attach(player0);
        sub.attach(player1);
        sub.attach(player2);
        // 开始游戏 根据地主不同顺序不同
        main.turn = main.dizhuFlag;
        while (true) {
            if (main.turn == 1) //玩家1出牌
            {
                turnOn(true);           // 出牌按钮 --玩家1出牌
                timeWait(10, 1);        // 倒计时10秒
                main.cardLeft.setVisible(false);
                turnOn(false);          //出牌完毕，关闭计时器
                main.turn = (main.turn + 1) % 3;     //换下一个玩家出牌
                if (main.playerList[1].size() < 3) {    
                    //观察者模式的应用，如果当前玩家的排数少于3张的话就通知别的玩家
                    String message = player1.CardLeft((ConcreteSuject) sub, main.playerList[1], 1);
                    main.cardLeft.setText(message);       
                    System.out.println(lordWin);
                    main.cardLeft.setVisible(true);
                }
                if (win(1))//判断输赢
                {
                    //设置游戏结束标志
                    main.setFinishFlag(1);
                    if (lordturn == 1) {
                        //地主获胜，加分、金豆数
                        lordWin = 1;
                        this.setPlayerScore(1, 0, 2,lordWin);
                    } else if (lordturn == 0) {
                        lordWin = 0;
                        //农民获胜，加分、金豆数
                        this.setPlayerScore(0, 1, 2,lordWin);
                    }
                    else if(lordturn == 2){
                        //农民获胜，加分、金豆数
                        lordWin = 0;
                        setPlayerScore(2, 0, 1,lordWin);
                    }
                    main.cardLeft.setVisible(false);
                    System.out.println(lordWin);
                    break;
                }
            }
            if (main.turn == 0) //玩家0（电脑）出牌
            {
                computer0();
                if (main.playerList[0].size() < 3) {
                    //观察者模式的应用，如果当前玩家的排数少于3张的话就通知别的玩家
                    String message = player0.CardLeft((ConcreteSuject) sub, main.playerList[0], 0);
                    main.cardLeft.setText(message);
                    main.cardLeft.setVisible(true);
                }
                main.turn = (main.turn + 1) % 3;     //换下一个玩家出牌
                if (win(0))
                {
                    //设置游戏结束标志
                    main.setFinishFlag(1);      
                    if (lordturn == 0) {
                        // 地主获胜，加分、金豆数
                        lordWin = 1;
                        setPlayerScore(0, 1, 2,lordWin);

                    } else if (lordturn == 1) {
                        // 农民获胜，加分、金豆数
                        lordWin = 0;
                        setPlayerScore(1, 0, 2,lordWin);
                    }
                    else if(lordturn == 2){
                         // 农民获胜，加分、金豆数
                        lordWin = 0;
                        setPlayerScore(2, 0, 1,lordWin);
                    }
                    main.cardLeft.setVisible(false);
                    System.out.println(lordWin);
                    break;
                }
            }
            if (main.turn == 2) //玩家2（电脑）出牌
            {
                computer2();
                sub.attach(player2);
                if (main.playerList[2].size() < 3) {
                    //观察者模式的应用，如果当前玩家的排数少于3张的话就通知别的玩家
                    String message = player2.CardLeft((ConcreteSuject) sub, main.playerList[2], 2);
                    //System.out.println(message);
                    main.cardLeft.setText(message);
                    main.cardLeft.setVisible(true);
                }
                main.turn = (main.turn + 1) % 3;
                if (win(2))//判断输赢
                {
                    //地主获胜，加分、金豆数
                    main.setFinishFlag(1);
                    if (lordturn == 2) {
                        lordWin = 1;
                        setPlayerScore(2, 0, 1,lordWin);

                    } else if (lordturn == 1) {
                       //农民获胜，加分、金豆数
                        lordWin = 0;
                        setPlayerScore(1, 0, 2,lordWin);
                    }
                    else if(lordturn == 0){
                       //农民获胜，加分、金豆数
                        lordWin = 0;
                        setPlayerScore(0, 1, 2,lordWin);
                    }
                    main.cardLeft.setVisible(false);
                    break;
                }
            }
        }
        //观察者模式结束
    }
    
    //设置游戏结束时的得分，访问者模式的应用
    public void setPlayerScore(int lordid, int farmer1ID, int farmer2ID, int lordWin) {
        EmlementList list = new EmlementList();
        Element farmer1, farmer2, lord;
        farmer1 = new ConcreteElementFarmer(this.playerInfo, farmer1ID,1-lordWin);
        farmer2 = new ConcreteElementFarmer(this.playerInfo, farmer2ID,1-lordWin);
        lord = new ConcreteElementLord(this.playerInfo, lordid,lordWin);

        list.addElement(farmer1);
        list.addElement(farmer2);
        list.addElement(lord);

        Visitor visitor;
        visitor = new ConcretVisitor();
        list.accept(visitor);
    }

    // 等待i秒
    public void second(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 打开出牌按钮
    public void turnOn(boolean flag) {
        main.publishCard[0].setVisible(flag);     //出牌按钮
        main.publishCard[1].setVisible(flag);     //不要按钮
    }

    // 玩家0（电脑）出牌
    public void computer0() {
        timeWait(2, 0); // 定时
        main.cardLeft.setVisible(false);
        ShowCard(0); // 出牌
    }

    // 玩家2（电脑）出牌
    public void computer2() {
        timeWait(2, 2); // 定时
        main.cardLeft.setVisible(false);
        ShowCard(2); // 出牌
    }

    // 走牌
    public void ShowCard(int role) {
        Model1 model = operation.getModel(main.playerList[role]);
        // 待走的牌
        List<String> list = new ArrayList();
        List<String> a1 = model.getA1();
        List<String> a2 = model.getA2();
        List<String> a3 = model.getA3();
        List<String> a111222 = model.getA111222();
        List<String> a123 = model.getA123();
        List<String> a112233 = model.getA112233();
        List<String> a4 = model.getA4();
        // 如果是主动出牌
        if (main.time[(role + 1) % 3].getText().equals("不要")
                && main.time[(role + 2) % 3].getText().equals("不要")) {
            // 有单出单 (除开3带，飞机能带的单牌)
            if (a1.size() > (a111222.size() * 2 + a3.size())) {
                list.add(a1.get(a1.size() - 1));
            }// 有对子出对子 (除开3带，飞机)
            else if (a2.size() > (a111222.size() * 2 + a3
                    .size())) {
                list.add(a2.get(a2.size() - 1));
            }// 有顺子出顺子
            else if (a123.size() > 0) {
                list.add(a123.get(a123.size() - 1));
            }// 有3带就出3带，没有就出光3
            else if (a3.size() > 0) {
                // 3带单,且非关键时刻不能带王，2
                if (a1.size() > 0) {
                    list.add(a1.get(a1.size() - 1));
                }// 3带对
                else if (a2.size() > 0) {
                    list.add(a2.get(a2.size() - 1));
                }
                list.add(a3.get(a3.size() - 1));
            }// 有双顺出双顺
            else if (a112233.size() > 0) {
                list.add(a112233.get(a112233.size() - 1));
            }// 有飞机出飞机
            else if (a111222.size() > 0) {
                String name[] = a111222.get(0).split(",");
                // 带单
                if (name.length / 3 <= a1.size()) {
                    list.add(a111222.get(a111222.size() - 1));
                    for (int i = 0; i < name.length / 3; i++) {
                        list.add(a1.get(i));
                    }
                } else if (name.length / 3 <= a2.size())// 带双
                {
                    list.add(a111222.get(a111222.size() - 1));
                    for (int i = 0; i < name.length / 3; i++) {
                        list.add(a2.get(i));
                    }
                }
                // 有炸弹出炸弹
            } else if (a4.size() > 0) {
                // 4带2,1
                int sizea1 = a1.size();
                int sizea2 = a2.size();
                if (sizea1 >= 2) {
                    list.add(a1.get(sizea1 - 1));
                    list.add(a1.get(sizea1 - 2));
                    list.add(a4.get(0));

                } else if (sizea2 >= 2) {
                    list.add(a2.get(sizea1 - 1));
                    list.add(a2.get(sizea1 - 2));
                    list.add(a4.get(0));

                } else {// 直接炸
                    list.add(a4.get(0));

                }

            }
        }// 如果是跟牌
        else {
            List<Card> player = main.currentList[(role + 2) % 3].size() > 0
                    ? main.currentList[(role + 2) % 3]
                    : main.currentList[(role + 1) % 3];

            CardType cType = operation.jugdeType(player);
            //如果是单牌
            if (cType == CardType.c1) {
                AI_1(a1, player, list, role);
            }//如果是对子
            else if (cType == CardType.c2) {
                AI_1(a2, player, list, role);
            }//3带
            else if (cType == CardType.c3) {
                AI_1(a3, player, list, role);
            }//炸弹
            else if (cType == CardType.c4) {
                AI_1(a4, player, list, role);
            }//如果是3带1
            else if (cType == CardType.c31) {
                //偏家 涉及到拆牌
                //if((role+1)%3==main.dizhuFlag)
                AI_2(a3, a1, player, list, role);
            }//如果是3带2
            else if (cType == CardType.c32) {
                //偏家
                //if((role+1)%3==main.dizhuFlag)
                AI_2(a3, a2, player, list, role);
            }//如果是4带11
            else if (cType == CardType.c411) {
                AI_5(a4, a1, player, list, role);
            } //如果是4带22
            else if (cType == CardType.c422) {
                AI_5(a4, a2, player, list, role);
            } //顺子
            else if (cType == CardType.c123) {
                AI_3(a123, player, list, role);
            } //双顺
            else if (cType == CardType.c1122) {
                AI_3(a112233, player, list, role);
            } //飞机带单
            else if (cType == CardType.c11122234) {
                AI_4(a111222, a1, player, list, role);
            } //飞机带对
            else if (cType == CardType.c1112223344) {
                AI_4(a111222, a2, player, list, role);
            }
            //炸弹
            if (list.size() == 0) {
                int len4 = a4.size();
                if (len4 > 0) {
                    list.add(a4.get(len4 - 1));
                }
            }
        }

        // 定位出牌
        main.currentList[role].clear();
        if (list.size() > 0) {
            Point point = new Point();
            if (role == 0) {
                point.x = 200;
            }
            if (role == 2) {
                point.x = 550;
            }
            point.y = (400 / 2) - (list.size() + 1) * 15 / 2;// 屏幕中部
            // 将name转换成Card
            for (int i = 0, len = list.size(); i < len; i++) {
                List<Card> cards = getCardByName(main.playerList[role],
                        list.get(i));
                for (Card card : cards) {
                    operation.move(card, card.getLocation(), point);
                    point.y += 15;
                    main.currentList[role].add(card);
                    main.playerList[role].remove(card);
                }
            }
            operation.rePosition(main, main.playerList[role], role);
        } else {
            main.time[role].setVisible(true);
            main.time[role].setText("不要");
        }
        for (Card card : main.currentList[role]) {
            card.turnFront();
        }
    }

    // 按name获得Card，方便从Model取出
    public List getCardByName(List<Card> list, String n) {
        String[] name = n.split(",");
        List cardsList = new ArrayList<Card>();
        int j = 0;
        for (int i = 0, len = list.size(); i < len; i++) {
            if (j < name.length && list.get(i).name.equals(name[j])) {
                cardsList.add(list.get(i));
                i = 0;
                j++;
            }
        }
        return cardsList;
    }
    
    //顺子
    public void AI_3(List<String> model, List<Card> player, List<String> list, int role) {

        for (int i = 0, len = model.size(); i < len; i++) {
            String[] s = model.get(i).split(",");
            if (s.length == player.size() && getValueInt(model.get(i)) > operation.getValue(player.get(0))) {
                list.add(model.get(i));
                return;
            }
        }
    }
    
    //飞机带单，双
    public void AI_4(List<String> model1, List<String> model2, List<Card> player, List<String> list, int role) {
        //排序按重复数
        player = operation.getOrder2(player);
        int len1 = model1.size();
        int len2 = model2.size();

        if (len1 < 1 || len2 < 1) {
            return;
        }
        for (int i = 0; i < len1; i++) {
            String[] s = model1.get(i).split(",");
            String[] s2 = model2.get(0).split(",");
            if ((s.length / 3 <= len2) && (s.length * (3 + s2.length) == player.size()) && getValueInt(model1.get(i)) > operation.getValue(player.get(0))) {
                list.add(model1.get(i));
                for (int j = 1; j <= s.length / 3; j++) {
                    list.add(model2.get(len2 - j));
                }
            }
        }
    }
    
    //4带1，2
    public void AI_5(List<String> model1, List<String> model2, List<Card> player,
            List<String> list, int role) {
        //排序按重复数
        player = operation.getOrder2(player);
        int len1 = model1.size();
        int len2 = model2.size();

        if (len1 < 1 || len2 < 2) {
            return;
        }
        for (int i = 0; i < len1; i++) {
            if (getValueInt(model1.get(i)) > operation.getValue(player.get(0))) {
                list.add(model1.get(i));
                for (int j = 1; j <= 2; j++) {
                    list.add(model2.get(len2 - j));
                }
            }
        }
    }
    
    //单牌，对子，3个，4个,通用
    public void AI_1(List<String> model, List<Card> player,
            List<String> list, int role) {
        //顶家
        if ((role + 1) % 3 == main.dizhuFlag) {

            for (int i = 0, len = model.size(); i < len; i++) {
                if (getValueInt(model.get(i)) > operation.getValue(player.get(0))) {
                    list.add(model.get(i));
                    break;
                }
            }
        } else {//偏家

            for (int len = model.size(), i = len - 1; i >= 0; i--) {
                if (getValueInt(model.get(i)) > operation.getValue(player.get(0))) {
                    list.add(model.get(i));
                    break;
                }
            }
        }
    }
    
    //3带1,2,4带1,2
    public void AI_2(List<String> model1, List<String> model2, List<Card> player, List<String> list, int role) {
        //model1是主牌,model2是带牌,player是玩家出的牌,,list是准备回的牌
        //排序按重复数
        player = operation.getOrder2(player);
        int len1 = model1.size();
        int len2 = model2.size();
        //如果有王直接炸了
        if (len1 > 0 && model1.get(0).length() < 10) {
            list.add(model1.get(0));
            System.out.println("王炸");
            return;
        }
        if (len1 < 1 || len2 < 1) {
            return;
        }
        for (int len = len1, i = len - 1; i >= 0; i--) {
            if (getValueInt(model1.get(i)) > operation.getValue(player.get(0))) {
                list.add(model1.get(i));
                break;
            }
        }
        list.add(model2.get(len2 - 1));
        if (list.size() < 2) {
            list.clear();
        }
    }
    
    // 延时，模拟时钟
    public void timeWait(int n, int player) {

        if (main.currentList[player].size() > 0) {
            operation.hideCards(main.currentList[player]);
        }
        if (player == 1)// 如果是玩家1，10秒到后直接下一家出牌
        {
            int i = n;
            while (main.nextPlayer == false && i > 0) {
                // main.container.setComponentZOrder(main.time[player], 0);
                main.time[player].setText("倒计时:" + i);
                main.time[player].setVisible(true);
                second(1);
                i--;
                if (i == 0) {
                    main.time[player].setText("不要");
                    second(1);
                    main.time[player].setVisible(false);
                }
            }
            if (i == -1) {
                main.time[player].setText("超时");
            }
            main.nextPlayer = false;
        } else {
            for (int i = n; i >= 0; i--) {
                second(1);
                // main.container.setComponentZOrder(main.time[player], 0);
                main.time[player].setText("倒计时:" + i);
                main.time[player].setVisible(true);
                if (i == 0) {
                    main.time[player].setVisible(false);
                }
            }
        }
        main.time[player].setVisible(false);
    }

    //通过牌name计算牌的大小
    public int getValueInt(String n) {
        String name[] = n.split(",");
        String s = name[0];
        int i = Integer.parseInt(s.substring(2, s.length()));
        if (s.substring(0, 1).equals("5")) {
            i += 3;
        }
        if (s.substring(2, s.length()).equals("1") || s.substring(2, s.length()).equals("2")) {
            i += 13;
        }
        return i;
    }

    //判断输赢
    public boolean win(int id) {
        for (int i = 0; i < 3; i++) {
            if (main.playerList[i].size() == 0) {
                String s;
                if (lordturn == id) {
                    s = "游戏结束，地主胜利！";
                } else {
                    s = "游戏结束，农民胜利！";
                }
                JOptionPane.showMessageDialog(main, s);
                return true;
            }
        }
        return false;
    }
}
