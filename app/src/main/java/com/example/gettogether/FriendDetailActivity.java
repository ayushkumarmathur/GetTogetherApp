package com.example.gettogether;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FriendDetailActivity extends Activity {

	TextView n, e, mobileno;
	String name, email;
	int phone;
	DBAdapter db;
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend_detail);

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
        
        n = (TextView)findViewById(R.id.txtName);   
		e = (TextView)findViewById(R.id.txtEmail);
		mobileno = (TextView)findViewById(R.id.txtPhone);
		db = new DBAdapter(this);
		db.open();
		Intent intent = this.getIntent();
        int id = intent.getIntExtra("id", 0);
        
        Cursor c = db.getFriend(id);
        if (c.moveToFirst())
        {
            do {
            	
            	name = c.getString(1);
            	email = c.getString(2);
                phone = c.getInt(3);
               
            } while (c.moveToNext());
        }
           
        db.close();
        
        n.setText(name);
        e.setText(email);
		mobileno.setText(new Integer(phone).toString());
        
        
		
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
	
	

}


