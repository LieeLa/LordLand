/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuilderPattern;

import Adapter.Adapter_Card;
import Adapter.Operation;
import doudizhu.Card;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 
 * 完成时间：2017/04/09
 * 建造者模式中的具体建造者：牌的类型的具体建造者
 */
public class ConcreteModelBuilder extends ModelBuilder  {
    private Operation operation =  Adapter_Card.getIntance();

    public void buildA123(List<Card> list) {
        List<Card> del = new ArrayList<Card>();//要删除的Cards
        List<String> A123 = new ArrayList<String>();    //连子

        //1.如果最大的牌小於7或者最小的牌大於10，則不可能出現順子
        if (list.size() > 0 && (operation.getValue(list.get(0)) < 7 || operation.getValue(list.get(list.size() - 1)) > 10)) {
            return;
        }
        //2.如果牌的張數小於5，也不可能是順子
        if (list.size() < 5) {
            return;
        }
        //3.其餘情況，從第一張開始（最大的牌），一直到最後求得本次牌中最大的順子
        for (int i = 0, len = list.size(); i < len; i++) {
            int k = i;
            for (int j = i; j < len; j++) {
                if (operation.getValue(list.get(i)) - operation.getValue(list.get(j)) == j - i) {
                    k = j;
                }
            }
            if (k - i >= 4) {
                String s = "";
                for (int j = i; j < k; j++) {
                    s = s + list.get(j).getName() + ",";
                    del.add(list.get(j));
                }
                s += list.get(k).getName();
                del.add(list.get(k));
                A123.add(s);
                i = k;
            }
        }
        list.removeAll(del);
        model.setA123(A123);
    }

    //拆连队
    public void buildA112233(List<Card> list) {
        List<String> del = new ArrayList<String>();//要删除的Cards
        List<String> A112233 = new ArrayList<String>(); //连牌

        //从model里面的对子找
        List<String> l = model.getA2();
        //a2為對子對應的list，如果順子的數目<3，則代表不可能組成連隊。
        if (l.size() < 3) {
            return;
        }

        Integer s[] = new Integer[l.size()];
        for (int i = 0, len = l.size(); i < len; i++) {
            String[] name = l.get(i).split(",");
            //獲得每個對子的數值
            s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
        }
        
        

        //s0,1,2,3,4  13,9,8,7,6
        for (int i = 0, len = l.size(); i < len; i++) {
            int k = i;
            /**
             * i：連隊的開始（最大數值），j：連隊的結束（最小數值） k：連隊結束的地方
             */

            for (int j = i; j < len; j++) {
                if (s[i] - s[j] == j - i) {
                    k = j;
                }
            }
            if (k - i >= 2)//k=4 i=1
            {
                //说明从i到k是连队
                String ss = "";
                for (int j = i; j < k; j++) {
                    ss += l.get(j) + ",";
                    del.add(l.get(j));
                }
                ss += l.get(k);
                A112233.add(ss);
                del.add(l.get(k));
                i = k;
            }
        }
        l.removeAll(del);
        model.setA112233(A112233);
    }

    //拆飞机，111222
    public void buildA111222(List<Card> list) {
        List<String> del = new ArrayList<String>();//要删除的Cards
        List<String> A111222 = new ArrayList<String>(); //飞机

        //从model里面的3带找，model.a3每個元素為一個三隊
        List<String> l = model.getA3();

        //如果只有一個三帶，就沒有飛機
        if (l.size() < 2) {
            return;
        }

        Integer s[] = new Integer[l.size()];
        //獲取每張牌的數值
        for (int i = 0, len = l.size(); i < len; i++) {
            String[] name = l.get(i).split(",");
            s[i] = Integer.parseInt(name[0].substring(2, name[0].length()));
        }
        for (int i = 0, len = l.size(); i < len; i++) {
            int k = i;
            for (int j = i; j < len; j++) {
                if (s[i] - s[j] == j - i) {
                    k = j;
                }
            }
            if (k != i) {
                //说明从i到k是飞机
                String ss = "";
                for (int j = i; j < k; j++) {
                    ss += l.get(j) + ",";
                    del.add(l.get(j));
                }
                ss += l.get(k);
                A111222.add(ss);
                del.add(l.get(k));
                i = k;
            }
        }
        l.removeAll(del);

        model.setA111222(A111222);
    }

    //拆炸弹
    public void buildA4(List<Card> list) {
        List<Card> del = new ArrayList<Card>();//要删除的Cards
        List<String> A1 = model.getA1(); //飞机
        List<String> A4 = new ArrayList<String>(); //飞机
        //王炸
        if (list.size() >= 2 && operation.getColor(list.get(0)) == 5 && operation.getColor(list.get(1)) == 5) {
            A4.add(list.get(0).getName() + "," + list.get(1).getName()); //按名字加入
            del.add(list.get(0));
            del.add(list.get(1));
        }
        //如果王不构成炸弹咋先拆单
        if (operation.getColor(list.get(0)) == 5 && operation.getColor(list.get(1)) != 5) {
            del.add(list.get(0));
            A1.add(list.get(0).getName());
        }
        list.removeAll(del);
        //一般的炸弹
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i + 3 < len && operation.getValue(list.get(i)) == operation.getValue(list.get(i + 3))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName() + ",";
                s += list.get(i + 2).getName() + ",";
                s += list.get(i + 3).getName();
                A4.add(s);
                for (int j = i; j <= i + 3; j++) {
                    del.add(list.get(j));
                }
                i = i + 3;
            }
        }
        list.removeAll(del);

        model.setA1(A1);
        model.setA4(A4);
    }

    //拆3带
    public void buildA3(List<Card> list) {
        List<Card> del = new ArrayList<Card>();//要删除的Cards
        List<String> A3 = new ArrayList<String>();      //3带

        //连续3张相同
        for (int i = 0, len = list.size(); i < len; i++) {
            if (i + 2 < len && operation.getValue(list.get(i)) == operation.getValue(list.get(i + 2))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName() + ",";
                s += list.get(i + 2).getName();
                A3.add(s);
                for (int j = i; j <= i + 2; j++) {
                    del.add(list.get(j));
                }
                i = i + 2;
            }
        }
        list.removeAll(del);

        model.setA3(A3);
    }

    //拆对子，33
    public void buildA2(List<Card> list) {

        List<Card> del = new ArrayList<Card>();//要删除的Cards
        List<String> A2 = new ArrayList<String>();      //对子
        //连续2张相同
        for (int i = 0, len = list.size(); i < len; i++) {
            //如果有兩張牌的大小一樣，則為連隊
            if (i + 1 < len && operation.getValue(list.get(i)) == operation.getValue(list.get(i + 1))) {
                String s = list.get(i).getName() + ",";
                s += list.get(i + 1).getName();
                A2.add(s);
                //本次循環只會循環兩次，目的是在del中加入一個連隊
                for (int j = i; j <= i + 1; j++) {
                    del.add(list.get(j));
                }
                i = i + 1;
            }
        }
        list.removeAll(del);

        model.setA2(A2);
    }

    //拆单字,3
    public void builA1(List<Card> list) {
        List<Card> del = new ArrayList<Card>();//要删除的Cards
        List<String> A1 = model.getA1();      //单张
        for (int i = 0, len = list.size(); i < len; i++) {
            A1.add(list.get(i).getName());
            del.add(list.get(i));
        }
        list.removeAll(del);
        model.setA1(A1);
    }

    public Model1 createModel() {
        return model;
    }
}
