package br.com.universomw8.rnmediawesome.player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Random;

import br.com.universomw8.rnmediawesome.MediawesomePlayerView;

public class Player {
    private final Random randomInstance;
    private Activity currentActivity;
    private HashMap<String, ArrayDeque<File>> playlists;
    private String currentPlaylistId;
    private Deque<File> currentPlaylist;
    private MediaPlayer nextMediaPlayer;
    private MediaPlayer currentMediaPlayer;
    private MediawesomePlayerView surfaceView;
    private static final String TAG = "Mediawesome Player";

    public Player(Activity currentActivity, MediawesomePlayerView surfaceView) {
        this.surfaceView = surfaceView;
        this.playlists = new HashMap<>();
        this.currentActivity = currentActivity;
        randomInstance = new Random(currentActivity.hashCode() * surfaceView.hashCode());
    }

    public String createPlaylist(ArrayList<String> filePaths) {
        String uid = getRandomHex(8);

        ArrayDeque<File> videos = new ArrayDeque<>();

        for (String path : filePaths) {
            videos.push(new File(path));
        }

        playlists.put(uid, videos);

        return uid;
    }

    public boolean destroyPlaylist(String uid) {
        return playlists.remove(uid) != null;
    }

    public ArrayDeque<File> getPlaylist(String id) {
        return playlists.get(id);
    }

    public boolean startPlaylist(String id) {
        if (id == null || id.length() == 0)
            return false;

        stopPlayback();

        currentPlaylistId = id;
        currentPlaylist = playlists.get(id);

        startPlayback();

        return true;
    }

    private void startPlayback() {
        currentMediaPlayer = new MediaPlayer();
        currentMediaPlayer.setDisplay(surfaceView.getHolder());

        try {
            currentMediaPlayer.setDataSource(currentActivity, Uri.fromFile(currentPlaylist.peek()));
            currentMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentMediaPlayer.start();
        setUpNextMediaPlayer();
    }

    private void setUpNextMediaPlayer() {
        currentMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    createNextMediaPlayer();
                }

                Log.d(TAG, "Info: what " + String.valueOf(what) + " extra " + String.valueOf(extra));
                return false;
            }
        });

        currentMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.d(TAG, "Completion: " + mp.toString());

                currentMediaPlayer.release();

                // This shouldn't happen
                if (nextMediaPlayer == null) {
                    Log.e(TAG, "nextMediaPlayer is null! Trying to recreate it");
                    createNextMediaPlayer();

                    if (nextMediaPlayer == null) {
                        Log.e(TAG, "nextMediaPlayer is still null! Something's broke yall");
                        throw new NullPointerException("nextMediaPlayer is null even after trying to recreate it");
                    }
                }

                try {
                    nextMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                currentMediaPlayer = nextMediaPlayer;
                nextMediaPlayer = null;

                currentMediaPlayer.start();
                setUpNextMediaPlayer();
            }
        });
    }

    private void createNextMediaPlayer() {
        currentPlaylist.addLast(currentPlaylist.pop());

        nextMediaPlayer = new MediaPlayer();
        nextMediaPlayer.setDisplay(surfaceView.getHolder());

        try {
            nextMediaPlayer.setDataSource(currentActivity, Uri.fromFile(currentPlaylist.peek()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomHex(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(Integer.toHexString(randomInstance.nextInt(16)));
        }
        return sb.toString();
    }

    public boolean isPlaying() {
        return currentPlaylistId != null;
    }

    public boolean stopPlayback() {
        if (currentMediaPlayer != null) {
            currentMediaPlayer.setOnCompletionListener(null);
            currentMediaPlayer.setOnInfoListener(null);
            currentMediaPlayer.stop();
            currentMediaPlayer.release();
        }

        if (nextMediaPlayer != null) {
            nextMediaPlayer.setOnCompletionListener(null);
            nextMediaPlayer.setOnInfoListener(null);
            nextMediaPlayer.stop();
            nextMediaPlayer.release();
        }

        currentPlaylist = null;
        currentPlaylistId = null;

        return true;
    }

    public boolean hideScreen() {
        float alpha = surfaceView.getAlpha();
        surfaceView.setAlpha(0);
        return alpha != 0;
    }

    public boolean showScreen() {
        float alpha = surfaceView.getAlpha();
        surfaceView.setAlpha(1);
        return alpha != 1;
    }
}
