package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

	LinearLayout         linearLayout;
	ScrollView           scrollView;
	FloatingActionButton fab;

	private void refreshServices() {
		FragmentManager     fragmentManager     = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
			fragmentTransaction.remove(fragmentManager.getFragments().get(i));
		}

		for (int i = 0; i < 10; i++) {
			fragmentTransaction.add(linearLayout.getId(), new Microservice("Name of microservice",
					"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
							"Aenean massa. Cum sociis natoque penatibus et", Microservice.DEB));
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
		scrollView = findViewById(R.id.scrollView);
		fab = findViewById(R.id.floatingActionButton2);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
				View                view    = getLayoutInflater().inflate(R.layout.dialog_filters, null, false);
				builder.setView(view);

				EditText          search = view.findViewById(R.id.search);
				Spinner           group  = view.findViewById(R.id.group);
				Button            submit = view.findViewById(R.id.submit);
				final AlertDialog dialog = builder.create();
				submit.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});
		scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
			@Override
			public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				int scroll = scrollView.getScrollBarSize();
				if (scrollY == scroll) {
					Toast.makeText(v.getContext(), "yay", Toast.LENGTH_SHORT).show();
				}
			}
		});
		refreshServices();

	}

}
