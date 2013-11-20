package com.example.poccompositecard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ViewContainer {
	public View createView(LayoutInflater inflater, ViewGroup parent);
	public void reinitView(View view);
}
