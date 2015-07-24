package com.health.modules;

import java.util.HashMap;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.json.JSONObject;
import static org.elasticsearch.common.xcontent.XContentFactory.*;
import com.health.util.Constants;

public class UploadData {

	public static int i=1;
	
	public void uploadData(HashMap data){
		try{
			
			Client client=new TransportClient().addTransportAddress(new InetSocketTransportAddress(Constants.ELASTIC_IP,9300));
			BulkRequestBuilder bulk=client.prepareBulk();
			bulk.add(client.prepareIndex("cn_health_monitoring","cn_health_monitoring",String.valueOf(i)).setSource(data));
			BulkResponse bulkResponse = bulk.execute().actionGet();
			
			i++;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
