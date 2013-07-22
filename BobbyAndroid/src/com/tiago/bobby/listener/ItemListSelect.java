/**
 * 
 */
package com.tiago.bobby.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tiago.bobby.AddFriendActivity;
import com.tiago.bobby.AddPersonActivity;
import com.tiago.bobby.ListFriendsActivity;
import com.tiago.bobby.RecommendationsActivity;
import com.tiago.bobby.RemovePersonActivity;

/**
 * @author tfr_souza
 *
 */
public class ItemListSelect implements OnItemClickListener {

	public void onItemClick(AdapterView<?> adapter, View currentView, int position, long id) {
		Intent opcaoEscolhida = null;
		
		if (position == 0) {
			opcaoEscolhida = new Intent(currentView.getContext(), AddPersonActivity.class);
		} else if (position == 1) {
			opcaoEscolhida = new Intent(currentView.getContext(), RemovePersonActivity.class);
		} else if (position == 2) {
			opcaoEscolhida = new Intent(currentView.getContext(), AddFriendActivity.class);
		} else if (position == 3) {
			opcaoEscolhida = new Intent(currentView.getContext(), ListFriendsActivity.class);
		} else if (position == 4) {
			opcaoEscolhida = new Intent(currentView.getContext(), RecommendationsActivity.class);
		}
		
		currentView.getContext().startActivity(opcaoEscolhida);
	}

}
