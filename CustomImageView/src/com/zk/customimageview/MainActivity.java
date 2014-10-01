package com.zk.customimageview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private CustomImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mImageView = (CustomImageView) findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
		case R.id.action_type_oval:
			mImageView.setType(CustomImageView.TYPE_OVAL);
			return true;
			
		case R.id.action_type_circle:
			mImageView.setType(CustomImageView.TYPE_CIRCLE);
			return true;
			
		case R.id.action_type_round_rect:
			mImageView.setType(CustomImageView.TYPE_ROUND_RECT);
			return true;
			
		case R.id.action_type_square:
			mImageView.setType(CustomImageView.TYPE_SQUARE);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
