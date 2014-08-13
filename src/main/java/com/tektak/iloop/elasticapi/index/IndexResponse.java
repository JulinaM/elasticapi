package com.tektak.iloop.elasticapi.index;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public class IndexResponse {
    private String name;
    private  String type;
    private  String id;
    private long version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
