/**
 * 
 */
package com.tiago.bobby.types;

import java.util.List;

import com.tiago.bobby.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author tfr_souza
 *
 */
public class ListFriendsArray extends BaseAdapter {
	
	private Context mContext;
	private List<Person> mListFriends;
	
	public ListFriendsArray(Context context, List<Person> listFriends) {
		mContext = context;
		mListFriends = listFriends;
	}

	@Override
	public int getCount() {
		return mListFriends.size();
	}

	@Override
	public Object getItem(int position) {
		return mListFriends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder elements = null;
		
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			elements = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.friends_list_view, null);
			elements.personName = (TextView) convertView.findViewById(R.id.txtPersonName);
			elements.personId = (TextView) convertView.findViewById(R.id.txtPersonID);
			
			convertView.setTag(elements);
		} else {
			elements = (ViewHolder) convertView.getTag();
		}
		
		elements.personName.setText(mListFriends.get(position).getNome());
		elements.personId.setText("ID: " + mListFriends.get(position).getId());
		
		return convertView;
	}
	
	static class ViewHolder {
		
		TextView personName;
		TextView personId;
	}

}
