package com.example.cacaniquel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView slot1 = findViewById(R.id.slot1);
        ImageView slot2 = findViewById(R.id.slot2);
        ImageView slot3 = findViewById(R.id.slot3);

        int[] position = {1};

        int[] images = {R.drawable.banana, R.drawable.berry, R.drawable.estrela, R.drawable.sete};

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(position[0] == 1 || position[0] == 2)
                    position[0] = position[0] + 1;
                else
                    position[0] = 0;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                int cont = 0;

                while (true) {
                    switch (position[0]) {
                        case 1:
                            slot1.setImageResource(images[cont]);
                            break;
                        case 2:
                            slot2.setImageResource(images[cont]);
                            break;
                        case 3:
                            slot3.setImageResource(images[cont]);
                            break;
                    }
                    cont = cont == 2 ? 0 : cont + 1;

                    try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        }).start();



    }
}