package velorum.com.wildwestreflexv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText player1Name;
    TextInputEditText player2Name;
    TextInputEditText roundAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Name = findViewById(R.id.input_player1_name);
        player2Name = findViewById(R.id.input_player2_name);
        roundAmount = findViewById(R.id.input_round_amount);

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
        String p1, p2;
        int rounds;

        p1 = player1Name.getText().toString();
        p2 = player2Name.getText().toString();
        String r_text = roundAmount.getText().toString();
        rounds = (r_text.equals("")) ? -1 : Integer.parseInt(r_text);

        if ((p1.equals("") || p2.equals("") || rounds == -1))
            Toast.makeText(getApplicationContext(), getString(R.string.inputs_missing), Toast.LENGTH_SHORT).show();
        else
            generateIntent(p1, p2, rounds);
    }

    private void clearInputs() {
        player1Name.setText("");
        player2Name.setText("");
        roundAmount.setText("");
    }

    private void generateIntent(String p1_name, String p2_name, int rounds) {
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("p1_name", p1_name);
        intent.putExtra("p2_name", p2_name);
        intent.putExtra("rounds", rounds);
        clearInputs();
        startActivity(intent, options.toBundle());
    }
}
