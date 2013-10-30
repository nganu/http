package com.paresh.googlesearchexamplevolley.adapters;

/**
 * @Author Paresh N. Mayani
 * @Web http://www.technotalkative.com
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.paresh.googlesearchexamplevolley.R;

public class ListViewImageAdapter extends BaseAdapter {

	private Activity activity;
	public ArrayList<Object> listImages;
	private static LayoutInflater inflater = null;
	// public ImageLoader imageLoader;
	private ImageLoader imageLoader;

	// private static RequestQueue mRequestQueue;

	public ListViewImageAdapter(Activity a, ArrayList<Object> listImages,
			RequestQueue requestQueue) {
		activity = a;
		this.listImages = listImages;
		// this.mRequestQueue = requestQueue;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(requestQueue,
				new ImageLoader.ImageCache() {
					private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(
							10);

					@Override
					public void putBitmap(String url, Bitmap btimap) {
						mCache.put(url, btimap);
					}

					@Override
					public Bitmap getBitmap(String url) {
						return mCache.get(url);
					}
				});
		// imageLoader=new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return listImages.size();
	}

	public Object getItem(int position) {
		// return position;
		return listImages.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		public ImageView imgViewImage;
		public TextView txtViewTitle;

	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.listview_row, null);
			holder = new ViewHolder();

			holder.imgViewImage = (ImageView) vi.findViewById(R.id.imageView01);
			holder.txtViewTitle = (TextView) vi.findViewById(R.id.textView1);

			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		GoogleImageBean imageBean = (GoogleImageBean) listImages.get(position);
		holder.imgViewImage.setTag(imageBean.getThumbUrl());
		imageLoader.get(imageBean.getThumbUrl(), new DefaultImageListener(holder.imgViewImage));
		// imageLoader.DisplayImage(imageBean.getThumbUrl(), activity,
		// holder.imgViewImage);

		holder.txtViewTitle.setText(Html.fromHtml(imageBean.getTitle()));
		return vi;
	}

	private class DefaultImageListener implements ImageListener {

		ImageView view;
		long startTime = System.currentTimeMillis();

		public DefaultImageListener(ImageView view) {
			super();
			this.view = view;
		}

		@Override
		public void onErrorResponse(VolleyError error) {
			System.out.println("Error image response");
		}

		@Override
		public void onResponse(ImageContainer response, boolean isImmediate) {
			if (response.getBitmap() != null) {
				Log.d("VOLLEY_TEST",  "image response has arrived in " + (System.currentTimeMillis() - startTime) + "(ms)");
				view.setImageBitmap(response.getBitmap());
			}
		}

	}
}