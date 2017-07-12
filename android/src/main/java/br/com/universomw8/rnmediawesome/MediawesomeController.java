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

    private static ArrayList<String> getFilePathsArrayList(ReadableArray filePaths) {
        ArrayList<String> paths = new ArrayList<>();

        for (int i = 0; i < filePaths.size(); i++) {
            paths.add(filePaths.getString(i));
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

    @Override
    public String getName() {
        return "MediawesomeController";
    }

    @ReactMethod
    public void createPlaylist(ReadableArray filePaths, Promise promise) {
        promise.resolve(player.createPlaylist(null));
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
    public void startPlaylist(String id, final Promise promise) {
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                promise.resolve(player.startPlaylist(null));
            }
        });
    }

    @ReactMethod
    public void stopPlaylist(String id, Promise promise) {
    }

    public void init(MediawesomePlayerView videoView, final Promise promise) {
        MediawesomeController.this.player = new Player(this.getCurrentActivity(), videoView);
        if (promise != null)
            promise.resolve(true);
    }
}
