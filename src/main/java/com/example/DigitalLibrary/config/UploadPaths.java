package com.example.DigitalLibrary.config;

public class UploadPaths {
    public static String UPLOADADHAARPATH(Long id) {return "/assets/library/doc/" + id + "/";}
    public static String GETADHAARPATH(Long id) { return "/assets/library/doc/" + id + "/"; }
    public static String UPLOADPROFILEPATH(Long id) {return "/assets/library/profile/" + id + "/";}
    public static String GETPROFILEPATH(Long id) { return "/assets/library/profile/" + id + "/"; }
    public static String UPLOADCOVERIMGPATH(Long id) {return "/assets/library/cover_image/" + id + "/";}
    public static String GETCOVERIMGPATH(Long id) { return "/assets/library/cover_image/" + id + "/"; }
}
