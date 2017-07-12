package br.com.universomw8.rnmediawesome;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNMediawesomePackage implements ReactPackage {
  private MediawesomeController controller;
  private MediawesomeViewManager viewManager;

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    controller = new MediawesomeController(reactContext);
    return Arrays.<NativeModule>asList(controller);
  }

  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    viewManager = new MediawesomeViewManager(reactContext, this);
    return Arrays.<ViewManager>asList(viewManager);
  }
}