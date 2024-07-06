package com.example.genshin_imp.Videos;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

public class VideoPlayerService extends Service {

    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        VideoPlayerService getService() {
            return VideoPlayerService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 视频播放完成时的操作
                // 可以在此添加相应的处理逻辑
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void playVideo(Uri videoUri) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(getApplicationContext(), videoUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseVideo() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stopVideo() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
