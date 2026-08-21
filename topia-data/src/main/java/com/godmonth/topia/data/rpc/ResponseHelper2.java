package com.godmonth.topia.data.rpc;

import com.godmonth.util.dozer.DozerMapperFunction;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.godmonth.topia.basic.rpc.utils2.BodyResponseUtils;
import com.godmonth.topia.basic.rpc.utils2.ListBodyResponseUtils;
import com.godmonth.topia.pagination.Pagination;
import com.godmonth.topia.rpc.SystemCode;
import com.godmonth.topia.rpc.response.BodyResponse;
import com.godmonth.topia.rpc.response.ListBodyResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseHelper2<DOMAIN, MODEL extends Serializable> implements InitializingBean {

    protected Class<MODEL> modelClass;

    protected Mapper mapper;

    protected Function<DOMAIN, MODEL> function;

    @Override
    public void afterPropertiesSet() throws Exception {
        function = new DozerMapperFunction<DOMAIN, MODEL>(mapper, modelClass);
    }

    public BodyResponse<MODEL> returnSuccess(DOMAIN domain) {
        if (domain != null) {
            return BodyResponseUtils.successBodyResponse(getFunction().apply(domain));
        } else {
            return BodyResponseUtils.successBodyResponse(null);
        }
    }

    public ListBodyResponse<MODEL> returnListSuccess(List<DOMAIN> domainList) {
        if (CollectionUtils.isNotEmpty(domainList)) {
            List<MODEL> modelList = Lists.transform(domainList, getFunction());
            return ListBodyResponseUtils.successListBodyResponse(modelList);
        } else {
            return ListBodyResponseUtils.successListBodyResponse(null);
        }
    }

    public BodyResponse<Pagination<MODEL>> returnPageSuccess(Page<DOMAIN> domainPage) {
        Pagination<MODEL> transform = PageTransformer.transform(domainPage, getFunction());
        return BodyResponseUtils.successBodyResponse(transform);
    }

    public BodyResponse<Pagination<MODEL>> returnPageSuccess(Pagination<DOMAIN> domainPage) {
        Pagination<MODEL> transform = PageTransformer.transform(domainPage, getFunction());
        return BodyResponseUtils.successBodyResponse(transform);
    }

    public <K> BodyResponse<MODEL> returnError(K key, Exception e) {
        log.error("", e);
        BodyResponse<MODEL> sor = BodyResponseUtils.codeBodyResponse(null, SystemCode.FAILURE, null,
                e.getMessage());

        sor.getHeader().setTrace(ExceptionUtils.getStackTrace(e));
        if (key != null) {
            DOMAIN domain = findModel(key);
            if (domain != null) {
                sor.setBody(getFunction().apply(domain));
            }
        }
        return sor;
    }

    protected DOMAIN findModel(Object key) {
        return null;
    }

    public void setModelClass(Class<MODEL> modelClass) {
        this.modelClass = modelClass;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    public Function<DOMAIN, MODEL> getFunction() {
        return function;
    }

}
