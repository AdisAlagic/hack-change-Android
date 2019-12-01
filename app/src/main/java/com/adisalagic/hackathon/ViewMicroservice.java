package com.adisalagic.hackathon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.widget.ImageView;
import android.widget.TextView;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ViewMicroservice extends AppCompatActivity {
	String name, description, status, author, tags;
	TextView mName, mDescription, mStatus, mAuthor, mTags;
	ImageView mImage;

//	ViewMicroservice(String name, String description, String status, String author, String tags){
//		this.name = name;
//		this.description = description;
//		this.status = status;
//		this.author = author;
//		this.tags = tags;
//	}

	public void getDataFromIntent() {
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		int type = intent.getIntExtra("status", Microservice.ERR);
		switch (type) {
			case Microservice.COO:
				status = "Статус: Готово";
				break;
			case Microservice.DEB:
				status = "Статус: Тестирование";
				break;
			case Microservice.ERR:
				status = "Статус: ошибка при получении статуса";
				break;
			case Microservice.IDE:
				status = "Статус: На стадии Идеи";
				break;
			case Microservice.IDV:
				status = "Статус: В Разработке";
				break;
		}
		mName.setText(name);
		description = intent.getStringExtra("description");
		List<Extension> extensions = Collections.singletonList(TablesExtension.create());
		Parser parser = Parser
				.builder()
				.extensions(extensions)
				.build();
		Node         desc     = parser.parse(description);
		HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
		description = renderer.render(desc);
		Spanned spanned = Html.fromHtml(description);
		mDescription.setText(spanned);
		Api       api = new Api();

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
