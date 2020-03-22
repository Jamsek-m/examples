package com.mjamsek.auth;

import com.mjamsek.auth.endpoint.ClassLevelSecuredEndpoint;
import com.mjamsek.auth.endpoint.PublicEndpoint;
import com.mjamsek.auth.endpoint.SecuredEndpoint;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/v1")
public class SampleApp extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        
        classes.add(SecuredEndpoint.class);
        classes.add(ClassLevelSecuredEndpoint.class);
        classes.add(PublicEndpoint.class);
        
        return classes;
    }
}
