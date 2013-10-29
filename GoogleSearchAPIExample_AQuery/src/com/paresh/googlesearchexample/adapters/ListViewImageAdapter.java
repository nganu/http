package com.paresh.googlesearchexample.adapters;

/**
 * @Author Paresh N. Mayani
 * @Web http://www.technotalkative.com
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.paresh.googlesearchexample.R;
import com.paresh.googlesearchexample.utils.ImageLoader;

public class ListViewImageAdapter extends BaseAdapter {

	private Activity activity;
	public ArrayList<Object> listImages;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;
	private long startTime;

	public ListViewImageAdapter(Activity a, ArrayList<Object> listImages) {
		activity = a;
		this.listImages = listImages;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
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
		// holder.imgViewImage.setTag(imageBean.getThumbUrl());
		// imageLoader.DisplayImage(imageBean.getThumbUrl(), activity,
		// holder.imgViewImage);
		//
		// holder.txtViewTitle.setText(Html.fromHtml(imageBean.getTitle()));
		AQuery aq = new AQuery(convertView);
		// aq.id(holder.imgViewImage).image(imageBean.getThumbUrl());
		aq.id(holder.txtViewTitle).text(Html.fromHtml(imageBean.getTitle()));

		aq.id(holder.imgViewImage).image(imageBean.getThumbUrl(), true, true,
				0, 0, new BitmapAjaxCallback() {
					long startTime = System.currentTimeMillis();

					@Override
					public void callback(String url, ImageView iv, Bitmap bm,
							AjaxStatus status) {

						iv.setImageBitmap(bm);
						Log.e("TAG", "TAG image "+
								+ (System.currentTimeMillis() - startTime));
					}
				});
		return vi;
	}
}