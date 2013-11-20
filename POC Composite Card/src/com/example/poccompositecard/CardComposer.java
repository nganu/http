package com.example.poccompositecard;

import android.content.Context;
import android.view.LayoutInflater;

public class CardComposer {
	private static CardComposer composer = null;

	public static CardComposer getComposer() {
		if (composer == null) {
			composer = new CardComposer();
		}
		return composer;
	}

	private CardComposer() {

	}

	public CardDisplay compose(Card card, LayoutInflater inflater,
			Context context) {
		CardDisplay display = new CardDisplay(context, inflater, card);
		String[] contents = card.getContentString().split("@");
		for (int i = 0; i < contents.length; i++) {
			display.addViewContainer(parseContent(contents[i]));
		}
		return display;
	}

	private ViewContainer parseContent(String content) {
		String[] data = content.split(":");
		ViewContainer viewContainer = null;
		switch (Integer.valueOf(data[0])) {
		case 1:
			viewContainer = new TextViewContainer(data[1]);
			break;
		}
		return viewContainer;
	}
}
