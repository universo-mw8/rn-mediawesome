package br.com.universomw8.rnmediawesome;

import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.facebook.react.uimanager.ThemedReactContext;

@SuppressLint("ViewConstructor")
public class MediawesomePlayerView extends VideoView {

    public MediawesomePlayerView(ThemedReactContext context) {
        super(context);
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
    }
}
