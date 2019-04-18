package velorum.com.wildwestreflexv2;

import androidx.appcompat.app.AppCompatActivity;
import velorum.com.wildwestreflexv2.bl.Game;
import velorum.com.wildwestreflexv2.bl.Player;
import velorum.com.wildwestreflexv2.bl.Position;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView tvWinnerName;
    TextView tvPlayer1Name;
    TextView tvPlayer2Name;
    TextView tvCurrentRound;
    TextView tvGetReady;

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initTextViews();
        initGame();
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
        for (int i = 0; i < game.getRounds(); i++) {
            tvCurrentRound.setText(String.valueOf(game.getRounds()-i));
            getReadyCountDown();
        }
    }

    private void initTextViews() {
        tvWinnerName = findViewById(R.id.tv_winner_name);
        tvPlayer1Name = findViewById(R.id.tv_player1_name);
        tvPlayer2Name = findViewById(R.id.tv_player2_name);
        tvCurrentRound = findViewById(R.id.tv_current_round);
        tvGetReady = findViewById(R.id.tv_get_ready);
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

    private void getReadyCountDown() {
        tvGetReady.setVisibility(View.VISIBLE);
        tvGetReady.setText(getString(R.string.get_ready));
        final int totalSeconds = 5;
        long millisInFuture = totalSeconds * 1000;
        new CountDownTimer(millisInFuture, 1100) {
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
            }
        }.start();
    }
}
