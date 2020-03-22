package com.mjamsek.auth.service;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.annotation.Priority;
import javax.interceptor.Interceptor;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Priority(Interceptor.Priority.PLATFORM_BEFORE)
public class NoRoleExceptionMapper implements ResponseExceptionMapper<RuntimeException> {
    
    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status == 403;
    }
    
    @Override
    public RuntimeException toThrowable(Response response) {
        return new RuntimeException("403 ERROR!");
    }
}
