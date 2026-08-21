package com.godmonth.topia.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;

/**
 * 分页参数
 *
 * @author shenyue
 */
@Data
@SuperBuilder(builderMethodName = "oneSortPPBuilder")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OneSortPageParam extends PageParam {

    /**
     * 排序
     */
    @Valid
    private SortParam sortParam;

    /**
     * @param number
     * @param size
     */
    public OneSortPageParam(int number, int size) {
        super(number, size);
    }

    public OneSortPageParam(int number, int size, SortParam sortParam) {
        super(number, size);
        this.sortParam = sortParam;
    }


}
