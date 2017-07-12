package br.com.universomw8.rnmediawesome;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class MediawesomeViewManager extends SimpleViewManager<MediawesomePlayerView> {

    public static final String REACT_CLASS = "RCTMediawesomePlayer";
    private ReactApplicationContext context;
    private RNMediawesomePackage mediawesomePackage;

    public MediawesomeViewManager(ReactApplicationContext context, RNMediawesomePackage mediawesomePackage) {
        this.context = context;
        this.mediawesomePackage = mediawesomePackage;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MediawesomePlayerView createViewInstance(ThemedReactContext themedReactContext) {
        MediawesomePlayerView view = new MediawesomePlayerView(themedReactContext);
        themedReactContext.getNativeModule(MediawesomeController.class).init(view, null);
        return view;
    }
}