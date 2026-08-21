package com.godmonth.topia.basic.servlet.filter;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import jakarta.servlet.http.HttpServletRequest;

public class RequestLoggingFilter extends CommonsRequestLoggingFilter {

    private RequestPathMatcherHelper excludeLogPathMatcherHelper;

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        return super.shouldLog(request) && !excludeLogPathMatcherHelper.match(request);
    }

    public void setExcludeLogPathMatcherHelper(RequestPathMatcherHelper excludeLogPathMatcherHelper) {
        this.excludeLogPathMatcherHelper = excludeLogPathMatcherHelper;
    }

}
