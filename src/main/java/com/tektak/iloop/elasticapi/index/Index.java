package com.tektak.iloop.elasticapi.index;

import com.tektak.iloop.elasticapi.Cluster;
import com.tektak.iloop.elasticapi.ElasticException;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public class Index {
    public static com.tektak.iloop.elasticapi.index.IndexResponse DoIndex(Document document) throws
            ElasticException.DocumentException, ElasticException.ClusterException {
        if(document == null)
            throw new ElasticException.DocumentException("Document instance is null");
        Client client = Cluster.getClient();
        if(client == null)
            throw new ElasticException.ClusterException("Client not initialized.");
        IndexRequestBuilder indexRequestBuilder;
        if(document.getIndexId() == null)
            indexRequestBuilder = client.prepareIndex(document.getIndex(),document.getIndexType());
        else
            indexRequestBuilder = client.prepareIndex(document.getIndex(),
                    document.getIndexType(),
                    document.getIndexId()
            );
        IndexResponse indexResponse = indexRequestBuilder.setSource(document.getIndexDocument()).execute().actionGet();
        com.tektak.iloop.elasticapi.index.IndexResponse result = new com.tektak.iloop.elasticapi.index.IndexResponse();
        result.setId(indexResponse.getId());
        result.setName(indexResponse.getIndex());
        result.setType(indexResponse.getType());
        result.setVersion(indexResponse.getVersion());
        return result;
    }
    public static ArrayList<com.tektak.iloop.elasticapi.index.SearchResponse> Search(Document document, String text, String ... fields) throws ElasticException.ClusterException, ElasticException.DocumentException {
        if(document == null)
            throw new ElasticException.DocumentException("Document instance is null");
        Client client = Cluster.getClient();
        if(client == null)
            throw new ElasticException.ClusterException("Client not initialized.");
        SearchResponse searchResponse = client.prepareSearch(document.getIndex()).
                setSearchType(SearchType.QUERY_AND_FETCH).
                setQuery(QueryBuilders.multiMatchQuery(text, fields)).
                setExplain(true).execute().actionGet();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        ArrayList<com.tektak.iloop.elasticapi.index.SearchResponse> searchResponses = new ArrayList<>(searchHits.length);
        for(SearchHit searchHit : searchHits){
            com.tektak.iloop.elasticapi.index.SearchResponse response = new com.tektak.iloop.elasticapi.index.SearchResponse();
            response.setIndex(searchHit.getIndex());
            response.setIndexType(searchHit.getType());
            response.setSource(searchHit.getSource());
            searchResponses.add(response);
        }
        return searchResponses;

    }
    public static com.tektak.iloop.elasticapi.index.IndexResponse Delete(Document document) throws ElasticException.ClusterException,
            ElasticException.DocumentException {
        if(document == null)
            throw new ElasticException.DocumentException("Document instance is null");
        Client client = Cluster.getClient();
        if(client == null)
            throw new ElasticException.ClusterException("Client not initialized.");
        DeleteResponse deleteResponse = client.prepareDelete(document.getIndex(),
                document.getIndexType(),
                document.getIndexId()).execute().actionGet();
        com.tektak.iloop.elasticapi.index.IndexResponse result = new com.tektak.iloop.elasticapi.index.IndexResponse();
        result.setId(deleteResponse.getId());
        result.setName(deleteResponse.getIndex());
        result.setType(deleteResponse.getType());
        result.setVersion(deleteResponse.getVersion());
        return result;


    }
}
