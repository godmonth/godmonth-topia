package com.godmonth.topia.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页参数
 *
 * @author shenyue
 */
@Data
@SuperBuilder(builderMethodName = "sortPPBuilder")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SortPageParam extends PageParam {

    /**
     * 排序
     */
    @Valid
    private List<SortParam> sortParamList;


    /**
     * @param number
     * @param size
     */
    public SortPageParam(int number, int size) {
        super(number, size);
    }

    public SortPageParam(PageParam pageParam, SortParam sortParam) {
        this(pageParam.getNumber(), pageParam.getSize(), sortParam);
    }

    public SortPageParam(int number, int size, SortParam sortParam) {
        super(number, size);
        List<SortParam> list = new ArrayList<SortParam>();
        list.add(sortParam);
        this.sortParamList = list;
    }

    public SortPageParam(int number, int size, List<SortParam> sortParamList) {
        super(number, size);
        this.sortParamList = sortParamList;
    }


}
