package com.example.hamit.urunstok;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Giris extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btn,btnveri;
    EditText edt1,edt2,edt3,edt4,edt5;
    String[] tarihi;
    String[] butun;
    String yenitarih;
    Calendar mcurrentTime = Calendar.getInstance();
    int gun,ay,yil;

    SimpleDateFormat bicim=new SimpleDateFormat("dd:M:yyyy");
    Date tarihim=new Date();
    String tarih=bicim.format(tarihim);
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
    int minute = mcurrentTime.get(Calendar.MINUTE);

    String saat=(hour + "." + minute);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris);
        myDb = new DatabaseHelper(this);
        edt1 = (EditText)findViewById(R.id.editText);
        edt2 = (EditText)findViewById(R.id.editText2);
        edt2.setText(tarih);
        edt3 = (EditText)findViewById(R.id.editText3);

        edt4 = (EditText)findViewById(R.id.editText4);
        edt5 = (EditText)findViewById(R.id.editText5);
        edt5.setText(saat);
        btn=(Button)findViewById(R.id.btnekle);
        btnveri=(Button)findViewById(R.id.btnveri);
        btnveri.setEnabled(false);

        btnveri.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent intent = new Intent(Giris.this,Urunkontrol.class);
                startActivity(intent);

            }
        });

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mik=edt3.getText().toString();
                        try{
                            double miktar = Double.parseDouble(mik);
                            if(edt1.getText().toString().length()>0 && edt1.getText().toString().length()>0
                                    && edt2.getText().toString().length()>0 && edt3.getText().toString().length()>0
                                    && edt4.getText().toString().length()>0 && miktar>0 && edt5.getText().toString().length()>0){
                                String tarihfor=edt2.getText().toString();

                                tarihi = tarihfor.split(":");

                                gun=Integer.parseInt(tarihi[0].trim());
                                ay=Integer.parseInt(tarihi[1].trim());
                                yil=Integer.parseInt(tarihi[2].trim());

                                if(gun<=31 && gun>0 && ay>0 && ay<=12 && yil>0 && yil<=2017
                                        && tarihi[0].trim().length()<=2 && tarihi[1].trim().length()<=2
                                        && tarihi[2].trim().length()<=4){

                                boolean isInserted = myDb.insertData(edt1.getText().toString(),
                                        edt2.getText().toString(), edt3.getText().toString(),edt4.getText().toString(),edt5.getText().toString());

                                if(isInserted == true){
                                    Toast.makeText(Giris.this,"Veri Kaydedildi", Toast.LENGTH_SHORT).show();
                                    btnveri.setEnabled(true);}
                                else{
                                    Toast.makeText(Giris.this,"Veri Kaydedilmedi",Toast.LENGTH_SHORT).show();}}

                                else{
                                    Toast.makeText(Giris.this,"Tarih Formatı GG.AA.YYYY ŞEKLİNDE OLMALI",Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Toast.makeText(Giris.this,"Lütfen Alanları Boş Bırakmayın",Toast.LENGTH_LONG).show();
                            }
                        }catch(Exception ex){
                            Toast.makeText(Giris.this,"Alanları boş bırakmayın Miktar Bilgisi Sayı olmalı",Toast.LENGTH_SHORT).show();
                        }

                    }

                }
        );
    }
    {


    }}
