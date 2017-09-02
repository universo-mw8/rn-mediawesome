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

class MediawesomeController extends ReactContextBaseJavaModule {
    private ReactApplicationContext context;
    private Player player;
    private MediawesomePlayerView view;

    MediawesomeController(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
    }

    void init(MediawesomePlayerView surfaceView) {
        this.view = surfaceView;
        this.player = new Player(this.getCurrentActivity(), surfaceView);
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
    public void getPlaylist(String uid, Promise promise) {
        if (promise != null)
            promise.resolve(getPlaylistWritableArray(player.getPlaylist(uid)));
    }

    @ReactMethod
    public void getAllPlaylists(Promise promise) {
        if (promise != null)
            promise.resolve(getPlaylistWritableArray(player.getAllPlaylists()));
    }

    @ReactMethod
    public void startPlaylist(final String uid, final Promise promise) {
        checkViewInitialized();
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                if (promise != null)
                    promise.resolve(player.startPlaylist(uid));
            }
        });
    }

    @ReactMethod
    public void hideScreen(final Promise promise) {
        checkViewInitialized();
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                if (promise != null)
                    promise.resolve(player.hideScreen());
            }
        });
    }

    @ReactMethod
    public void showScreen(final Promise promise) {
        checkViewInitialized();
        context.runOnUiQueueThread(new Runnable() {
            @Override
            public void run() {
                if (promise != null)
                    promise.resolve(player.showScreen());
            }
        });
    }

    @ReactMethod
    public void stopPlayback(Promise promise) {
        checkViewInitialized();
        if (promise != null)
            promise.resolve(player.stopPlayback());
    }

    @ReactMethod
    public void isPlaying(Promise promise) {
        checkViewInitialized();
        if (promise != null)
            promise.resolve(player.isPlaying());
    }

    @ReactMethod
    public void getCurrentPlaylist(Promise promise) {
        checkViewInitialized();
        if (promise != null)
            promise.resolve(player.getCurrentPlaylist());
    }

    private void checkViewInitialized() {
        if (this.view == null)
            throw new IllegalStateException("The Mediawesome View is not initialized. Make sure it's being rendered!");
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

    private static WritableArray getPlaylistWritableArray(Iterable<String> playlist) {
        WritableArray arr = Arguments.createArray();
        for (String s : playlist)
            arr.pushString(s);

        return arr;
    }
}
