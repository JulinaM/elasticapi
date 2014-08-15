package com.tektak.iloop.elasticapi;

import org.json.JSONObject;

/**
 * Created by julina on 8/15/14.
 */
public interface ElasticProcessor {
    public <E> E process(JSONObject jsonObject ,E model);
}
