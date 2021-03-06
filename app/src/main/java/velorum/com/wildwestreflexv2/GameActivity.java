package velorum.com.wildwestreflexv2;

import androidx.appcompat.app.AppCompatActivity;
import velorum.com.wildwestreflexv2.bl.Game;
import velorum.com.wildwestreflexv2.bl.Round;
import velorum.com.wildwestreflexv2.bl.Player;
import velorum.com.wildwestreflexv2.bl.Position;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView tvWinnerName;
    TextView tvPlayer1Name;
    TextView tvPlayer2Name;
    TextView tvCurrentRound;
    TextView tvGetReady;
    View p1Screen;
    View p2Screen;

    Game game;
    private int currentRound;
    private boolean freeFire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        currentRound = 0;
        freeFire = false;
        initViews();
        initGame();
        p1Screen.setOnTouchListener(setTimeToPlayerOnClick(game.getP1(), game.getP2()));
        p2Screen.setOnTouchListener(setTimeToPlayerOnClick(game.getP2(), game.getP1()));

    }

    @Override
    protected void onStart() {
        super.onStart();
        playGame();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void playGame() {
        handleRound(currentRound);
    }

    private void initViews() {
        tvWinnerName = findViewById(R.id.tv_winner_name);
        tvPlayer1Name = findViewById(R.id.tv_player1_name);
        tvPlayer2Name = findViewById(R.id.tv_player2_name);
        tvCurrentRound = findViewById(R.id.tv_current_round);
        tvGetReady = findViewById(R.id.tv_get_ready);
        p1Screen = findViewById(R.id.btnPlayer1);
        p2Screen = findViewById(R.id.btnPlayer2);
    }

    private void initGame() {
        Intent intent = getIntent();
        String p1Name = intent.getStringExtra("p1_name");
        String p2Name = intent.getStringExtra("p2_name");
        int rounds = intent.getIntExtra("rounds", 1);

        Player p1 = new Player(p1Name, Position.LEFT);
        Player p2 = new Player(p2Name, Position.RIGHT);

        initPlayerCharacter(p1, (ImageView) findViewById(R.id.iv_cowboy));
        initPlayerCharacter(p2, (ImageView) findViewById(R.id.iv_alien));

        game = new Game(p1, p2, rounds);

        tvPlayer1Name.setText(p1Name);
        tvPlayer2Name.setText(p2Name);

    }

    private void initPlayerCharacter(Player player, ImageView character) {
        player.setCharacter(character);
    }

    private View.OnTouchListener setTimeToPlayerOnClick(final Player player, final Player opponent) {
        return (new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (freeFire && player.getCurrentTime() == 0) {
                    player.setCurrentTime(getTime());
                    if (opponent.getCurrentTime() != 0) {
                        endRound();
                    }
                }
                return false;
            }
        });
    }

    private void endRound() {
        freeFire = false;
        Player roundWinner = game.getRoundList().get(currentRound).handleRoundWinner(game.getP1(), game.getP2());
        roundWinner.addScore(1);

        String winnerName = (game.getWinner() != null) ? game.getWinner().getName() : getString(R.string.tie);
        tvWinnerName.setText(winnerName);
        currentRound++;
        handleRound(currentRound);
    }

    private void getReadyCountDown() {
        tvGetReady.setVisibility(View.VISIBLE);
        tvGetReady.setText(getString(R.string.get_ready));
        final int totalSeconds = 5;
        long millisInFuture = totalSeconds * 1000;
        CountDownTimer getReadyCountDown = new CountDownTimer(millisInFuture, 1100) {
            int currentSeconds = totalSeconds;

            @Override
            public void onTick(long l) {
                if (currentSeconds < 4)
                    tvGetReady.setText(String.valueOf(currentSeconds));
                currentSeconds--;
            }

            @Override
            public void onFinish() {
                tvGetReady.setVisibility(View.GONE);
                playGunShotSound();
            }
        }.start();
    }

    private void playGunShotSound() {
        long millisInFuture = (new Random()).nextInt(10) * 1000;
        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.gun_shot);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });

        new CountDownTimer(millisInFuture, 1000) {

            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                initRound();
                mp.start();
            }
        }.start();
    }

    private void initRound() {
        freeFire = true;
        game.getRoundList().get(currentRound).setStartTime(getTime());
    }

    private void handleRound(int round) {
        if (round < game.getRounds()) {
            tvCurrentRound.setText(String.valueOf(round + 1));
            getReadyCountDown();
        } else {
            final Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
            new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long l) {
                    tvGetReady.setText(getString(R.string.game_over));
                    tvGetReady.setVisibility(View.VISIBLE);
                }

                @Override
                public void onFinish() {
                    tvGetReady.setVisibility(View.GONE);
                    startActivity(intent);
                }
            }.start();
        }
    }

    private float getTime() {
        return System.nanoTime();
    }
}
