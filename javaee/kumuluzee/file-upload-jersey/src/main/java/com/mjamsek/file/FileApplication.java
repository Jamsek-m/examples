package com.mjamsek.file;

import com.mjamsek.file.resources.FileResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class FileApplication extends ResourceConfig {
    
    public FileApplication() {
        // register jersey's multipart feature - required for file upload
        register(MultiPartFeature.class);
        // register our endpoint
        register(FileResource.class);
    }
}
