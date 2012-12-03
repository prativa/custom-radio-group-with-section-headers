package com.example.radiogroupwithsectionheaders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class RadioGrpHeaderUI {
	private RadioGroup radioGroup;
	LinearLayout.LayoutParams layoutParamsBasic = getLayoutParams();
	Context context;
	int count = 0;
	String selectedCategory;
	ScrollView categoryListingLayout;
	LinkedHashMap<String, ArrayList<String>> newsCategoryListing;

	ArrayList<String> newsArrayList;

	public RadioGrpHeaderUI(Context context, ScrollView categoryListingLayout) {
		this.context = context;
		this.categoryListingLayout = categoryListingLayout;
		initializeHashMap();
		createTheList();
	}

	public void initializeHashMap() {
		newsCategoryListing = new LinkedHashMap<String, ArrayList<String>>();
		ArrayList<String> movies = new ArrayList<String>() {
			{
				add("Boxoffice");
				add("Bright Lights");
				add("Cineaste");

				add("Close Up");
				add("eFilmCritic.com");
				add("Empire");
			}
		};
		ArrayList<String> sports = new ArrayList<String>() {
			{
				add("The Sun");
				add("Sky Sports");
				add("Daily Star");
			}
		};
		ArrayList<String> technology = new ArrayList<String>() {
			{
				add("TechCrunch");
				add("HackerNews");
				add("LinkedIn");
				add("YahooNews");
				add("NewYorkTimes");
				add("Interanational Herald Times");
			}
		};

		newsCategoryListing.put("MOVIES", movies);
		newsCategoryListing.put("SPORTS", sports);
		newsCategoryListing.put("TECH NEWS", technology);
	}

	public void createTheList() {
		radioGroup = new RadioGroup(context);
		radioGroup.setLayoutParams(getLayoutParams());
		radioGroup.setOrientation(LinearLayout.VERTICAL);

		if (newsCategoryListing.size() > 0) {
			Iterator it = newsCategoryListing.entrySet().iterator();
			newsArrayList = new ArrayList<String>();
			count = count + 1;
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				count = count + 1;
				// create field header and its run time UI
				String fieldHeader = (String) pairs.getKey();
				addFieldHeaderTextView(fieldHeader);

				newsArrayList = (ArrayList<String>) pairs.getValue();
				int length = newsArrayList.size();
				int parentId = count++;
				final RadioButton[] radiobutton = new RadioButton[length];

				for (int i = 0; i < length; i++) {

					final String fieldOption = newsArrayList.get(i);
					radiobutton[i] = new RadioButton(context);
					radiobutton[i].setText(fieldOption);

					radiobutton[i].setPadding(radiobutton[i].getBackground()
							.getIntrinsicWidth(), 0, 20, 0);
					// /radioGroup.addView(radiobutton[i]);
					radioGroup.addView(radiobutton[i]);

					if (radioGroup.getCheckedRadioButtonId() == -1) {
						radioGroup
								.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
									@Override
									public void onCheckedChanged(
											RadioGroup group, int checkedId) {

										RadioButton checkedRadioButton = (RadioButton) categoryListingLayout
												.findViewById(checkedId);
										selectedCategory = checkedRadioButton
												.getText().toString();
										((MainActivity) context).finish();

									}
								});
					}

				}
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		categoryListingLayout.addView(radioGroup);
	}

	public void addFieldHeaderTextView(String fieldHeader) {
		TextView tv = new TextView(context);
		tv.setText(fieldHeader);
		layoutParamsBasic = getLayoutParams();
		tv.setLayoutParams(layoutParamsBasic);
		radioGroup.addView(tv);
	}

	public LinearLayout.LayoutParams getLayoutParams() {
		return new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
	}

}
