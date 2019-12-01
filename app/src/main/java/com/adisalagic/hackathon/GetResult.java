package com.adisalagic.hackathon;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.adisalagic.hackathon.Api.API_URL;

public class GetResult extends AsyncTask<Integer, Void, Api.ResultSet> {
	private Api.ResultSet resultSet = null;

	public Api.ResultSet getResultSet() {
		return resultSet;
	}

	@Override
	protected Api.ResultSet doInBackground(Integer... strings) {
		try {
			URL               url        = new URL(API_URL + "api/get-service/" + strings[0]);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(100);
			connection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder  sb = new StringBuilder();
			String         line;
			while ((line = br.readLine()) != null) {
				Log.i("ASYNC_F", "Iteration " + line);
				sb.append(line);
			}
			resultSet = new Gson().fromJson(sb.toString(), Api.ResultSet.class);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}
}
