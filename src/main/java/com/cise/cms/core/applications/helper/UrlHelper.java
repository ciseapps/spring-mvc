package com.cise.cms.core.applications.helper;

import javax.servlet.http.HttpServletRequest;

public class UrlHelper {

    public static String baseUrl(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }

    public static String baseUrl(HttpServletRequest req, String path) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/" + path;
    }

}
