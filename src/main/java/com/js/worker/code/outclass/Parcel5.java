package com.js.worker.code.outclass;

/*
 * 局部内部类
 * 它是嵌套在方法和作用于内的，对于这个类的使用主要是应用与解决比较复杂的问题，想创建一个类来辅助我们的解决方案，到那时又不希望这个类是公共可用的，
 * 所以就产生了局部内部类，局部内部类和成员内部类一样被编译，只是它的作用域发生了改变，它只能在该方法和属性中被使用，出了该方法和属性就会失效。
 */
public class Parcel5 {
	
	public String destionation(String str){
        class PDestionation {
            private String label;
            private PDestionation(String whereTo){
                label = whereTo;
            }
            public String readLabel(){
            	System.out.println("label:" + label);
                return label;
            }
        }
        return new PDestionation(str).readLabel();
    }
    
    public static void main(String[] args) {
        Parcel5 parcel5 = new Parcel5();
        String label = parcel5.destionation("chenssy");
    }

}
