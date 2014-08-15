package com.tektak.iloop.elasticapi;

import com.tektak.iloop.elasticapi.index.Document;
import com.tektak.iloop.elasticapi.index.Index;
import com.tektak.iloop.elasticapi.index.IndexResponse;
import com.tektak.iloop.elasticapi.index.SearchResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by julina on 8/15/14.
 */
public class ElasticProcessorContext {

    public IndexResponse index( String name, String tableName) throws
            ElasticException.ClusterException, ElasticException.DocumentException {

        Document document = new Document();
        document.setIndex(tableName.toLowerCase());
        document.setIndexType("1");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("count", 1);
        document.setIndexDocument(jsonObject.toString());
        return Index.DoIndex(document);
    }

    public IndexResponse update( int name, String tableName,  int count )
            throws ElasticException.ClusterException, ElasticException.DocumentException {

        Document document = new Document();
        document.setIndex(tableName.toLowerCase());
        document.setIndexType(tableName.toLowerCase());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("count", count);
        document.setIndexDocument(jsonObject.toString());
        return Index.DoIndex(document);
    }

    public <E> ArrayList<E> search(String toSearch, String tableName,
                                   ElasticProcessor elasticProcessor, E model)
            throws ElasticException.ClusterException, ElasticException.DocumentException {

        Document document = new Document();
        document.setIndex(tableName.toLowerCase());
        ArrayList<SearchResponse> searchResponses = Index.Search(document, toSearch, "name");
        int size = searchResponses.size();
        ArrayList<E> results = new ArrayList<>();
        for(int i =0 ; i< size ; i++){
            SearchResponse searchResponse = searchResponses.get(i);
            Map<String, Object> object =  searchResponse.getSource();
            JSONObject jsonObject = new JSONObject(object);
            results.add(elasticProcessor.process(jsonObject, model));
        }
        return results;
    }
}
