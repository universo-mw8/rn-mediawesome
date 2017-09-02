package br.com.universomw8.rnmediawesome;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RNMediawesomePackage implements ReactPackage {
    private MediawesomeController controller;
    private MediawesomeViewManager viewManager;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        controller = new MediawesomeController(reactContext);
        return Collections.<NativeModule>singletonList(controller);
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        viewManager = new MediawesomeViewManager(reactContext, this);
        return Collections.<ViewManager>singletonList(viewManager);
    }
}
