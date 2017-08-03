package com.daboo.web.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AvoidDuplicateSubmission{
	/**
	 * 是否需要保存token
	 * @return
	 */
	boolean needSaveToken() default false;
	/**
	 * 删除token
	 * @return
	 */
	boolean needRemoveToken() default false;
}