package br.com.universomw8.rnmediawesome;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;

import java.util.ArrayList;

import br.com.universomw8.rnmediawesome.player.Player;

public class MediawesomeController extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;
    private Player player;

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
    public void stopPlaylist(String id, Promise promise) {
    }

    void init(MediawesomePlayerView surfaceView, final Promise promise) {
        MediawesomeController.this.player = new Player(this.getCurrentActivity(), surfaceView);
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
