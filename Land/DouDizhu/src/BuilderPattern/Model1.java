/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuilderPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 聂娜
 * 完成时间：2017/04/09
 * 建造者模式中的具体产品：牌的类型
 */
public class Model1 {
     private List<String> a1 = new ArrayList<String>();      //单张
    private List<String> a2 = new ArrayList<String>();      //对子
    private List<String> a3 = new ArrayList<String>();      //3带
    private List<String> a123 = new ArrayList<String>();    //连子
    private List<String> a112233 = new ArrayList<String>(); //连牌
    private List<String> a111222 = new ArrayList<String>(); //飞机
    private List<String> a4 = new ArrayList<String>();      //炸弹

    public List getA1() {
        return this.a1;
    }

    public List getA2() {
        return this.a2;
    }

    public List getA3() {
        return this.a3;
    }

    public List getA123() {
        return this.a123;
    }

    public List getA112233() {
        return this.a112233;
    }

    public List getA111222() {
        return this.a111222;
    }

    public List getA4() {
        return this.a4;
    }

    public void setA1(List A1) {
        this.a1 = A1;
    }

    public void setA2(List A2) {
        this.a2 = A2;
    }

    public void setA3(List A3) {
        this.a3 = A3;
    }

    public void setA123(List A123) {
        this.a123 = A123;
    }

    public void setA112233(List A112233) {
        this.a112233 = A112233;
    }

    public void setA111222(List A111222) {
        this.a111222 = A111222;
    }

    public void setA4(List A4) {
        this.a4 = A4;
    }
}

   



