package com.keysun.common;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 扎金花
 *
 * @author sunwh
 * @create 2018-02-01 20:09
 */
public class GoldenFlower {
    /**
     * 共52张普通牌，牌面为2,3,4,5,6,7,8,9,10,J,Q,K,A之一，大小递增，各四张； 每人抓三张牌。两人比较手中三张牌大小，大的人获胜。
     * 对于牌型的规则如下：
     * 1.三张牌一样即为豹子
     * 2.三张牌相连为顺子（A23不算顺子）
     * 3.有且仅有两张牌一样为对子 豹子>顺子>对子>普通牌型 在牌型一样时，比较牌型数值大小
     * （如AAA>KKK,QAK>534，QQ2>10104） 在二人均无特殊牌型时，依次比较三张牌中最大的。
     * 大的人获胜，如果最大的牌一样，则比较第二大，以此类推（如37K>89Q） 如二人牌面相同，则为平局。
     * 输入描述:
     * 输入两个字符串代表两个玩家的牌（如”10KQ” “354”），先输入的作为玩家1，后输入的作为玩家2
     * 输出描述:
     * 1 代表 玩家1赢 0 代表 平局 -1 代表 玩家2赢 -2 代表不合法的输入
     * 输入例子:
     * KQ3 3Q9
     * 10QA 6102
     * 5810 7KK
     * 632 74J
     * 10102 K77
     * JKJ 926
     * 68K 27A
     * 输出例子:
     * 1
     * 1
     * -1
     * -1
     * 1
     * 1
     * -1
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Integer> init = init();
        while (true){
            Scanner in1 = new Scanner(System.in);
            String[] name = in1.nextLine().split(" ");
            int compare = compare(name[0],name[1],init);
            System.out.println(compare);
        }

    }

    public static Map<String, Integer> init() {
        Map<String, Integer> value = new HashMap<String, Integer>();
        value.put("2", 1);
        value.put("3", 2);
        value.put("4", 3);
        value.put("5", 4);
        value.put("6", 5);
        value.put("7", 6);
        value.put("8", 7);
        value.put("9", 8);
        value.put("10", 9);
        value.put("J", 10);
        value.put("Q", 11);
        value.put("K", 12);
        value.put("A", 13);
        return value;
    }

    public static int compare(String first, String second, Map<String, Integer> value) {
        int result;
        int[] firstData = parse(first, value);
        int[] secondData = parse(second, value);
        if (getType(firstData) > getType(secondData)) {
            result = 1;
        } else if (getType(firstData) == getType(secondData)) {
            result = 0;
        } else {
            result = -1;
        }

        return result;
    }

    public static int getType(int[] data) {
        int type;
        int value;
        if (data[0] == data[1] && data[1] == data[2]) {
            type = 4;
            value = data[1];
        } else if (data[0] == data[1] - 1 && data[2] == data[1] + 1) {
            type = 3;
            value = data[1];
        } else if (data[0] == data[1] || data[1] == data[2]) {
            type = 2;
            value = data[1];
        } else {
            type = 1;
            value = data[2];
        }
        return type * 100 + value;
    }

    public static int[] parse(String str, Map<String, Integer> value) {
        int[] data = new int[3];
        if (str.contains("0")) {
            int i = str.indexOf("0");
            String ten1 = str.substring(i-1 , i+1);
            data[0] = value.get(ten1);
            String s1 = str.substring(0, i-1) + str.substring(i + 1, str.length());
            if (s1.contains("0")) {
                int j = str.indexOf("0");
                String ten2 = s1.substring(i-1, i+1);
                data[1] = value.get(ten2);
                String s2 = s1.substring(0, i-1) + s1.substring(i + 1, s1.length());
                data[2] = value.get(s2);
            } else {
                data[1] = value.get(s1.substring(0, 1));
                data[2] = value.get(s1.substring(1, s1.length()));
            }
        } else {
            data[0] = value.get(str.substring(0, 1));
            data[1] = value.get(str.substring(1, 2));
            data[2] = value.get(str.substring(2, str.length()));
        }
        Arrays.sort(data);
        return data;
    }
}
