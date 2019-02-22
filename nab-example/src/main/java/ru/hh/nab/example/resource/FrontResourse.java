package ru.hh.nab.example.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/front")
public class FrontResourse {
    private String frontPath = "src/main/resources/vanillamvc/";

    @GET
    @Path("/")
    public InputStream getIndex() {
        File file = new File(frontPath + "index.html");
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/{path0}")
    public InputStream getRootScript(@PathParam("path0") String path0) {
        File file = new File(frontPath + path0);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/{path0}/{path1}")
    public InputStream getSecontScript(@PathParam("path0") String path0, @PathParam("path1") String path1) {
        File file = new File(frontPath + path0 + "/"+path1);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    @GET
    @Path("/{path0}/{path1}/{path2}")
    public InputStream getThirdScript(@PathParam("path0") String path0, @PathParam("path1") String path1, @PathParam("path2") String path2) {
        File file = new File(frontPath + path0 + "/"+path1+ "/"+path2);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
