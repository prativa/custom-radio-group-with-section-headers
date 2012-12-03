package com.example.radiogroupwithsectionheaders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView newsCategoryListView;

	LinkedHashMap<String, ArrayList<String>> newsCategoryListing;
	ScrollView categoryListingLayout;
	String selectedCategory;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);// for the custom
															// title bar
		setContentView(R.layout.config);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);// for
																				// the
																				// custom
																				// title
																				// bar
		initListViews();
	}

	public void initListViews() {

		categoryListingLayout = (ScrollView) findViewById(R.id.categoryListingLayout);
		categoryListingLayout.setVerticalScrollBarEnabled(true);
		createTheList();
	}

	public void createTheList() {
		RadioGrpHeaderUI radioGrpHeaderUI = new RadioGrpHeaderUI(
				MainActivity.this, categoryListingLayout);
		// while initializing the class, just pass the context and the
		// scrollview where you
		// want to integrate your radio group with section header
	}

}