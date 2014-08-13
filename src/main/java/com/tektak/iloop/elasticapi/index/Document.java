package com.tektak.iloop.elasticapi.index;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public class Document {
    private String index;
    private String indexType;
    private  String indexId;
    private String indexDocument;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIndexDocument() {
        return indexDocument;
    }

    public void setIndexDocument(String indexDocument) {
        this.indexDocument = indexDocument;
    }
}
