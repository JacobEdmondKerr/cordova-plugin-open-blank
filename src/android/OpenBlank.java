package org.apache.cordova.openblank;

import org.apache.cordova.*;
import org.apache.cordova.PluginResult.Status;

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

public class OpenBlank extends CordovaPlugin {
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }
	
    @Override
    public boolean shouldAllowRequest(String url){
        return true;
    }
	
    @Override
    public boolean onOverrideUrlLoading(String url) {
    	Log.d("OpenBlank", "onOverrideUrlLoading called with URL " + url);
   		if(url.indexOf("google") > -1 || url.indexOf(".com") > -1 || url.indexOf(".net") > -1 || url.indexOf(".org") > -1) {
			try {
			    webView.getView().post(new Runnable() {
				@Override
				public void run() {
					rootView.loadUrl("javascript:cordova.InAppBrowser.open('"+url+"');");
				}
			    });
			    return true;
			} catch (android.content.ActivityNotFoundException e) {
			    Log.d("OpenBlank", "OpenBlank: Error loading url "+url+":"+ e.toString());
			    return false;
			}
   		}
   		return false;
    }
}
