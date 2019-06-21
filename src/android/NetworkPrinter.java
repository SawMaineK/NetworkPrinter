package com.smk.networkprinter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.smk.networkprinter.invoice.InvoiceActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkPrinter extends CordovaPlugin {

    private static final int RESULT_PRINT = 10;
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("print")) {
            PluginResult pr = new PluginResult(PluginResult.Status.NO_RESULT);
            pr.setKeepCallback(true);
            callbackContext.sendPluginResult(pr);
            this.callbackContext = callbackContext;
            print(args);
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULT_PRINT) {
            if (resultCode == Activity.RESULT_OK) {
                this.callbackContext.success("1");
                this.callbackContext = null;
            } else {
                this.callbackContext.error("0");
                this.callbackContext = null;
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);

    }

    private void print(JSONArray args) {
        try {
            JSONObject params = args.getJSONObject(0);
            Bundle bundle = new Bundle();
            bundle.putString("ipAddress", params.getString("ipAddress"));
            bundle.putInt("port",params.getInt("port"));
            bundle.putInt("paperWidth", params.getInt("paperWidth")); //80mm
            bundle.putString("invoice", params.getJSONObject("invoice").toString());
            cordova.getActivity().startActivityForResult(new Intent(cordova.getActivity(), InvoiceActivity.class).putExtras(bundle), RESULT_PRINT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
