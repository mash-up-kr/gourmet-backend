package com.kodachaya.gourmet.api.dto;

import java.util.List;
import java.util.Optional;

public class BaseListModel<T> {

    private int count;
    private int totalCount;
    private Optional<Integer> nextPage;
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

    public Optional<Integer> getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = Optional.ofNullable(nextPage);
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
                ", nextPage=" + nextPage +
                ", data=" + data +
                '}';
    }
}
