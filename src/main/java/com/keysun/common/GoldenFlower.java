package com.keysun.common;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ����
 *
 * @author sunwh
 * @create 2018-02-01 20:09
 */
public class GoldenFlower {
    /**
     * ��52����ͨ�ƣ�����Ϊ2,3,4,5,6,7,8,9,10,J,Q,K,A֮һ����С�����������ţ� ÿ��ץ�����ơ����˱Ƚ����������ƴ�С������˻�ʤ��
     * �������͵Ĺ������£�
     * 1.������һ����Ϊ����
     * 2.����������Ϊ˳�ӣ�A23����˳�ӣ�
     * 3.���ҽ���������һ��Ϊ���� ����>˳��>����>��ͨ���� ������һ��ʱ���Ƚ�������ֵ��С
     * ����AAA>KKK,QAK>534��QQ2>10104�� �ڶ��˾�����������ʱ�����αȽ������������ġ�
     * ����˻�ʤ�����������һ������Ƚϵڶ����Դ����ƣ���37K>89Q�� �����������ͬ����Ϊƽ�֡�
     * ��������:
     * ���������ַ�������������ҵ��ƣ��硱10KQ�� ��354���������������Ϊ���1�����������Ϊ���2
     * �������:
     * 1 ���� ���1Ӯ 0 ���� ƽ�� -1 ���� ���2Ӯ -2 �����Ϸ�������
     * ��������:
     * KQ3 3Q9
     * 10QA 6102
     * 5810 7KK
     * 632 74J
     * 10102 K77
     * JKJ 926
     * 68K 27A
     * �������:
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
