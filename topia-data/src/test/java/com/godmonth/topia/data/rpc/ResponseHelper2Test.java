package com.godmonth.topia.data.rpc;

import com.godmonth.topia.rpc.response.BodyResponse;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.Test;

/**
 * <p></p >
 *
 * @author shenyue
 */
class ResponseHelper2Test {
    @Test
    void name() throws Exception {
        ResponseHelper2<ClassA, ClassB> responseHelper2 = new ResponseHelper2();
        responseHelper2.setMapper(new DozerBeanMapper());
        responseHelper2.setModelClass(ClassB.class);
        responseHelper2.afterPropertiesSet();
        ClassA a = new ClassA();
        a.setName("2");
        final BodyResponse<ClassB> bodyResponse = responseHelper2.returnSuccess(a);
        System.out.println(bodyResponse);
    }
}