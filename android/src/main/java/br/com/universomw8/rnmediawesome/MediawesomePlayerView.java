package br.com.universomw8.rnmediawesome;

import android.annotation.SuppressLint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import com.facebook.react.uimanager.ThemedReactContext;

@SuppressLint("ViewConstructor")
public class MediawesomePlayerView extends RelativeLayout {

    private final SurfaceHolder surfaceHolder;
    private final SurfaceView surfaceView;

    public MediawesomePlayerView(ThemedReactContext context) {
        super(context);
        surfaceView = new SurfaceView(context);
        surfaceView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        surfaceHolder = surfaceView.getHolder();

        this.addView(surfaceView);
    }

    public SurfaceHolder getHolder() {
        return surfaceHolder;
    }
    public SurfaceView getSurfaceView() {
        return surfaceView;
    }
}
