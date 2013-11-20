package com.example.poccompositecard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TextViewContainer implements ViewContainer {
	private String strLabel;

	public TextViewContainer(String strLabel) {
		super();
		this.strLabel = strLabel;
	}

	@Override
	public View createView(LayoutInflater inflater, ViewGroup parent) {
		View view = inflater.inflate(R.layout.viewcontainer_textlabel, parent,
				false);
		reinitView(view);
		return view;
	}

	@Override
	public void reinitView(View view) {
		TextView textView = (TextView) view;
		textView.setText(strLabel);
	}

}
