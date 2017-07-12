package br.com.universomw8.rnmediawesome.player;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Player {
    private final SurfaceHolder surface;
    private Activity currentActivity;
    private HashMap<String, ArrayDeque<File>> playlists;
    private String currentPlaylistId;
    private Deque<File> currentPlaylist;
    private MediaPlayer nextMediaPlayer;
    private MediaPlayer currentMediaPlayer;

    public Player(Activity currentActivity, VideoView videoView) {
        this.playlists = new HashMap<>();
        this.currentActivity = currentActivity;
        this.surface = videoView.getHolder();
    }

    public String createPlaylist(ArrayDeque<File> files) {
        String uid = "4";

        File directory = currentActivity.getFilesDir();

        ArrayDeque<File> videos = new ArrayDeque<>();

        videos.push(new File(directory.getAbsolutePath() + "/service.mp4"));
        videos.push(new File(directory.getAbsolutePath() + "/wearables.mp4"));
        videos.push(new File(directory.getAbsolutePath() + "/pay.mp4"));

        this.playlists.put(uid, videos);

        return uid;
    }

    public ArrayDeque<File> getPlaylist(String id) {
        return playlists.get(id);
    }

    public boolean startPlaylist(String id) {

        id = "4";

        if (currentMediaPlayer != null)
            currentMediaPlayer.release();

        if (nextMediaPlayer != null)
            nextMediaPlayer.release();

        currentPlaylistId = id;
        currentPlaylist = playlists.get(id);

        surface.setKeepScreenOn(true);
        startPlayback();

        return true;
    }

    private void startPlayback() {
        currentMediaPlayer = new MediaPlayer();
        currentMediaPlayer.setDisplay(surface);

        try {
            currentMediaPlayer.setDataSource(currentActivity, Uri.fromFile(currentPlaylist.peek()));
            currentMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currentMediaPlayer.start();
        setUpNextMediaPlayer(currentMediaPlayer);
    }

    private void setUpNextMediaPlayer(final MediaPlayer mediaPlayer) {
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    currentPlaylist.addLast(currentPlaylist.pop());

                    nextMediaPlayer = new MediaPlayer();

                    try {
                        nextMediaPlayer.setDataSource(currentActivity, Uri.fromFile(currentPlaylist.peek()));
                        nextMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.setNextMediaPlayer(nextMediaPlayer);
                    setUpNextMediaPlayer(nextMediaPlayer);
                }

                return false;
            }
        });
    }
}
