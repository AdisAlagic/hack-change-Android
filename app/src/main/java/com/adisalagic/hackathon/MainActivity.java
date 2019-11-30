package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

	LinearLayout linearLayout;

	private void refreshServices(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		for (int i = 0; i < fragmentManager.getFragments().size(); i++){
			fragmentTransaction.remove(fragmentManager.getFragments().get(i));
		}

		for (int i = 0; i < 10; i++){
			fragmentTransaction.add(linearLayout.getId(), new Microservice("Name of microservice", "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et"));
		}

		fragmentTransaction.commit();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getSupportActionBar();
		assert actionBar != null;
		actionBar.hide();
		linearLayout = findViewById(R.id.lay);
		refreshServices();

	}

}
