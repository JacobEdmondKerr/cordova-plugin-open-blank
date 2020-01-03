package org.apache.cordova.openblank;

import org.apache.cordova.*;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.provider.Browser;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;


@SuppressLint("SetJavaScriptEnabled")
public class OpenBlank extends CordovaPlugin {
    @Override
    public boolean onOverrideUrlLoading(String url) {
    	Log.d("OpenBlank", "onOverrideUrlLoading called with URL " + url);
   		if(url.indexOf("google") > -1 || url.indexOf(".com") > -1 || url.indexOf(".net") > -1 || url.indexOf(".org") > -1) {

	        try {
	            Intent intent = null;
	            intent = new Intent(Intent.ACTION_VIEW);
	            // Omitting the MIME type for file: URLs causes "No Activity found to handle Intent".
	            // Adding the MIME type to http: URLs causes them to not be handled by the downloader.
	            Uri uri = Uri.parse(url);
	            if ("file".equals(uri.getScheme())) {
	                //intent.setDataAndType(uri, webView.getResourceApi().getMimeType(uri));
	            } else {
	                //intent.setData(uri);
	            }
	            //intent.putExtra(Browser.EXTRA_APPLICATION_ID, cordova.getActivity().getPackageName());
	            //this.cordova.getActivity().startActivity(intent);
		    webView.loadUrl("javascript:cordova.InAppBrowser.open("+url+")");
	            return true;
	        } catch (android.content.ActivityNotFoundException e) {
	            Log.d("OpenBlank", "OpenBlank: Error loading url "+url+":"+ e.toString());
	            return false;
	        }
   		}

   		return false;
    }
}
