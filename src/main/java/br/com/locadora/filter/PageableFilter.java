package br.com.locadora.filter;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import java.util.Map;

public class PageableFilter {
    int first;
    int pageSize;
    Map<String, SortMeta> sortBy;
    Map<String, FilterMeta> filterBy;

    public PageableFilter(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        this.first = first;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.filterBy = filterBy;
    }

    public PageableFilter(Map<String, FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }

    public PageableFilter() {
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, SortMeta> getSortBy() {
        return sortBy;
    }

    public void setSortBy(Map<String, SortMeta> sortBy) {
        this.sortBy = sortBy;
    }

    public Map<String, FilterMeta> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(Map<String, FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }
}
