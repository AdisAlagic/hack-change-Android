package com.adisalagic.hackathon;


import android.os.AsyncTask;
import android.renderscript.RenderScript;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Api {
	public static final String API_URL = "http://10.5.12.151:64233/";
	int     size;
	boolean readyForUse = false;

	public class ResultSet {
		@SerializedName(value = "name")
		String name;
		@SerializedName(value = "about")
		String description;
		@SerializedName(value = "status")
		String status;
		@SerializedName(value = "author")
		String author;
		@SerializedName(value = "tags")
		String tags;
		//		@SerializedName(value = "image")
//		String image;


		public ResultSet(String name, String description, String status, String author, String tags) {
			this.name = name;
			this.description = description;
			this.status = status;
			this.author = author;
			this.tags = tags;
		}

		public int getStatusAsInt() {
			switch (status) {
				case "COO":
					return Microservice.COO;
				case "IDV":
					return Microservice.IDV;
				case "IDE":
					return Microservice.IDE;
				case "DEB":
					return Microservice.DEB;
				default:
					return Microservice.ERR;
			}
		}
	}

	private reOiLApi reOiLAPI;
	public interface reOiLApi{
		@GET(value = "/api/get-service/{id}")
		Call<List<Request>> getData(@Path(value = "id") int id);
	}

	public interface reOiLApiCount{
		@GET(value = "/api/get-service-count/")
		Call<Integer> getCount();
	}

	private reOiLApiCount reOiLAPICount;
	public void GetCount(){
		Retrofit retrofit = new Retrofit
				.Builder()
				.baseUrl("http://10.5.12.151:64233/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		reOiLAPICount = retrofit.create(reOiLApiCount.class);
	}

	public reOiLApiCount getReOiLAPICount(){
		return reOiLAPICount;
	}

	public void GetResultByID(){

		Retrofit retrofit = new Retrofit
				.Builder()
				.baseUrl("http://10.5.12.151:64233/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		reOiLAPI = retrofit.create(reOiLApi.class);
	}

	public reOiLApi getAPI(){
		return reOiLAPI;
	}
}
