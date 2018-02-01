package com.keysun.dp;

/**
 * 股票交易日
 *
 * @author sunwh
 * @create 2018-02-01 10:18
 */
public class StockTradingDay {
    /**
     * 在股市的交易日中，假设最多可进行两次买卖(即买和卖的次数均小于等于2)，
     * 规则是必须一笔成交后进行另一笔(即买-卖-买-卖的顺序进行)。给出一天中
     * 的股票变化序列，请写一个程序计算一天可以获得的最大收益。请采用实践复
     * 杂度低的方法实现。
     * 给定价格序列prices及它的长度n，请返回最大收益。保证长度小于等于500。
     * 测试样例：[10，22，5，75，65，80]，6
     * 返回：87
     * @param args
     */
    public static void main(String[] args) {
        int[] data = {10, 22, 5, 75,1, 65, 80};
        int firstProfit = data[1]-data[0];
        int secondProfit = data[3]-data[2];
        int max = 0;
        for(int i=0;i<data.length-3;i++){
            for(int j=i+1;j<data.length-2;j++){
                firstProfit = data[j]-data[i];
                for(int l=j+1;l<data.length-1;l++){
                    for (int m=l+1;m<data.length;m++){
                        secondProfit = data[m]-data[l];
                        if(max<firstProfit+secondProfit){
                            max = firstProfit+secondProfit;
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }
}
