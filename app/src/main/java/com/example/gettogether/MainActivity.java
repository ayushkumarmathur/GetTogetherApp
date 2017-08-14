package com.example.gettogether;







import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

String msg = "GetTogetherActivityLC";
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(msg, "OnCreate called");
	}
	
	/** Called when the Launch button is clicked. */
	public void onClick(View view)
	{
		Intent i = new Intent("com.example.gettogether.HomeActivity");
		startActivity(i);
		
	}

	/** Called when the activity is about to become visible. */
	protected void onStart()
	{
		super.onStart();
		Log.d(msg, "OnStart called");
		
	}
	
	/** Called when the activity has become visible. */
	protected void onResume()
	{
		super.onResume();
		Log.d(msg, "OnResume called");
		
	}	
	
	/** Called when the another activity is getting focus. */
	protected void onPause()
	{
		super.onPause();
		Log.d(msg, "OnPause called");
		
	}
	
	/** Called when the activity is no longer visible. */
	protected void onStop()
	{
		super.onStop();
		Log.d(msg, "OnStop called");
		
	}
	
	/** Called just before the activity is destroyed. */
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(msg, "OnDestroy called");
		
	}
	
	
    
}
