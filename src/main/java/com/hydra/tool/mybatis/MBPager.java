package com.hydra.tool.mybatis;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hydra.tool.system.Sys;

import java.util.List;
import java.util.Map;

/**
 * Created by ZhengGong on 15/4/18.
 * Description mybatis分页工具
 */
public class MBPager {
    public static final int MAX_SHOW_NUMBER = 1000;
    public static final MBPager ONE_PAGER = MBPager.create(1, 1);
    public static final MBPager MAX_PAGER = MBPager.create(1, MAX_SHOW_NUMBER);

    private int showCount;
    private int currentPage;
    private int pageRows;
    private String pageOrder;

    private MBPager() {

    }

    public Map<String, Object> format() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("pageOrder", pageOrder);
        params.put("pageStart", showCount * (currentPage - 1));
        params.put("pageRows", pageRows);

        return params;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageRows() {
        return pageRows;
    }

    public String getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    public void setPageRows(int pageRows) {
        this.pageRows = pageRows;
    }

    public static List<MBPager> createByCount(long count, int showCount, Order order) {
        List<MBPager> list = Lists.newArrayList();
        if (count > 0) {
            int sign = 0;
            if (count % showCount > 0) {
                sign = 1;
            }

            int page = (int) (count / showCount + sign);
            for (int i = 1; i < page + 1; i++) {
                MBPager pager = MBPager.create(i, showCount, order);
                if (i == page && sign == 1) {
                    int next = (int) (count % showCount);
                    pager.setPageRows(next);
                }
                list.add(pager);
            }
        }

        return list;
    }

    public static MBPager create(int currentPage, int showCount) {
        return create(currentPage, showCount, null);
    }

    public static MBPager create(int currentPage, int showCount, Order order) {
        if (currentPage < 1) {
            throw new RuntimeException("currentPage is error");
        }

        if (showCount < 1) {
            throw new RuntimeException("showCount is error");
        }

        MBPager mlsPager = new MBPager();
        mlsPager.currentPage = currentPage;
        mlsPager.showCount = showCount;
        mlsPager.pageRows = showCount;

        if (order != null) {
            mlsPager.pageOrder = order.format();
        }
        return mlsPager;
    }

    public static class Order {
        private ESort sort;
        private String name;

        private Order() {

        }

        public static Order create(ESort sort, String name) {
            Order order = new Order();
            order.sort = sort;
            order.name = name;
            return order;
        }

        public String format() {
            return name + " " + sort.value;
        }

        public String getName() {
            return name;
        }

        public static enum ESort {
            DESC("desc"), ASC("asc");

            public final String value;

            private ESort(String value) {
                this.value = value;
            }
        }
    }

    @Override
    public String toString() {
        return "MBPager{" +
                "showCount=" + showCount +
                ", currentPage=" + currentPage +
                ", pageRows=" + pageRows +
                ", pageOrder='" + pageOrder + '\'' +
                '}';
    }

    public static void main(String[] args) {

        List<MBPager> pagerList = MBPager.createByCount(101, 20, Order.create(Order.ESort.DESC, "account_day"));

        for (MBPager pager : pagerList) {
            Sys.pl(pager.format());
        }
    }
}
