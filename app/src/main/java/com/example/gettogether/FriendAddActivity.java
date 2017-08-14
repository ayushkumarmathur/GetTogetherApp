package com.example.gettogether;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FriendAddActivity extends Activity {
	EditText mobileno,message;
	Button sendsms;
	String name, email, phone;
	EditText n, e, p;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);
	    mobileno=(EditText)findViewById(R.id.txtPhone);
		message=(EditText)findViewById(R.id.editText2);
		sendsms=(Button)findViewById(R.id.button1);
		sendsms.setOnClickListener(new View.OnClickListener() {
		//	@Override
			public void onClick(View v) {
				String p=mobileno.getText().toString();
				String msg=message.getText().toString();

				//Getting intent and PendingIntent instance
				Intent intent=new Intent(getApplicationContext(),FriendAddActivity.class);
				PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

				//Get the SmsManager instance and call the sendTextMessage method to send message
				SmsManager sms=SmsManager.getDefault();
				sms.sendTextMessage(p, null, msg, pi,null);

				Toast.makeText(getApplicationContext(), "Message Sent successfully!",
						Toast.LENGTH_LONG).show();
			}
		});

        try {
            String destPath = "/data/data/" + getPackageName() +
                "/databases";
            File f = new File(destPath);
            if (!f.exists()) {            	
            	f.mkdirs();
                f.createNewFile();
            	
            	//---copy the db from the assets folder into 
            	// the databases folder---
                CopyDB(getBaseContext().getAssets().open("gtapp"),
                    new FileOutputStream(destPath + "/GTApp"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        n = (EditText)findViewById(R.id.txtName);   
		e = (EditText)findViewById(R.id.txtEmail);
		mobileno = (EditText)findViewById(R.id.txtPhone);
		
	}
	
	public void CopyDB(InputStream inputStream, 
		    OutputStream outputStream) throws IOException {
		        //---copy 1K bytes at a time---
		        byte[] buffer = new byte[1024];
		        int length;
		        while ((length = inputStream.read(buffer)) > 0) {
		            outputStream.write(buffer, 0, length);
		        }
		        inputStream.close();
		        outputStream.close();
		    }

	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        super.onCreateOptionsMenu(menu);
	        
	        MenuItem mnu1 = menu.add(0, 1, 1, "Home");
	        {
	            mnu1.setIcon(R.drawable.home_icon);
	            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	        }
	        MenuItem mnu2 = menu.add(0, 2, 2, "Exit");
	        {
	            mnu2.setIcon(R.drawable.exit_icon);
	            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	        }
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    	switch (item.getItemId()) {
	    	 
	    	 case 1:
	    		 	Intent intent1 = new Intent(FriendAddActivity.this, HomeActivity.class);
					startActivity(intent1);
		            return true;
	        case 2:
	        	Intent intent = new Intent(Intent.ACTION_MAIN);
	        	intent.addCategory(Intent.CATEGORY_HOME);
	        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        	startActivity(intent);
	            return true;
	        
	        
	    }
	    	return false;
	    }
	    	
	
	/** Called when the Add Friend button is clicked. */
	public void onClick(View view)
	{
		DBAdapter db = new DBAdapter(this);

		  
			name = n.getText().toString();
			email = e.getText().toString(); 
			phone = mobileno.getText().toString();
        //---add a friend to the database---
        db.open();
        long id = db.insertFriend(name, email,phone);
        Log.d("From App","insert="+id);
        db.close();
        
        
		Toast.makeText(getBaseContext(), "Friend Added", Toast.LENGTH_SHORT).show();
		Intent i = new Intent("com.example.gettogether.FriendsActivity");
		startActivity(i);
		
	}
	
	
		   
	

}


