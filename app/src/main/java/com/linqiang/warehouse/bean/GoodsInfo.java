package com.linqiang.warehouse.bean;

public class GoodsInfo  {
    private String goodsNumber;
    private String goodsSize;
    private String goodsSum;

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(String goodsSum) {
        this.goodsSum = goodsSum;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "goodsNumber='" + goodsNumber + '\'' +
                ", goodsSize='" + goodsSize + '\'' +
                ", goodsSum='" + goodsSum + '\'' +
                '}';
    }
}
