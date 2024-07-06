package com.example.genshin_imp.Videos;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.genshin_imp.R;

public class VideosActivity extends AppCompatActivity {

    private VideoView videoView;
    private Button btnPlay, btnPause, btnStop;
    private VideoPlayerService videoPlayerService;
    private boolean isBound = false;
    //获取传来的信息.
    public String characterName ;


    public String getCharacterName(String characterName) {
        if(characterName.equals("胡桃"))    return "hutao";
        else if (characterName.equals("夜兰")) return "yelan";
        else if (characterName.equals("爷")) return "ye";
        else if (characterName.equals("可莉")) return "keli";
        else if (characterName.equals("甘雨")) return "ganyu";
        else if (characterName.equals("神里绫华")) return "shenli";
        else return "genshin_1";
    }

    //构建对应的文件目录,命名规则: video_角色名_pv
    public String characterVideos;
    public int videoResourceId ;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            VideoPlayerService.LocalBinder binder = (VideoPlayerService.LocalBinder) service;
            videoPlayerService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_videos);
        characterName = getIntent().getStringExtra("character_name");
        characterVideos="video_"+getCharacterName(characterName)+"_pv";
        videoResourceId = getResources().getIdentifier(characterVideos, "raw", getPackageName());

        videoView = findViewById(R.id.videoView);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);


        // 绑定Service
        Intent serviceIntent = new Intent(this, VideoPlayerService.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseVideo();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo();
            }
        });
    }


    private void playVideo() {

        try {
            if (isBound && videoPlayerService != null) {
//                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.genshin_1);
                Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResourceId);
                videoPlayerService.playVideo(videoUri);
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(videoUri);
                videoView.requestFocus();
                videoView.start();
            } else {
                Log.e("VideoPlayerService", "Service not bound or null");
            }
        } catch (Exception e) {
            Log.e("VideoPlayerService", "Error playing video: " + e.getMessage());
            e.printStackTrace();
        }
    }


//    private void playVideo() {
//        if (isBound && videoPlayerService != null) {
//            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.genshin_1); // 替换为你的视频资源
//            videoPlayerService.playVideo(videoUri);
//
//            MediaController mediaController = new MediaController(this);
//            mediaController.setAnchorView(videoView);
//            videoView.setMediaController(mediaController);
//            videoView.setVideoURI(videoUri);
//            videoView.requestFocus();
//            videoView.start();
//        }
//    }

    private void pauseVideo() {
        if (isBound && videoPlayerService != null) {
            videoPlayerService.pauseVideo();
            videoView.pause();
        }
    }

    private void stopVideo() {
        if (isBound && videoPlayerService != null) {
            videoPlayerService.stopVideo();
            videoView.stopPlayback();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解绑Service
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }
}


