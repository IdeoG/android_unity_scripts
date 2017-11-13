using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.VR;
using System;

public class UpdatePosition : MonoBehaviour {

	private AndroidJavaObject currentActivity;
	private AndroidJavaClass UnityPlayer;
	private AndroidJavaObject context;
    private string toastString;

    void Start () {
		if(Application.platform == RuntimePlatform.Android){
			UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");
			currentActivity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity"); 
			context = currentActivity.Call<AndroidJavaObject>("getApplicationContext");
		}
    }

    /**
     * Show Toast
     */
    public void showToastOnUiThread(string toastString) {
        this.toastString = toastString;
        currentActivity.Call("runOnUiThread", new AndroidJavaRunnable(showToast));
    }

    private void showToast() {
        AndroidJavaClass Toast = new AndroidJavaClass("android.widget.Toast");
        AndroidJavaObject javaString = new AndroidJavaObject("java.lang.String", toastString);
        AndroidJavaObject toast = Toast.CallStatic<AndroidJavaObject>("makeText", context, javaString, Toast.GetStatic<int>("LENGTH_SHORT"));
        toast.Call("show");
    }
}