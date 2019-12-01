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





	public int getAmountOfServices() {
		readyForUse = false;
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection;
				try {
					Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
					URL reqUrl = new URL(API_URL + "api/get-service-count/");
					connection = (HttpURLConnection) reqUrl.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(100);
					connection.connect();
					BufferedReader br   = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					StringBuilder  sb   = new StringBuilder();
					String         line = "";
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					br.close();
					size = Integer.parseInt(sb.toString());
					readyForUse = true;
					connection.disconnect();
				} catch (Exception e) {
					Log.e("NETWORK", e.toString());
				}
			}
		});
		return size;
	}


	public ResultSet getResult(final int id) {
		final ResultSet[] result = new ResultSet[1];
		readyForUse = false;
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				ResultSet resultSet = null;

				readyForUse = true;
				result[0] = resultSet;
			}
		});
		return result[0];
	}

	public ArrayList<ResultSet> getResults(final int size) {
		Log.i("ASYNC", "After");
		final ArrayList<ResultSet> resultSets = new ArrayList<>();
		Log.i("ASYNC", "Here!");
		AsyncTask.execute(new Runnable() {
			@Override
			public void run() {
				Gson              gson;
				HttpURLConnection connection;
				try {
					Log.i("ASYNC", "SIZE " + size);
					for (int i = 0; i < size; i++) {
						URL reqUrl = new URL(API_URL + "api/get-service/" + i);
						connection = (HttpURLConnection) reqUrl.openConnection();
						connection.setRequestMethod("GET");
						connection.connect();
						BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						StringBuilder  sb = new StringBuilder();
						String         line;
						if (br.ready()) {
							while ((line = br.readLine()) != null) {
								sb.append(line);
							}
						}
						br.close();
						Log.i("ASYNC", sb.toString());
						gson = new Gson();
						resultSets.add(gson.fromJson(sb.toString(), ResultSet.class));
						connection.disconnect();
					}
				} catch (Exception e) {
					Log.e("ASYNC", e.toString());
				}
			}
		});

		return resultSets;
	}
}
