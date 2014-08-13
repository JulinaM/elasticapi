package com.tektak.iloop.elasticapi;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;


import java.util.ArrayList;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public class Cluster {
    private static Client client;
    public static Client initElasticCluster(ArrayList<TransportAddress> addressArrayList, ClusterSettings clusterSettings)
            throws ElasticException.ClusterException {
        if(addressArrayList == null || addressArrayList.size() == 0)
            throw new ElasticException.ClusterException("Address not found.");
        if(clusterSettings == null)
            clusterSettings = new ClusterSettings();
        if(client == null){
            Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterSettings.getClusterName()).
                    put("client.transport.sniff", clusterSettings.getClusterSniff()).build();
            TransportClient transportClient = new TransportClient(settings);
            for(TransportAddress transportAddress : addressArrayList){
                transportClient.addTransportAddress(new InetSocketTransportAddress(transportAddress.getAddress(),
                        transportAddress.getPort()));
            }
            client = transportClient;
        }
        return client;
    }
    public static Client getClient(){
        return client;
    }
}
