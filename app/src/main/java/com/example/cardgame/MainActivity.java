package com.example.cardgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private CardsDeck cardsDeck;
    private ImageView play;
    private TextView rightScore;
    private TextView leftScore;
    private ImageView leftCardImg;
    private ImageView rightCardImg;
    private int rightPlayerScore = 0;
    private int leftPlayerScore = 0;
    private int draws = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("lifeCheck", "MainActivityOnCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.game_BTN_play);
        play.setOnClickListener(onClickListener);
        rightScore = findViewById(R.id.game_LBL_scoreLeft);
        leftScore = findViewById(R.id.game_LBL_scoreRight);
        leftCardImg = findViewById(R.id.game_IMG_card_1);
        rightCardImg = findViewById(R.id.game_IMG_card_2);

        cardsDeck = new CardsDeck();
        cardsDeck.shuffle();
        cardsDeck.split();

    }

    @Override
    protected void onStart() {
        Log.d("lifeCheck", "MainActivityOnStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("lifeCheck", "MainActivityOnResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d("lifeCheck", "MainActivityOnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("lifeCheck", "MainActivityOnDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("lifeCheck", "MainActivityOnPause");
        super.onPause();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.game_BTN_play:
                    if (rightPlayerScore + leftPlayerScore + draws == 26)
                        openActivity(MainActivity.this);
                    else {
                        // Get top cards:
                        String[] topCards = cardsDeck.getTopCards();
                        Log.d(CardsDeck.TAG, topCards[0] + " | " + topCards[1]);

                        // Show cards.
                        leftCardImg.setImageResource(getResources().getIdentifier(topCards[0], "drawable", getPackageName()));
                        rightCardImg.setImageResource(getResources().getIdentifier(topCards[1], "drawable", getPackageName()));

                        // Get winner.
                        switch (CardsDeck.getWinner(topCards[0], topCards[1])) {
                            case 0:
                                // Number of draws + 1
                                draws++;
                                break;
                            case 1:
                                leftPlayerScore++;
                                leftScore.setText("" + leftPlayerScore);
                                break;
                            case 2:
                                rightPlayerScore++;
                                rightScore.setText("" + rightPlayerScore);
                        }
                    }
                    break;
            }
        }
    };

    private void openActivity(Activity activity) {
        Intent myIntent = new Intent(activity, WinnerActivity.class);
        myIntent.putExtra("LEFT_SCORE",String.valueOf(leftScore.getText()));
        myIntent.putExtra("RIGHT_SCORE",String.valueOf(rightScore.getText()));
        startActivity(myIntent);
    }

}
