package com.example.tani;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sentaku extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sentaku);

		Button Ibtn = (Button)findViewById(R.id.button1);
	        Ibtn.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Sub 画面を起動
	                Intent intent = new Intent();
	                intent.setClassName("com.example.tani", "com.example.tani.Repo");
	                startActivity(intent);
	            }
	        });

	        Button Ibtn2 = (Button)findViewById(R.id.button3);
	        Ibtn2.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Sub 画面を起動
	                Intent intent = new Intent();
	                intent.setClassName("com.example.tani", "com.example.tani.Check");
	                startActivity(intent);
	            }
	        });
	        Button Ibtn3 = (Button)findViewById(R.id.button4);
	        Ibtn3.setOnClickListener(new OnClickListener() {
	            public void onClick(View v) {
	                // Sub 画面を起動
	                Intent intent = new Intent();
	                intent.setClassName("com.example.tani", "com.example.tani.Permission");
	                startActivity(intent);
	            }
	        });
	    }



	}




