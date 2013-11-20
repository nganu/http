package com.example.poccompositecard;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<CardDisplay> cards = new ArrayList<CardDisplay>();
		LayoutInflater inflater = LayoutInflater.from(this);
		CardComposer composer = CardComposer.getComposer();
		String content = "1:test@1:test lain";
		Card card = new Card(0, content);
		cards.add(composer.compose(card, inflater, this));
		content = "1:testa@1:testa lain@1:testoteron";
		card = new Card(2, content);
		cards.add(composer.compose(card, inflater, this));
		CardListAdapter adapter = new CardListAdapter(cards);
		ListView list = (ListView) findViewById(R.id.cardlist);
		list.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
