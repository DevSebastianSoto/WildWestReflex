package velorum.com.wildwestreflexv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
