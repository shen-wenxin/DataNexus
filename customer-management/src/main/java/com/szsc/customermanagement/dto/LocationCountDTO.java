package com.szsc.customermanagement.dto;

public class LocationCountDTO {
    private int total;
    private int mainland;
    private int hongkong;
    private int overseas;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMainland() {
        return mainland;
    }

    public void setMainland(int mainland) {
        this.mainland = mainland;
    }

    public int getHongkong() {
        return hongkong;
    }

    public void setHongkong(int hongkong) {
        this.hongkong = hongkong;
    }

    public int getOverseas() {
        return overseas;
    }

    public void setOverseas(int overseas) {
        this.overseas = overseas;
    }

    @Override
    public String toString() {
        return "LocationCount{" +
                "total=" + total +
                ", mainland=" + mainland +
                ", hongkong=" + hongkong +
                ", overseas=" + overseas +
                '}';
    }
    
}
