package com.example.cardgame;

import android.app.Activity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.mian_btn_play);
        play.setOnClickListener(onClickListener);
        rightScore = findViewById(R.id.game_txt_scoreLeft);
        leftScore = findViewById(R.id.game_txt_scoreRight);
        leftCardImg = findViewById(R.id.game_img_card_1);
        rightCardImg = findViewById(R.id.game_img_card_2);

        cardsDeck = new CardsDeck();
        cardsDeck.shuffle();
        cardsDeck.split();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mian_btn_play:
                    // Get top cards.
                    String[] topCards = cardsDeck.getTopCards();
                    Log.d(CardsDeck.TAG, topCards[0] + " | " + topCards[1]);

                    // Show cards.
                    leftCardImg.setImageResource(getResources().getIdentifier(topCards[0], "drawable", getPackageName()));
                    rightCardImg.setImageResource(getResources().getIdentifier(topCards[1], "drawable", getPackageName()));

                    // Get winner.
                    switch (CardsDeck.getWinner(topCards[0], topCards[1])) {
                        case 0:
                            // Do nothing.
                            break;
                        case 1:
                            leftPlayerScore ++;
                            leftScore.setText("" + leftPlayerScore);
                            break;
                        case 2:
                            rightPlayerScore ++;
                            rightScore.setText("" + rightPlayerScore);
                            break;
                    }
                    break;
            }
        }
    };
}


