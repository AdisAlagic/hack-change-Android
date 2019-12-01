package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	public static int size = 0;
	LinearLayout         linearLayout;
	ScrollView           scrollView;
	FloatingActionButton fab;

	private void check() {
		GetAmountService task = new GetAmountService();
		task.execute();
		if (task.res > 0){
			refreshServices(task);
		}else {
			check();
		}
	}

	private void refreshServices(GetAmountService task) {

		task.execute();
		FragmentManager     fragmentManager     = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		for (int i = 0; i < fragmentManager.getFragments().size(); i++) {
			fragmentTransaction.remove(fragmentManager.getFragments().get(i));
		}
		Api api = new Api();
		Log.i("ASYNC", "The final size is " + size);
		ArrayList<Api.ResultSet> resultSets = new ArrayList<>();
		for (int i = 2; i < size + 2; i++) {
			GetResult result = new GetResult();
			result.execute(i);
			resultSets.add(result.getResultSet());
		}

		for (int i = 0; i < resultSets.size(); i++) {
			Api.ResultSet resultSet = resultSets.get(i);
			resultSet.description = resultSet.description.split("\n")[1];
			Microservice microservice = new Microservice(resultSet.name, resultSet.description, resultSet.getStatusAsInt());
			fragmentTransaction.add(linearLayout.getId(), microservice);
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
		check();

	}

}
