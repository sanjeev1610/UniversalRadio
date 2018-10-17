package com.example.pc.universalradio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    MediaPlayer player;
    Button button_play, button2_pause, button3_soundi, button4_soundd;
    String stream = "http://peridot.streamguys.com:7150/Mirchi";

    Boolean prepared = false;
    Boolean started = false;

    SeekBar seekBar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        button_play = (Button)findViewById(R.id.button);
//        button2_pause = (Button)findViewById(R.id.button2);
//        button3_stop = (Button)findViewById(R.id.button3);
//        button_play.setEnabled(false);
//
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//        new PlayTask().execute(stream);
//        button_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(started){
//                    started = false;
//                    mediaPlayer.pause();
//                }else{
//                    started = true;
//                    mediaPlayer.start();
//                }
//            }
//        });
        initializeUIElements();

        initializeMediaPlayer();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    private class PlayTask extends AsyncTask<String, Void, Boolean>{
//
//        @Override
//        protected Boolean doInBackground(String... strings) {
//            try {
//                mediaPlayer.setDataSource(strings[0]);
//                mediaPlayer.prepareAsync();
//                prepared = true;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return prepared;
//        }
//
//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            super.onPostExecute(aBoolean);
//            button_play.setEnabled(true);
//
//
//        }
//
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(started){
//            mediaPlayer.pause();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(started){
//            mediaPlayer.start();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(prepared){
//            mediaPlayer.release();
//        }
//    }

    private void initializeUIElements() {
//
//        playSeekBar = (ProgressBar) findViewById(R.id.progressBar1);
//        playSeekBar.setMax(100);
//        playSeekBar.setVisibility(View.INVISIBLE);

        button_play = (Button) findViewById(R.id.button);
        button_play.setOnClickListener(this);

        button2_pause = (Button) findViewById(R.id.button2);
        button2_pause.setEnabled(false);
        button2_pause.setAlpha(0);
        button2_pause.setOnClickListener(this);

        button3_soundi = (Button)findViewById(R.id.button3);
        button3_soundi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

//        button4_soundd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                seekBar.setProgress(100);
//            }
//        });

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                //textview.setText("Media Volume : " + i);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);}


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void onClick(View v) {
        if (v == button_play) {
            startPlaying();
        } else if (v == button2_pause) {
            stopPlaying();
        }
    }

    private void startPlaying() {
        button2_pause.setAlpha(1);
        button2_pause.setEnabled(true);
        button_play.setAlpha(0);
        button_play.setEnabled(false);

      //  playSeekBar.setVisibility(View.VISIBLE);

        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                player.start();
            }
        });

    }

    private void stopPlaying() {
        if (player.isPlaying()) {
            player.stop();
            player.release();
            button2_pause.setAlpha(0);
            button2_pause.setEnabled(false);
            button_play.setAlpha(1);
            button_play.setEnabled(true);

            initializeMediaPlayer();
        }

        button_play.setEnabled(true);
        button2_pause.setEnabled(false);
        //playSeekBar.setVisibility(View.INVISIBLE);
    }

    private void initializeMediaPlayer() {
        player = new MediaPlayer();
        try {
            player.setDataSource(stream);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {

            public void onBufferingUpdate(MediaPlayer mp, int percent) {
             //   playSeekBar.setSecondaryProgress(percent);
                Log.i("Buffering", "" + percent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player.isPlaying()) {
            player.stop();
        }
    }
}
