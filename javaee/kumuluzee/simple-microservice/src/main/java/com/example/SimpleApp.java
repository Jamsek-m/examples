package com.example;

import com.kumuluz.ee.cors.annotations.CrossOrigin;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1")
@CrossOrigin(allowOrigin = "*", supportedMethods = "GET,POST,PUT,DELETE,OPTIONS")
public class SimpleApp extends Application {
}
