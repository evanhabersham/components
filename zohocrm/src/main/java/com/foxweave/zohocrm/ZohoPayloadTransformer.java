package com.foxweave.zohocrm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import com.foxweave.pipeline.transform.PipelinePayloadTransformer;

public class ZohoPayloadTransformer implements PipelinePayloadTransformer {

	@Override
	public JSONObject transform(JSONObject payload) throws Exception {
		JSONObject transformedPayload = new JSONObject();
		System.out.println(Thread.currentThread().getStackTrace());
		
			
			JSONArray FL = payload.getJSONArray("FL");
				int numFields = FL.length();
				//Start traversing the 'FL' array
				for (int i = 0; i < numFields; i++) {
					JSONObject fieldObject = FL.getJSONObject(i);
					String fieldName = fieldObject.getString("val");
					Object fieldValue = fieldObject.get("content");

					fieldName = fieldName.replace(" ", "_");
			
					transformedPayload.put(fieldName, fieldValue);
				   
				}
		
			

	
		return transformedPayload;
	}
}