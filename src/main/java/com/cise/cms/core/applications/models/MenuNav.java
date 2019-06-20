package com.cise.cms.core.applications.models;

public class MenuNav {

    private long rootId;
    private long id;
    private String name;
    private String url;


    public MenuNav() {
    }

    public MenuNav(long rootId, String name) {
        this.rootId = rootId;
        this.name = name;
    }

    public MenuNav(long rootId, long id, String name, String url) {
        this.rootId = rootId;
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public MenuNav(long rootId, String name, String url) {
        this.rootId = rootId;
        this.name = name;
        this.url = url;
    }

    public MenuNav(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MenuNav(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
