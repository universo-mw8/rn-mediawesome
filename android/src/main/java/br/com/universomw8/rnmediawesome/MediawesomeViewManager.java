package br.com.universomw8.rnmediawesome;

import android.widget.RelativeLayout;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import static android.widget.RelativeLayout.TRUE;

class MediawesomeViewManager extends SimpleViewManager<MediawesomePlayerView> {

    private static final String REACT_CLASS = "RCTMediawesomePlayer";
    private static final int UNSET_SIZE = -9999;
    private ReactApplicationContext context;
    private RNMediawesomePackage mediawesomePackage;
    private int height = UNSET_SIZE;
    private int width = UNSET_SIZE;
    private MediawesomePlayerView view;

    MediawesomeViewManager(ReactApplicationContext context, RNMediawesomePackage mediawesomePackage) {
        this.context = context;
        this.mediawesomePackage = mediawesomePackage;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MediawesomePlayerView createViewInstance(ThemedReactContext themedReactContext) {
        view = new MediawesomePlayerView(themedReactContext);
        themedReactContext.getNativeModule(MediawesomeController.class).init(view);
        return view;
    }

    @Override
    public void onDropViewInstance(MediawesomePlayerView view) {
        super.onDropViewInstance(view);
        context.getNativeModule(MediawesomeController.class).stopPlayback(null);
    }

    @ReactProp(name = "alpha", defaultFloat = 0f)
    public void setAlpha(MediawesomePlayerView view, float alpha) {
        view.setAlpha(alpha);
    }

    @ReactProp(name = "width", defaultInt = UNSET_SIZE)
    public void setWidth(MediawesomePlayerView view, int width) {
        this.width = width;
        maybeSetWidthHeight();
    }

    @ReactProp(name = "height", defaultInt = UNSET_SIZE)
    public void setHeight(MediawesomePlayerView view, int height) {
        this.height = height;
        maybeSetWidthHeight();
    }

    private void maybeSetWidthHeight() {
        if (width == UNSET_SIZE || height == UNSET_SIZE)
            return;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(CENTER_IN_PARENT, TRUE);

        view.getSurfaceView().setLayoutParams(params);
    }
}
