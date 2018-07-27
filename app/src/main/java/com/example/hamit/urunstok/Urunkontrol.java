package com.example.hamit.urunstok;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
public class Urunkontrol extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnguncel;
    EditText edt1,edt2,edt3,edt4;
    String[] tarihi;
    Calendar mcurrentTime = Calendar.getInstance();
    int gun,ay,yil;
    int year = mcurrentTime.get(Calendar.YEAR);
    int month = mcurrentTime.get(Calendar.MONTH);
    int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urunkontrol);
        myDb = new DatabaseHelper(this);
        edt1 = (EditText)findViewById(R.id.edit1);
        edt2 = (EditText)findViewById(R.id.edit2);
        edt3 = (EditText)findViewById(R.id.edit3);
        edt4 = (EditText)findViewById(R.id.edit4);
        idmiktarirs();
        btnguncel=(Button)findViewById(R.id.btnguncel);
        btnguncel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    String mik=edt3.getText().toString().trim();
                    try{

                        double miktar = Double.parseDouble(mik);
                        if(edt1.getText().toString().length()>0 && edt1.getText().toString().length()>0
                                && edt2.getText().toString().length()>0 && edt3.getText().toString().length()>0
                                && edt4.getText().toString().trim().length()>0 && miktar>0){
                            String tarihfor=edt2.getText().toString();

                            tarihi = tarihfor.split(":");
                            gun=Integer.parseInt(tarihi[0].trim());
                            ay=Integer.parseInt(tarihi[1].trim());
                            yil=Integer.parseInt(tarihi[2].trim());
                            if(gun<=31 && gun>0 && ay>0 && ay<=12 && yil>0 && yil<=2017
                                    && tarihi[0].trim().length()<=2 && tarihi[1].trim().length()<=2
                                    && tarihi[2].trim().length()<=4){
                                boolean isUpdate = myDb.updateData(id+"",edt1.getText().toString(),
                                        edt2.getText().toString(),
                                        edt3.getText().toString(),edt4.getText().toString());
                                if(isUpdate == true) {
                                    Toast.makeText(Urunkontrol.this, "Güncellendi", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(Urunkontrol.this,"Güncellenmedi",Toast.LENGTH_LONG).show();}
                                }

                            else{
                                Toast.makeText(Urunkontrol.this,"Format Bu Şekilde Olmalı GG.AA.YYYY",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(Urunkontrol.this,"Lütfen Alanları Boş Bırakmayın ve MİKTAR bilgisi SAYI olmalı",Toast.LENGTH_LONG).show();
                        }
                    }catch(Exception ex){
                        Toast.makeText(Urunkontrol.this,ex.getMessage(),Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }
    public void idmiktarirs(){
        try{
            Cursor res = myDb.butunveriler();

            while (res.moveToNext()) {

                id= res.getInt(0);
                edt1.setText(res.getString(1));
                edt2.setText(res.getString(2));
                edt3.setText(res.getInt(3)+" ");
                edt4.setText(res.getString(4));
            }

        }

        catch(Exception ex){
            Toast.makeText(Urunkontrol.this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
