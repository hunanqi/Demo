package com.example.utsoft.demo.entity;

/**
 * Created by 胡楠启 on 2017/2/20.
 * Function：返回数据中结果实体类
 * Desc：
 */

public class ResultEntity  {
    /**
     * 省份
     */
    public String province;
    /**
     * 城市
     */
    public String city;
    /**
     * code
     */
    public String areacode;
    /**
     * zip
     */
    public String zip;
    /**
     * 卡类型
     */
    public String company;
    /**
     * 用户类型
     */
    public String card;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }
}
