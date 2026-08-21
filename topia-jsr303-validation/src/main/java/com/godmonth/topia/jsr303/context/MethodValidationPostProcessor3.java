package com.godmonth.topia.jsr303.context;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

public class MethodValidationPostProcessor3 extends
		MethodValidationPostProcessor implements InitializingBean {

	public void afterPropertiesSet() {
		Pointcut pointcut = new AnnotationMatchingPointcut(Validated.class,
				true);
		Advice advice = new MethodValidationInterceptor3();
		this.advisor = new DefaultPointcutAdvisor(pointcut, advice);
	}

}
