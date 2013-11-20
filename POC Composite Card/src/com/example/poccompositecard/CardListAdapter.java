package com.example.poccompositecard;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CardListAdapter extends BaseAdapter {

	List<CardDisplay> cardDisplays;

	public CardListAdapter(List<CardDisplay> cardDisplays) {
		super();
		this.cardDisplays = cardDisplays;
	}

	@Override
	public int getCount() {
		return cardDisplays.size();
	}

	@Override
	public Object getItem(int position) {
		return cardDisplays.get(position);
	}

	@Override
	public int getItemViewType(int position) {
		return cardDisplays.get(position).getType();
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return cardDisplays.get(position).getView(convertView, parent);
	}

}
