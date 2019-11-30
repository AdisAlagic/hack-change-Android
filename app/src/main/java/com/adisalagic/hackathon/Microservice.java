package com.adisalagic.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Microservice extends Fragment {
	/**
	 * State - Ready to use
	 */
	static final int COO = 412;
	/**
	 * State - Currently as idea
	 */
	static final int IDE = 142;

	/**
	 * State - In development
	 */
	static final int IDV = 705;

	/**
	 * State - Currently in testing
	 */
	static final int DEB = 854;

	int              type;
	String           name;
	String           description;
	ConstraintLayout layout;
	TextView         mName, mDescription;
	ImageView circle;


	@Target(value = ElementType.PARAMETER)
	@IntDef({Microservice.COO, Microservice.IDE, Microservice.DEB, Microservice.IDV})
	@Retention(value = RetentionPolicy.CLASS)
	@interface SericeType {
	}

	Microservice(String name, String description, @SericeType int type) {
		this.name = name;
		this.description = description;
		this.type = type;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_microservice, container, false);
		mName = rootView.findViewById(R.id.name);
		mDescription = rootView.findViewById(R.id.description);
		mName.setText(name);
		circle = rootView.findViewById(R.id.circle);
		mDescription.setText(description);
		layout = rootView.findViewById(R.id.layout);
		rootView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(v.getContext(), ViewMicroservice.class);
				startActivity(intent);
			}
		});
		switch (type) {
			case Microservice.IDE:
				circle.setColorFilter(Color.CYAN);
				break;
			case Microservice.COO:
				circle.setColorFilter(Color.WHITE);
				break;
			case Microservice.DEB:
				circle.setColorFilter(Color.YELLOW);
				break;
			case Microservice.IDV:
				circle.setColorFilter(Color.GREEN);
				break;
		}
		return rootView;
	}
}