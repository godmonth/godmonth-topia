package com.godmonth.topia.rpc.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorCodeImpl implements ErrorCode {

    private String code;

    private String template;

}
