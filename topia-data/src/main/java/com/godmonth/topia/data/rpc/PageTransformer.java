package com.godmonth.topia.data.rpc;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.godmonth.topia.data.page.PageUtils;
import com.godmonth.topia.data.page.SortUtils;
import com.godmonth.topia.pagination.Pagination;
import com.godmonth.topia.pagination.SortPageParam;
import jodd.bean.BeanCopy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public class PageTransformer {

    public static <T> Pagination<T> createPagination(List<T> pageContent, int totalElements, SortPageParam spp) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setSortPageParam(spp);
        pagination.setTotalElements(totalElements);
        int pageSize = spp.getSize();
        pagination.setTotalPages(totalElements / pageSize + (totalElements % pageSize > 0 ? 1 : 0));
        pagination.setItems(pageContent);
        return pagination;
    }

    public static <T> Pagination<T> extractPageFromFullList(List<T> rawList, SortPageParam spp) {
        int totalElements = rawList.size();
        int totalPages = totalElements / spp.getSize() + totalElements % spp.getSize() > 0 ? 1 : 0;
        int fromIndex = Math.min(totalElements, spp.getNumber() * spp.getSize());
        int toIndex = Math.min(totalElements, fromIndex + spp.getSize());
        List<T> subList = rawList.subList(fromIndex, toIndex);
        Pagination<T> pagination = new Pagination<>();
        pagination.setSortPageParam(spp);
        pagination.setTotalElements(totalElements);
        pagination.setTotalPages(totalPages);
        pagination.setItems(subList);
        return pagination;
    }




    public static <IN, OUT> Pagination<OUT> transform(Pagination<IN> page, java.util.function.Function<IN, OUT> function) {
        if (page == null) {
            return null;
        }
        Pagination<OUT> pagination = new Pagination<>();
        BeanCopy.beans(page, pagination).exclude("items").copy();
        pagination.setItems(page.getItems().stream().map(function).collect(Collectors.toList()));
        return pagination;
    }


    public static <IN, OUT> Pagination<OUT> transform(Page<IN> page, java.util.function.Function<IN, OUT> function) {
        if (page == null) {
            return null;
        }
        Pagination<OUT> pagination = new Pagination<OUT>();
        pagination.setTotalElements(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        if (page.getContent() != null) {
            pagination.setItems(page.getContent().stream().map(function).collect(Collectors.toList()));
        }
        pagination.setSortPageParam(
                new SortPageParam(page.getNumber(), page.getSize(), SortUtils.convert(page.getSort())));
        return pagination;
    }

    public static <IN, OUT> Page<OUT> transformPage(Page<IN> page, java.util.function.Function<IN, OUT> function) {
        if (page == null) {
            return null;
        }
        List<OUT> collect = page.getContent().stream().map(function).collect(Collectors.toList());
        return new PageImpl<OUT>(collect, page.getPageable(), page.getTotalElements());
    }

    public static <T> Pagination<T> transform(Page<T> page) {
        if (page == null) {
            return null;
        }
        Pagination<T> pagination = new Pagination<T>();
        pagination.setTotalElements(page.getTotalElements());
        pagination.setTotalPages(page.getTotalPages());
        pagination.setItems(page.getContent());
        pagination.setSortPageParam(
                new SortPageParam(page.getNumber(), page.getSize(), SortUtils.convert(page.getSort())));
        return pagination;
    }

    public static <T> Page<T> transform(Pagination<T> pagination) {
        if (pagination == null) {
            return null;
        }
        PageRequest pageRequest = PageUtils.convert(pagination.getSortPageParam());
        return new PageImpl<T>(pagination.getItems(), pageRequest, pagination.getTotalElements());
    }
}
