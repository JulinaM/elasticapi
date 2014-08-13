package com.tektak.iloop.elasticapi;

/**
 * Created by Dipak Malla
 * Date: 8/13/14
 */
public interface ElasticException {
    public class ClusterException extends  Exception{
        public ClusterException(String msg){
            super(msg);
        }
    }
    public class DocumentException extends  Exception{
        public DocumentException(String msg){
            super(msg);
        }
    }
}
