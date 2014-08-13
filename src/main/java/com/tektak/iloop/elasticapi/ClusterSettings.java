package com.tektak.iloop.elasticapi;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public class ClusterSettings {
    private Boolean clusterSniff = true;
    private String clusterName = "elasticsearch";

    public Boolean getClusterSniff() {
        return clusterSniff;
    }

    public void setClusterSniff(Boolean clusterSniff) {
        this.clusterSniff = clusterSniff;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}
