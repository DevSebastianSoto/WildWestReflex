package velorum.com.wildwestreflexv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_custom_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGame();
            }
        });

        findViewById(R.id.btn_quick_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickGame();
            }
        });
    }

    private void quickGame() {
        String p1 = "Player 1", p2 = "Player 2";
        int rounds = 3;
        generateIntent(p1, p2, rounds);
    }

    private void customGame() {
        String p1 = "Player 1", p2 = "Player 2";
        int rounds = 3;
        generateIntent(p1, p2, rounds);
    }

    private Intent generateIntent(String p1_name, String p2_name, int rounds) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("p1_name", p1_name);
        intent.putExtra("p2_name", p2_name);
        intent.putExtra("rounds", rounds);
        startActivity(intent, options.toBundle());
        return intent;
    }

    private Intent generateIntent() {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent, options.toBundle());
        return intent;
    }
}
