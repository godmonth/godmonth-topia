package com.godmonth.topia.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 排序参数
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SortParam implements Serializable {
    /**
     * 方向
     */
    @NotNull
    private Direction direction;

    /**
     * 排序属性
     */
    @NotBlank
    private String property;


}
