package br.com.universomw8.rnmediawesome;

import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;

import java.util.ArrayList;

import br.com.universomw8.rnmediawesome.player.Player;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import static android.widget.RelativeLayout.TRUE;

public class MediawesomeController extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;
    private Player player;
    private MediawesomePlayerView view;

    public MediawesomeController(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    @Override
    public String getName() {
        return "MediawesomeController";
    }

    @ReactMethod
    public void createPlaylist(ReadableArray filePaths, Promise promise) {
        promise.resolve(player.createPlaylist(getFilePathsArrayList(filePaths)));
    }

    @ReactMethod
    public void destroyPlaylist(String uid, Promise promise) {
        promise.resolve(player.destroyPlaylist(uid));
    }

    @ReactMethod
    public void getPlaylist(String id, Promise promise) {
        throw new RuntimeException("Not implemented");
    }

    @ReactMethod
    public void getAllPlaylists(Promise promise) {
        throw new RuntimeException("Not implemented");
    }

    @ReactMethod
    public void startPlaylist(final String uid, final Promise promise) {
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                promise.resolve(player.startPlaylist(uid));
            }
        });
    }

    @ReactMethod
    public void hideScreen(final Promise promise) {
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                promise.resolve(player.hideScreen());
            }
        });
    }

    @ReactMethod
    public void showScreen(final Promise promise) {
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                promise.resolve(player.showScreen());
            }
        });
    }

    @ReactMethod
    public void stopPlayback(Promise promise) {
        promise.resolve(player.stopPlayback());
    }

    @ReactMethod
    public void isPlaying(Promise promise) {
        promise.resolve(player.isPlaying());
    }

    @ReactMethod
    public void setScreenSize(int width, int height, Promise promise) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
        params.addRule(CENTER_IN_PARENT, TRUE);

        view.getSurfaceView().setLayoutParams(params);

        if (promise != null) {
            promise.resolve(player.isPlaying());
        }
    }

    void init(MediawesomePlayerView view, final Promise promise) {
        this.view = view;
        MediawesomeController.this.player = new Player(this.getCurrentActivity(), view);
        if (promise != null)
            promise.resolve(true);
    }

    private static ArrayList<String> getFilePathsArrayList(ReadableArray filePaths) {
        ArrayList<String> paths = new ArrayList<>();

        for (int i = 0; i < filePaths.size(); i++) {
            String s = filePaths.getString(i);
            if (s != null && s.length() > 0)
                paths.add(s);
        }

        return paths;
    }

    private static WritableArray getPlaylistWritableArray(ArrayList<String> playlist) {
        WritableArray arr = Arguments.createArray();
        for (String s : playlist) {
            arr.pushString(s);
        }
        return arr;
    }
}
