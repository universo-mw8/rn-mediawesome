package br.com.universomw8.rnmediawesome;

import android.annotation.SuppressLint;
import android.view.SurfaceView;

import com.facebook.react.uimanager.ThemedReactContext;

@SuppressLint("ViewConstructor")
public class MediawesomePlayerView extends SurfaceView {

    public MediawesomePlayerView(ThemedReactContext context) {
        super(context);
        this.setBackgroundColor(getResources().getColor(android.R.color.transparent));
    }
}
