package com.example.hamit.urunstok;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    Button btngir,btnrap,btnayar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btngir=(Button)findViewById(R.id.button5);
        btnrap=(Button)findViewById(R.id.button6);
        btnayar=(Button)findViewById(R.id.button7);
        btngir.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Giris.class);
                startActivity(intent);

            }
        });
        btnayar.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Ayarlar.class);
                startActivity(intent);

            }
        });
        btnrap.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Raporla.class);
                startActivity(intent);

            }
        });
    }
}
