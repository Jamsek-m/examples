package com.mjamsek.auth;

import com.mjamsek.auth.endpoint.SecureEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/v1")
public class SampleApp extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(SecureEndpoint.class);
        return classes;
    }
}
