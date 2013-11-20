package com.example.poccompositecard;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CardDisplay {
	Context context;
	LayoutInflater inflater;
	List<ViewContainer> viewContainers;
	Card card;

	public CardDisplay(Context context, LayoutInflater inflater, Card card) {
		super();
		this.context = context;
		this.inflater = inflater;
		this.card = card;
		viewContainers = new ArrayList<ViewContainer>();
	}
	
	public void addViewContainer(ViewContainer container) {
		viewContainers.add(container);
	}

	public View getView(View convertView, ViewGroup parent) {
		ViewGroup view = (ViewGroup) convertView;
		if (view == null) {
			view = (ViewGroup) inflater.inflate(R.layout.carditem_blankcard,
					parent, false);
			MagicViewHolder holder = new MagicViewHolder();
			View child;
			for (ViewContainer container : viewContainers) {
				child = container.createView(inflater, parent);
				view.addView(child);
				holder.addHolderView(child);
			}
			view.setTag(holder);
		} else {
			MagicViewHolder holder = (MagicViewHolder) view.getTag();
			ViewContainer container;
			for (int i = 0; i < viewContainers.size(); i++) {
				container = viewContainers.get(i);
				container.reinitView(holder.getHolderView(i));
			}
		}
		return view;
	}

	public int getType() {
		return card.getType();
	}

	private static class MagicViewHolder {
		private List<View> views;

		public MagicViewHolder() {
			views = new ArrayList<View>();
		}

		public View getHolderView(int position) {
			return views.get(position);
		}

		public void addHolderView(View view) {
			views.add(view);
		}

	}
}
