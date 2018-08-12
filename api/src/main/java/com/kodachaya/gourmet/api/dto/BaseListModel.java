package com.kodachaya.gourmet.api.dto;

import java.util.List;

public class BaseListModel<T> {

    private int count;
    private int totalCount;
    private int cursor;
    private List<T> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseListModel{" +
                "count=" + count +
                ", totalCount=" + totalCount +
                ", cursor=" + cursor +
                ", data=" + data +
                '}';
    }
}
