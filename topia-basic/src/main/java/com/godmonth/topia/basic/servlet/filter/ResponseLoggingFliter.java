package com.godmonth.topia.basic.servlet.filter;

import org.apache.commons.lang3.StringUtils;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseLoggingFliter implements Filter {

	private RequestPathMatcherHelper excludeLogPathMatcherHelper;
	private Integer maxPayloadLength;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (shouldLog((HttpServletRequest) request)) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			HttpServletResponseCopier responseCopier = new HttpServletResponseCopier((HttpServletResponse) response,
					byteArrayOutputStream);
			chain.doFilter(request, responseCopier);
			try {
				String string = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
				if (maxPayloadLength != null) {
					string = StringUtils.abbreviate(string, maxPayloadLength);
				}
				log.trace("response body:{}", string);
			} catch (Exception e) {
				log.error("", e);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	protected boolean shouldLog(HttpServletRequest request) {
		return log.isTraceEnabled() && !excludeLogPathMatcherHelper.match(request);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	public void setExcludeLogPathMatcherHelper(RequestPathMatcherHelper excludeLogPathMatcherHelper) {
		this.excludeLogPathMatcherHelper = excludeLogPathMatcherHelper;
	}

	public void setMaxPayloadLength(Integer maxPayloadLength) {
		this.maxPayloadLength = maxPayloadLength;
	}

}