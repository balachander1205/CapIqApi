package com.api.capIq.controller;

import javax.xml.ws.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.capIq.model.CapIqBuyerModel;
import com.api.capIq.model.CapIqRequestModel;
import com.google.gson.Gson;

@RestController
public class CapIqRestContoller {

	@PostMapping
	public String getCapiqResponse(@RequestBody CapIqBuyerModel buyerModel) {

		JSONObject capIqRequest = new JSONObject();
		JSONArray capIqInputRequestArr = new JSONArray();
		try {			
			CapIqRequestModel capIqReqModel = new CapIqRequestModel();
			capIqReqModel.setFunction(buyerModel.getBuyerProgramCode());
			capIqReqModel.setIdentifier(buyerModel.getIngDetailId());
			capIqReqModel.setMnemonic(buyerModel.getSupplierCompanyName());
			
			String capReq = new Gson().toJson(capIqReqModel);
			JSONObject jsonCapReq = new JSONObject(capReq);
			
			 capIqInputRequestArr.put(jsonCapReq);
			 capIqRequest.put("inputRequests", capIqInputRequestArr);
		}catch (Exception e) {
			System.out.println("Xception:="+e);
		}
		return capIqRequest.toString();
	}
}
