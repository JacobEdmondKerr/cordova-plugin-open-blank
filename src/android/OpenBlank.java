package org.apache.cordova.openblank;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;
import android.util.Log;
import org.apache.cordova.CordovaPlugin;


@SuppressLint("SetJavaScriptEnabled")
public boolean onOverrideUrlLoading(String url) {
    	// Log.d("OpenBlank", "onOverrideUrlLoading called with URL " + url);
   		//if(url.indexOf("utm_") > -1 || url.indexOf("adurl") > -1) {

	        try {
	            Intent intent = null;
	            intent = new Intent(Intent.ACTION_VIEW);
	            // Omitting the MIME type for file: URLs causes "No Activity found to handle Intent".
	            // Adding the MIME type to http: URLs causes them to not be handled by the downloader.
	            Uri uri = Uri.parse(url);
	            if ("file".equals(uri.getScheme())) {
	                intent.setDataAndType(uri, webView.getResourceApi().getMimeType(uri));
	            } else {
	                intent.setData(uri);
	            }
	            intent.putExtra(Browser.EXTRA_APPLICATION_ID, cordova.getActivity().getPackageName());
	            this.cordova.getActivity().startActivity(intent);
	            return true;
	        } catch (android.content.ActivityNotFoundException e) {
	            // Log.d("OpenBlank", "OpenBlank: Error loading url "+url+":"+ e.toString());
	            return false;
	        }
   		//}

   		return false;
    }
