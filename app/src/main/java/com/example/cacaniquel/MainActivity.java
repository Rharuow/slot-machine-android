package com.example.cacaniquel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView slot1 = findViewById(R.id.slot1);
        ImageView slot2 = findViewById(R.id.slot2);
        ImageView slot3 = findViewById(R.id.slot3);

        EditText totalValue = findViewById(R.id.totalValue);
        EditText apostaValue = findViewById(R.id.apostaValue);

        Integer[] total = {Integer.parseInt(totalValue.getText().toString())};
        Integer[] aposta = {Integer.parseInt(apostaValue.getText().toString())};

        Intent intent = new Intent(this, GameOver.class);


        int[] position = {1};

        int[] images = {R.drawable.banana, R.drawable.berry, R.drawable.estrela, R.drawable.sete};

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(position[0] >= 1 && position[0] <= 3)
                    position[0]++;
                else {

                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                int cont = 0;

                int image1 = R.drawable.banana;
                int image2 = R.drawable.berry;
                int image3 = R.drawable.estrela;

                while (total[0] > 1) {
                    switch (position[0]) {
                        case 1:
                            slot1.setImageResource(images[cont]);
                            image1 = images[cont];
                            break;
                        case 2:
                            slot2.setImageResource(images[cont]);
                            image2 = images[cont];
                            break;
                        case 3:
                            slot3.setImageResource(images[cont]);
                            image3 = images[cont];
                            break;
                        case 4:
                            if(image1 == image2 && image2 == image3) {
                                total[0] = total[0] + aposta[0];
                                aposta[0] = aposta[0] * 2;
                            } else {
                                total[0] = total[0] - aposta[0];
                                aposta[0] = total[0] / 2;
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    totalValue.setText(total[0].toString());
                                    apostaValue.setText(aposta[0].toString());
                                }
                            });

                            position[0] = 1;
                            break;
                    }


                    cont = cont == 3 ? 0 : cont + 1;

                    try {
                            Thread.sleep( min(1000, ((total[0]/(aposta[0] + 1)) * 1000)));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                startActivity(intent);
            }
        }).start();



    }
}