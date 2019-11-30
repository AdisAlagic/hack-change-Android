package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewMicroservice extends AppCompatActivity {
	String name, description, status, author, tags;
	TextView mName, mDescription, mStatus, mAuthor, mTags;
	ImageView mImage;

	ViewMicroservice(String name, String description, String status, String author, String tags){
		this.name = name;
		this.description = description;
		this.status = status;
		this.author = author;
		this.tags = tags;
	}

	public void getDataFromIntent(){
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		mName.setText(name);
		description = intent.getStringExtra("description");
		mDescription.setText(description);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_microservice);
		ActionBar actionBar = getSupportActionBar();
		assert actionBar != null;
		actionBar.hide();
		mName = findViewById(R.id.name);
		mDescription = findViewById(R.id.description);
		mStatus = findViewById(R.id.status);
		mAuthor = findViewById(R.id.author);
		mTags = findViewById(R.id.tags);
		mImage = findViewById(R.id.image);
		getDataFromIntent();
	}
}
