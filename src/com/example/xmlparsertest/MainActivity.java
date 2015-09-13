package com.example.xmlparsertest;

import java.io.InputStream;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		parseXMLByDOM();//使用DOM的方式来解析XML
	}
	public void parseXMLByDOM() {
		InputStream xml = this.getClass().getClassLoader()
				.getResourceAsStream("singers.xml");
		List<Singer> singers;
		try {
			singers = DomParserHelper.getSingers(xml);
			for (Singer singer : singers) {
				System.out.println( "----------->" + singer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
