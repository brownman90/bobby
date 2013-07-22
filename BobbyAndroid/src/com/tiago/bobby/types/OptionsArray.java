/**
 * 
 */
package com.tiago.bobby.types;

import com.tiago.bobby.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author tfr_souza
 *
 */
public class OptionsArray extends BaseAdapter {

	private static final String[] mOptions = { "Add Person", "Remove Person",  "Add Friend", "List Friends", "Recommendations" };
	private Context mContext;
	
	public OptionsArray(Context context) {
		mContext = context;
	}
	
	@Override
	public int getCount() {
		return mOptions.length;
	}

	@Override
	public Object getItem(int position) {
		return mOptions[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder elements = null;
		
		if (convertView == null) {
			elements = new ViewHolder();
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.main_list_view, null);
			
			elements.txtOption = (TextView) convertView.findViewById(R.id.txtOption);
			elements.imgOption = (ImageView) convertView.findViewById(R.id.imgOption);
			
			convertView.setTag(elements);
		} else {
			elements = (ViewHolder) convertView.getTag();
		}
		
		elements.txtOption.setText(mOptions[position]);
		elements.imgOption.setImageResource(R.drawable.arrow_right);
		
		return convertView;
	}
	
	static class ViewHolder {
		
		TextView txtOption;
		ImageView imgOption;
	}

}
