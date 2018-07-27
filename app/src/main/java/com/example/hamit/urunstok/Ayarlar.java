package com.example.hamit.urunstok;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class Ayarlar extends AppCompatActivity {
    Button btnurun,btnverisifirla,btnsil;
    ListView listView;
    EditText edtsil;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayarlar);
        btnurun=(Button)findViewById(R.id.button4);
        btnverisifirla=(Button)findViewById(R.id.buttonsil);
        btnsil=(Button)findViewById(R.id.buttondel);
        edtsil=(EditText)findViewById(R.id.edtsil);
        final ListView listView=(ListView)findViewById(R.id.listView1);
        btnurun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper db=new DatabaseHelper(Ayarlar.this);
                List<String> vveriler=db.VeriListele();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(Ayarlar.this , android.R.layout.simple_list_item_1, android.R.id.text1 , vveriler);
                listView.setAdapter(adapter);
            }
        });
        btnverisifirla.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                DatabaseHelper dbm=new DatabaseHelper(Ayarlar.this);
                dbm.VeriSil();
                Toast.makeText(getApplicationContext(),"Bütün Veriler Silindi.", Toast.LENGTH_LONG).show();
                dbm.VeriListele();
                  }
        });
        btnsil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            edtsil.getText();
                try {
                    if (edtsil.getText().length() > 0) {
                        DatabaseHelper dbm = new DatabaseHelper(Ayarlar.this);
                        int idsil = Integer.parseInt(edtsil.getText().toString());
                        if (idsil >= 0) {
                            dbm.deleteData(idsil + "");
                            Toast.makeText(getApplicationContext(), "Ürün Silindi", Toast.LENGTH_LONG).show();
                        }
                    }
                }catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "Bu Kayıt Yok veya Sayı Girilmeli", Toast.LENGTH_LONG).show();
                }
            }
        });

}

}