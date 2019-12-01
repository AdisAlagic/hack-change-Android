package com.adisalagic.hackathon;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.adisalagic.hackathon.Api.API_URL;

public class GetAmountService extends AsyncTask<String, Void, Integer> {
	public int res = 0;
	boolean isReady = false;
	@Override
	protected void onPostExecute(Integer integer) {
		res = integer;
		MainActivity.size = res;
		isReady = true;
	}

	@Override
	protected Integer doInBackground(String... strings) {
		int cake = -1;

		try {
			isReady = false;
			res = 0;
			URL               reqUrl     = new URL(API_URL + "api/get-service-count/");
			HttpURLConnection connection = (HttpURLConnection) reqUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(10000);
			connection.connect();
			BufferedReader br   = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder  sb   = new StringBuilder();
				String         line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line).append('\n');
			}
			br.close();

			cake = Integer.parseInt(sb.toString().replace("\n", ""));
			connection.disconnect();
		} catch (Exception e) {
			Log.i("ASYNC", e.toString());
		}
		return cake;
	}
}