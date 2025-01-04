package com.murdock.examples.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Path("/api")
public class HolaRestResourceV1 {
    @Path("/holaV1")
    @GET
    public String hola() throws UnknownHostException {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost()
                    .getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return "Hola Dropwizard @ " + hostname;
    }
}
