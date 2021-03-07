package net.projectpro.splash;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    int score;
    int scoreB;
    public Button btnStart;
    public Button btnResume;
    public TextView tView;
    public Button btnPause;
    public Button btnCancel;
    public Button btnReset;
    long millisInFuture;
    private View view;
    Context context = this;
    MediaPlayer mp;

    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;

    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;
    public void  menu (View v){
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }





    // after overriding the instance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
       final EditText team_a=(EditText) findViewById(R.id.dis1);
        final EditText team_b=(EditText) findViewById(R.id.dis2);

        hideNavigationBar();

        Button c= (Button) findViewById(R.id.backto);
        c.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                Intent cad= new Intent(Main2Activity.this,Page_1.class);
startActivity(cad);
            }
        });

        //Object casting to the particular widget.
         tView = (TextView) findViewById(R.id.tv);
        btnStart = (Button) findViewById(R.id.btn_start);
         btnPause = (Button) findViewById(R.id.btn_pause);
          btnResume = (Button) findViewById(R.id.btn_resume);
          btnCancel = (Button) findViewById(R.id.btn_cancel);
          btnReset=(Button)findViewById(R.id.team_a_reset);




        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnCancel.setEnabled(false);


        btnStart.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

                isPaused = false;
                isCanceled = false;


                btnStart.setEnabled(false);
                btnResume.setEnabled(false);

                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                CountDownTimer timer;
                long millisInFuture = 500000;
                long countDownInterval = 1000;



                timer = new CountDownTimer(millisInFuture,countDownInterval){
                    public void onTick(long millisUntilFinished){

                        if(isPaused || isCanceled)
                        {

                            cancel();
                        }
                        else {

                            tView.setText("" + millisUntilFinished / 1000);

                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){

                        tView.setText("Done");


                        btnStart.setEnabled(true);

                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                    }
                }.start();
            }
        });


        btnPause.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

                isPaused = true;


                btnResume.setEnabled(true);
                btnCancel.setEnabled(true);
                btnStart.setEnabled(false);
                btnPause.setEnabled(false);
            }
        });


        btnResume.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){

                btnStart.setEnabled(false);
                btnResume.setEnabled(false);
                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                isPaused = false;
                isCanceled = false;


                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){

                        if(isPaused || isCanceled)
                        {

                            cancel();
                        }
                        else {
                            tView.setText("" + millisUntilFinished / 1000);

                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){

                        tView.setText("MATCH OVER");


                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);

                        btnStart.setEnabled(false);
                    }
                }.start();


                btnCancel.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v){

                        isCanceled = true;
                        if (score>scoreB){
                            Context context = getApplicationContext();
                            Toast.makeText(context,team_a +"wins", Toast.LENGTH_LONG).show();
                        }
                        else if (score==scoreB)
                        {
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Match Draw", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Context context = getApplicationContext();
                            Toast.makeText(context, team_b+"wins", Toast.LENGTH_LONG).show();
                        }
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);

                        btnStart.setEnabled(false);


                        tView.setText("MATCH OVER");
                    }
                });
            }
        });


        btnCancel.setOnClickListener(new OnClickListener(){

            public void onClick(View v){



                  if (score>scoreB){
                      Context context = getApplicationContext();
                      Toast.makeText(context, "team A wins", Toast.LENGTH_LONG).show();
                  }
                  else if (score==scoreB)
                  {
                      Context context = getApplicationContext();
                      Toast.makeText(context, "Match Draw", Toast.LENGTH_LONG).show();
                  }
                  else{
                      Context context = getApplicationContext();
                      Toast.makeText(context, "team B wins", Toast.LENGTH_LONG).show();
                  }
                isCanceled = true;


                btnPause.setEnabled(false);
                btnResume.setEnabled(false);
                btnCancel.setEnabled(false);

                btnStart.setEnabled(false);


                tView.setText("MATCH OVER.");
            }
        });

    }



// code for the button 3+

    public void scorethree(View view) {
        score = score + 3;
        board(score);

    }

    // code for button 2+
    public void scoretwo(View view) {
        score = score + 2;
        board(score);

    }

    //code for free score
    public void freescore(View view) {

        score = score + 1;
        board(score);
    }

    //code to display score
    private void board(int n) {
        TextView boardscoreTextView = (TextView) findViewById(R.id.score);
        boardscoreTextView.setText("" + n);
    }





    //code to reset
    private void croft(int v) {
        TextView quartertext = (TextView) findViewById(R.id.loool);
        isPaused=true;
        btnCancel.setEnabled(true);
        btnPause.setEnabled(false);
        btnStart.setEnabled(false);
        btnResume.setEnabled(false);

        quartertext.setText("MATCH OVER" );


    }

    public void dis(View view) {
        score = 0;
        scoreB=0;
        teamBboard(scoreB);
        board(score);
    }

    public void teamBscorethree(View view) {
        scoreB = scoreB + 3;
        teamBboard(scoreB);

    }

    // code for button 2+
    public void teamBscoretwo(View view) {
        scoreB = scoreB + 2;
        teamBboard(scoreB);

    }

    //code for free score
    public void teamBfreescore(View view) {
        scoreB = scoreB + 1;
        teamBboard(scoreB);
    }
    private void teamBboard(int n) {
        TextView boardscoreTextView = (TextView) findViewById(R.id.scoreB);
        boardscoreTextView.setText("" + n);
    }






    private void teamBcroft(int v) {
        TextView quartertext = (TextView) findViewById(R.id.loool);
        quartertext.setText(" " + v);


    }
    private void hideNavigationBar() {
        this.getWindow().getDecorView()
                .setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                );
    }




}

