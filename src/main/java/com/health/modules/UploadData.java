package com.health.modules;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;

import com.health.util.Constants;

public class UploadData {

	public static int i=1;
	public static  String TIMESTAMP_FROM="2015-06-01T02:53:00.928Z";
	public static  String TIMESTAMP_TO="2015-06-01T02:54:15.928Z";
	
	/*public static void main (String args[]){
		
		
		
		try{
			String [] source={"ether_stat_s","cpu_stat_s"};
			System.out.println(source);
			Client client = new TransportClient()
	        .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
			SearchResponse response = client.prepareSearch("cn_*")
			           .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
			            .setFrom(0).setSize(10).setExplain(true)
			            .setPostFilter(FilterBuilders.rangeFilter("@timestamp").from(TIMESTAMP_FROM).to(TIMESTAMP_TO))
			            .setFetchSource(source, null)
                        .execute()
			           .actionGet();
		
			
			int i=0;
			if(response.getHits().getHits().length>0){
				for(SearchHit searchData:response.getHits().getHits()){
					JSONObject values=new JSONObject(searchData.getSource());
					System.out.println(values);
					i++;
				}
				System.out.println("Number of records fetched is "+i);
				}
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
	}*/
	
	
	public void uploadData(HashMap data){
		try{
			
			Client client=new TransportClient().addTransportAddress(new InetSocketTransportAddress(Constants.ELASTIC_IP,9300));
			IndexResponse response=client.prepareIndex("cn_health_monitoring","cn_health_monitoring",String.valueOf(i)).setSource(data).execute().actionGet();
		    System.out.println(response.getIndex() + "  "+response.getId());
			
			/*BulkRequestBuilder bulk=client.prepareBulk();
			
			bulk.add(client.prepareIndex("cn_health_monitoring","cn_health_monitoring",String.valueOf(i)).setSource(data));
			BulkResponse bulkResponse = bulk.execute().actionGet();*/
			
			i++;
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void uploadDataViaHTTP(HashMap data){
		
		JSONObject dataToInsert=new JSONObject(data);
		try {
			URL url=new URL("http://"+Constants.ELASTIC_IP+":9200/cn_health_monitoring/cn_health_monitoring");
			HttpURLConnection httpcon=(HttpURLConnection)url.openConnection();
			httpcon.setDoOutput(true);
			httpcon.setRequestMethod("POST");
			
			OutputStreamWriter output=new OutputStreamWriter(httpcon.getOutputStream());
			System.out.println(dataToInsert);
			output.write(dataToInsert.toString());
			
			
			output.close();
			httpcon.connect();
			int code=httpcon.getResponseCode();
			System.out.println(code);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
