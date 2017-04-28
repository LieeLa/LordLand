/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;
import BuilderPattern.ConcreteModelBuilder;
import BuilderPattern.Model1;
import BuilderPattern.ModelBuilder;
import BuilderPattern.ModelController;
import doudizhu.Card;
import doudizhu.CardType;
import doudizhu.playWin;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/12
 * 适配者1：对整副牌操作类
 */
public class ListOperation {
    //判断牌型
    public static CardType jugdeType(List<Card> list) {
        //因为之前排序过所以比较好判断
        int len = list.size();
        //单牌,对子，3不带，4个一样炸弹
        if (len <= 4) {	//如果第一个和最后个相同，说明全部相同
            if (list.size() > 0 && CardOpration.getValue(list.get(0))
                    == CardOpration.getValue(list.get(len - 1))) {
                switch (len) {
                    case 1:
                        return CardType.c1;
                    case 2:
                        return CardType.c2;
                    case 3:
                        return CardType.c3;
                    case 4:
                        return CardType.c4;
                }
            }
            //双王,化为对子返回
            if (len == 2 && CardOpration.getColor(list.get(1)) == 5) {
                return CardType.c4;
            }
            //return CardType.c2;
            //当第一个和最后个不同时,3带1
            if (len == 4 && ((CardOpration.getValue(list.get(0)) == CardOpration.getValue(list.get(len - 2)))
                    || CardOpration.getValue(list.get(1)) == CardOpration.getValue(list.get(len - 1)))) {
                return CardType.c31;
            } else {
                return CardType.c0;
            }
        }
        //当5张以上时，连字，3带2，飞机，2顺，4带2等等
        if (len >= 5) {
            //现在按相同数字最大出现次数
            Card_index card_index = new Card_index();
            for (int i = 0; i < 4; i++) {
                card_index.a[i] = new ArrayList<Integer>();
            }
            //求出各种数字出现频率
            ListOperation.getMax(card_index, list); //a[0,1,2,3]分别表示重复1,2,3,4次的牌
            //3带2 -----必含重复3次的牌
            if (card_index.a[2].size() == 1 && card_index.a[1].size() == 1 && len == 5) {
                return CardType.c32;
            }
            //4带2(单,双)
            if (card_index.a[3].size() == 1 && len == 6) {
                return CardType.c411;
            }
            if (card_index.a[3].size() == 1 && card_index.a[1].size() == 2 && len == 8) {
                return CardType.c422;
            }
            //順子,保证不存在王
            if ((CardOpration.getColor(list.get(0)) != 5) && (card_index.a[0].size() == len)
                    && (CardOpration.getValue(list.get(0)) - CardOpration.getValue(list.get(len - 1)) == len - 1)) {
                return CardType.c123;
            }
            //连队
            if (card_index.a[1].size() == len / 2 && len % 2 == 0 && len / 2 >= 3
                    && (CardOpration.getValue(list.get(0)) - CardOpration.getValue(list.get(len - 1)) == (len / 2 - 1))) {
                return CardType.c1122;
            }
            //飞机
            if (card_index.a[2].size() == len / 3 && (len % 3 == 0)
                    && (CardOpration.getValue(list.get(0)) - CardOpration.getValue(list.get(len - 1)) == (len / 3 - 1))) {
                return CardType.c111222;
            }
            //飞机带n单,n/2对
            if (card_index.a[2].size() == len / 4
                    && ((Integer) (card_index.a[2].get(len / 4 - 1)) - (Integer) (card_index.a[2].get(0)) == len / 4 - 1)) {
                return CardType.c11122234;
            }

            //飞机带n双
            if (card_index.a[2].size() == len / 5 && card_index.a[2].size() == len / 5
                    && ((Integer) (card_index.a[2].get(len / 5 - 1)) - (Integer) (card_index.a[2].get(0)) == len / 5 - 1)) {
                return CardType.c1112223344;
            }

        }
        return CardType.c0;
    }
    //对list排序，
    public static void order(List<Card> list) {
        Collections.sort(list, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                // TODO Auto-generated method stub
                int a1 = Integer.parseInt(o1.getName().substring(0, 1));//花色
                int a2 = Integer.parseInt(o2.getName().substring(0, 1));
                int b1 = Integer.parseInt(o1.getName().substring(2, o1.getName().length()));//数值
                int b2 = Integer.parseInt(o2.getName().substring(2, o2.getName().length()));
                int flag = 0;
                //如果是王的话
                if (a1 == 5 && b1 == 2) {
                    b1 += 100;   //大王
                } else if (a1 == 5 && b1 == 1) {
                    b1 += 50;    //小王
                } else if (a2 == 5 && b2 == 2) {
                    b2 += 100;
                } else if (a2 == 5 && b2 == 1) {
                    b2 += 50;
                } //如果是A或者2
                else if (b1 == 1) {
                    b1 += 20;
                } else if (b2 == 1) {
                    b2 += 20;
                } else if (b1 == 2) {
                    b1 += 30;
                } else if (b2 == 2) {
                    b2 += 30;
                }
                flag = b2 - b1;
                if (flag == 0) {
                    return a2 - a1;  //如果兩張牌的大小一樣，則按照花色排序
                } else {
                    return flag;
                }
            }
        });
    }
    //重新定位 flag代表电脑1 ,2 或者是我
    public static void rePosition(playWin m, List<Card> list, int flag) {
        Point p = new Point();
        if (flag == 0) {
            p.x = 50;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        if (flag == 1) {//我的排序 _y=450 width=830
            p.x = (800 / 2) - (list.size() + 1) * 21 / 2;
            p.y = 450;
        }
        if (flag == 2) {
            p.x = 700;
            p.y = (450 / 2) - (list.size() + 1) * 15 / 2;
        }
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Card card = list.get(i);
            CardOpration.move(card, card.getLocation(), p);
            m.container.setComponentZOrder(card, 0);
            if (flag == 1) {
                p.x += 21;
            } else {
                p.y += 15;
            }

        }
    }
    //地主牌权值，看是否抢地主
    public static int getScore(List<Card> list) {
        int count = 0;
        for (int i = 0, len = list.size(); i < len; i++) {
            Card card = list.get(i);
            String name = card.getName();
            //2：加2分,小王：加5分,大王：加7分。
            //如果有王，則權值加5
            if (name.substring(0, 1).equals("5")) {
                //System.out.println(card.name.substring(0, 1));
                count += 5;
            }
            //如果有2或大王,權值加2
            if (name.substring(2, name.length()).equals("2")) {
                //System.out.println(2);
                count += 2;
            }
        }
        return count;
    }
    //获得当前牌中最大的model
    public static void getMax(Card_index card_index, List<Card> list) {
        /**
         * 得到最大相同数，先創建四個list，代表含義為每個數字出現的次數為：1次、兩次、三次、四次 統計完成后，就將數字加入數組中。
         * 這一部分是為了判斷每次出牌是否為CardType中的結果，如果不符合就不允許出牌
         */
        int count[] = new int[14];//1-13各算一种,王算第14种
        for (int i = 0; i < 14; i++) {
            count[i] = 0;
        }
        for (int i = 0, len = list.size(); i < len; i++) {
            if (CardOpration.getColor(list.get(i)) == 5) {
                count[13]++;
            } else {
                count[CardOpration.getValue(list.get(i)) - 1]++;
            }
        }
        for (int i = 0; i < 14; i++) {
            switch (count[i]) {
                case 1:
                    card_index.a[0].add(i + 1);
                    break;
                case 2:
                    card_index.a[1].add(i + 1);
                    break;
                case 3:
                    card_index.a[2].add(i + 1);
                    break;
                case 4:
                    card_index.a[3].add(i + 1);
                    break;
            }
        }
    }
    //建造者模式的应用
    public static Model1 getModel(List<Card> list) {
         /*拆牌
        *將一副牌拆开，model为相应牌的list
        *1.每次将list拆成新的list和model
        *2.在新的list继续拆，重复1，沒有可以再拆的
     */
        //先复制一个list
        List<Card> list2 = new ArrayList<Card>(list);
        ModelBuilder builder = new ConcreteModelBuilder();
        ModelController controller = new ModelController();
        Model1 model;
        model = controller.construct(builder, list2);
        return model;
    }
    //建造者模式应用结束
    //隐藏之前出过的牌，移到适配器中
    public static void hideCards(List<Card> list) {
        for (int i = 0, len = list.size(); i < len; i++) {
            list.get(i).setVisible(false);
        }
    }
    //检查牌的是否能出
    public static int checkCards(List<Card> c, List<Card>[] current) {
        //找出当前最大的牌是哪个电脑出的,c是点选的牌
        List<Card> currentlist = (current[0].size() > 0) ? current[0] : current[2];
        CardType cType = ListOperation.jugdeType(c);

        //如果张数不同並且不是炸彈直接过滤 
        if (cType != CardType.c4 && c.size() != currentlist.size()) {
            return 0;
        }

        //比较我的出牌类型
        if (ListOperation.jugdeType(c) != ListOperation.jugdeType(currentlist)) {
            return 0;
        }

        //比较出的牌是否要大
        //王炸弹：如果對方出的是炸彈
        if (cType == CardType.c4) {
            //如果我出的是王炸，我大，能出
            if (c.size() == 2) {
                return 1;
            }
            //如果對方的是王炸，對方大，不能出
            if (currentlist.size() == 2) {
                return 0;
            }
        }

        //单牌,对子,3带,4炸弹
        if (cType == CardType.c1 || cType == CardType.c2 || cType == CardType.c3 || cType == CardType.c4) {
            if (CardOpration.getValue(c.get(0)) <= CardOpration.getValue(currentlist.get(0))) {
                return 0;
            } else {
                return 1;
            }
        }

        //顺子,连队，飞机裸
        if (cType == CardType.c123 || cType == CardType.c1122 || cType == CardType.c111222) {
            if (CardOpration.getValue(c.get(0)) <= CardOpration.getValue(currentlist.get(0))) {
                return 0;
            } else {
                return 1;
            }
        }

        //3带1,3带2 ,飞机带单，双,4带1,2, 
        //按重复多少排序，只需比较第一个就行
        if (cType == CardType.c31 || cType == CardType.c32 || cType == CardType.c411 || cType == CardType.c422
                || cType == CardType.c11122234 || cType == CardType.c1112223344) {
            List<Card> a1 = ListOperation.getOrder2(c); //我出的牌
            List<Card> a2 = ListOperation.getOrder2(currentlist);//当前最大牌
            if (CardOpration.getValue(a1.get(0)) < CardOpration.getValue(a2.get(0))) {
                return 0;
            }
        }
        return 1;
    }
    //按照重复次数排序
    public static List getOrder2(List<Card> list) {
        List<Card> list2 = new ArrayList<Card>(list);
        List<Card> list3 = new ArrayList<Card>();
        List<Integer> list4 = new ArrayList<Integer>();
        int len = list2.size();
        int a[] = new int[20];
        for (int i = 0; i < 20; i++) {
            a[i] = 0;
        }
        for (int i = 0; i < len; i++) {
            //數組元素對應的值為該數值的牌出現的次數
            a[CardOpration.getValue(list2.get(i))]++;
        }
        int max = 0;
        for (int i = 0; i < 20; i++) {
            max = 0;
            //找打出現次數最多的牌
            for (int j = 19; j >= 0; j--) {
                if (a[j] > a[max]) {
                    max = j;
                }
            }

            for (int k = 0; k < len; k++) {
                if (CardOpration.getValue(list2.get(k)) == max) {
                    list3.add(list2.get(k));
                }
            }
            list2.remove(list3);
            a[max] = 0;
        }
        return list3;
    }
}

