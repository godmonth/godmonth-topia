package com.godmonth.topia.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 * 分页参数
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageParam implements Serializable {

    /**
     * 分页号
     */
    @Min(0)
    private int number;
    /**
     * 页尺寸
     */
    @Min(1)
    private int size;

}
