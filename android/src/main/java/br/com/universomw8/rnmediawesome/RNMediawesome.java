package br.com.universomw8.rnmediawesome;

import android.app.Activity;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReadableArray;

public class RNMediawesome extends ReactContextBaseJavaModule {
  private ReactApplicationContext context;

  public RNMediawesome(ReactApplicationContext reactContext) {
    super(reactContext);
    this.context = reactContext;
  }

  @Override
  public String getName() {
    return "RNMediawesome";
  }

  @ReactMethod
  public void createPlaylist(ReadableArray filePaths, Promise promise) {
    promise.resolve(null);
  }

  @ReactMethod
  public void getPlaylist(String id, Promise promise) {
  }

  @ReactMethod
  public void destroyPlaylist(String id, Promise promise) {
  }

  @ReactMethod
  public void getAllPlaylists(Promise promise) {
  }

  @ReactMethod
  public void startPlaylists(String id, Promise promise) {
  }

  @ReactMethod
  public void stopPlaylist(String id, Promise promise) {
  }
}
