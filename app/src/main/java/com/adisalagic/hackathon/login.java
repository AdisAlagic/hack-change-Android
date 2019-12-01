package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

	EditText login, password;
	TextView loginWithout;
	Button submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		login = findViewById(R.id.login);
		password = findViewById(R.id.password);
		loginWithout = findViewById(R.id.login_without);
		submit = findViewById(R.id.submit);
		ActionBar actionBar = getSupportActionBar();
		assert actionBar != null;
		actionBar.hide();
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(v.getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
			}
		});

		loginWithout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(v.getContext(), MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
