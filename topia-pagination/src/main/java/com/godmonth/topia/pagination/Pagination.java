package com.godmonth.topia.pagination;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页
 *
 * @param <T>
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class Pagination<T> implements Serializable {
    /**
     * 总条目数
     */
    @Min(0)
    private long totalElements;
    /**
     * 总分页数
     */
    @Min(0)
    private long totalPages;
    /**
     * 分页参数
     */
    @NotNull
    @Valid
    private SortPageParam sortPageParam;

    /**
     * 当前分页条目
     */
    @NotNull
    private List<T> items;

    public Pagination(@Min(0) long totalElements, @Min(0) long totalPages, @NotNull @Valid SortPageParam sortPageParam, @NotNull List<T> items) {
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.sortPageParam = sortPageParam;
        this.items = items;
    }

    public <U> Pagination<U> map(Function<? super T, ? extends U> converter) {
        Pagination<U> pagination = new Pagination<>();
        List<U> convertedContent = getConvertedContent(converter);
        pagination.setItems(convertedContent);
        pagination.setTotalElements(getTotalElements());
        pagination.setTotalPages(getTotalPages());
        pagination.setSortPageParam(getSortPageParam());
        return pagination;
    }

    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        if (items == null) {
            return null;
        }
        return items.stream().map(converter::apply).collect(Collectors.toList());
    }


}
