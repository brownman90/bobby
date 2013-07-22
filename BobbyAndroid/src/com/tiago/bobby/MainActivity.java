package com.tiago.bobby;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.tiago.bobby.listener.ItemListSelect;
import com.tiago.bobby.types.OptionsArray;

public class MainActivity extends Activity {
	
	private ListView listOptions;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (!checkInternetConnection(this)) noConnection(this);
        
        listOptions = (ListView) findViewById(R.id.listViewOptions);
        listOptions.setAdapter(new OptionsArray(this));
        listOptions.setOnItemClickListener(new ItemListSelect());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void noConnection(final Context context) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(context);

	    builder.setMessage("Este plicativo necessita de conexão com a internet. Ative a rede de dados ou Wi-Fi.");

	    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	            ;
	        }
	    });

	    AlertDialog alerta = builder.create();

	    alerta.show();
	}
    
}
