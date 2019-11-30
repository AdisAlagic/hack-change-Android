package com.adisalagic.hackathon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Microservice extends Fragment {

	String name;
	String description;

	TextView mName, mDescription;

	Microservice(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_microservice, container, false);
		mName = rootView.findViewById(R.id.name);
		mDescription = rootView.findViewById(R.id.description);
		mName.setText(name);
		mDescription.setText(description);
		return rootView;
	}
}