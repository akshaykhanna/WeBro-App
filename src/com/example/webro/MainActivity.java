package com.example.webro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	Toast t;
	WebView ourwv;
	EditText url;
	Button back,forward,refresh,tab,go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        ourwv=(WebView) findViewById(R.id.wv1);
        ourwv.loadUrl("http://www.google.co.in");
      //Brings keyboard in inner textbox of website
        ourwv.requestFocus(View.FOCUS_DOWN);
        ourwv.setWebViewClient(new myWebViewClient());
        ourwv.getSettings().setLoadWithOverviewMode(true);
        ourwv.getSettings().setJavaScriptEnabled(true);
        ourwv.getSettings().setUseWideViewPort(true);
        ourwv.getSettings().setBuiltInZoomControls(true);
        
        
       

   	 
      
        
        intialise();
      
      
       url.setOnClickListener(this);
        go.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        tab.setOnClickListener(this);
    }

    private void intialise() 
    {
		// TODO Auto-generated method stub
		url=(EditText) findViewById(R.id.etUrl);
		go=(Button) findViewById(R.id.bGo);
		back=(Button) findViewById(R.id.bBackward);
		forward=(Button) findViewById(R.id.bForward);
		refresh=(Button) findViewById(R.id.bRefresh);
		tab=(Button) findViewById(R.id.bNew);
		
	}

	

	@Override
	public void onClick(View ar)
	{
		// TODO Auto-generated method stub
		switch(ar.getId())
		{
		case R.id.bGo:
			String website=url.getText().toString();
		
			 if(website.startsWith("http://www."))
			 
			{
				ourwv.loadUrl(website);
				ourwv.requestFocus(View.FOCUS_DOWN);
			}
			else if(website.startsWith("www."))
			{
				ourwv.loadUrl("http://"+website);
				ourwv.requestFocus(View.FOCUS_DOWN);
			}
			else if(website.contains(".com")|| website.contains(".in")|| website.contains(".*"))
			{
				ourwv.loadUrl("http://www."+website);
				ourwv.requestFocus(View.FOCUS_DOWN);
			}
			else
			{
				try
				{
				String search="https://www.google.co.in/search?q="+url.getText().toString().trim();
				ourwv.loadUrl(search);
				}
				catch(Exception e)
				{
					Toast.makeText(this, "Failed to search: "+e.toString(), Toast.LENGTH_LONG).show();
				}
				/*
				//search Google
				  HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://google.co.in");
			     try {
			       List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			       nameValuePairs.add(new BasicNameValuePair("search", website));
			       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			       httpclient.execute(httppost);
					 } catch (ClientProtocolException e) {
			         // TODO Auto-generated catch block
			     } catch (IOException e) {
			         // TODO Auto-generated catch block
			     }*/

				
			}
			 
			//hiding keyboard
			InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(url.getWindowToken(),0);
             
			
			break;
		case R.id.bBackward:
			if(ourwv.canGoBack())
				ourwv.goBack();
			break;
		case R.id.bForward:
			ourwv.goForward();
			break;
	case R.id.bRefresh:
		ourwv.reload();
		break;
	case R.id.bNew:
		try
		{
			ourwv.loadUrl("http://www.google.co.in");
			//ourwv.loadUrl("https://www.google.co.in/search?q=highway");
		}
		catch(Exception e)
		{
			Toast.makeText(this, "Failed to load home page: "+e.toString(), Toast.LENGTH_LONG).show();
		}
		break;
	case R.id.etUrl:
		url.setText("");
			break;
		}
		
	}
	
	/*Settings menu*/
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu)

	{
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater capactiveButtonMenu=getMenuInflater();
	    capactiveButtonMenu.inflate(R.menu.activity_main, menu);
	    return true;
	    
	}
	
	

}

