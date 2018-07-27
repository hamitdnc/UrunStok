package com.example.hamit.urunstok;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Raporla extends AppCompatActivity {

    Spinner sp;
    TextView tv,tv2,tv3,tv4;
    DatabaseHelper db;
    Button btn;
    EditText edtarih;
    int gun,ay,yil;
    Calendar mcurrentTime = Calendar.getInstance();
    SimpleDateFormat bicim=new SimpleDateFormat("dd:M:yyyy");
    Date tarihim=new Date();
    String tarih=bicim.format(tarihim);
    String urunum;
    String[] yeniyil;
    String[] yeniay;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rapor);
        sp=(Spinner)findViewById(R.id.spinner);
        tv=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.textView);
        tv3=(TextView)findViewById(R.id.text3);
        tv4=(TextView)findViewById(R.id.tv4);
        db=new DatabaseHelper(Raporla.this);
        List<String> veriler=db.VeriSpinner();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Raporla.this , android.R.layout.simple_dropdown_item_1line, android.R.id.text1 , veriler);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(select);
        btn=(Button)findViewById(R.id.btnozel);
        edtarih=(EditText)findViewById(R.id.ed6);
        edtarih.setText(tarih);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{ double ozeltop=0;
                String[] ozeltarih=edtarih.getText().toString().split(":");
                gun=Integer.parseInt(ozeltarih[0]);
                ay=Integer.parseInt(ozeltarih[1]);
                yil=Integer.parseInt(ozeltarih[2]);

                if(gun<=31 && gun>0 && ay>0 && ay<=12 && yil>0 && yil<=2017 &&
                        ozeltarih[0].trim().length()<=2 && ozeltarih[1].trim().length()<=2
                        && ozeltarih[2].trim().length()<=4){
                urunum=sp.getSelectedItem().toString();
                Cursor res=db.butunveriler();

                while(res.moveToNext()){

                 if(res.getString(2).trim().equals(edtarih.getText().toString()) && res.getString(1).trim().equals(urunum)){
                     ozeltop+=res.getInt(3);
                 }

                }
                    tv.setText(ozeltop + " Kilo");

                }
                else{
                    Toast.makeText(Raporla.this,"Tarih Formatı GG.A.YYYY ŞEKLİNDE OLMALI",Toast.LENGTH_LONG).show();
                }}
                catch(Exception ex){

                    Toast.makeText(Raporla.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    AdapterView.OnItemSelectedListener select=new AdapterView.OnItemSelectedListener(){

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String.valueOf(sp.getSelectedItem());
            double toplamozeltarih=0.0;
            double toplamyil=0.0;
            double toplamhafta=0.0;
            double toplamay=0.0;
            Toast.makeText(Raporla.this,String.valueOf(sp.getSelectedItem()),Toast.LENGTH_SHORT).show();
            Calendar simdi=Calendar.getInstance();
            String aylar[]={"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık"};
            int ay=simdi.get(Calendar.MONTH);
            int yil=simdi.get(Calendar.YEAR);
            int hafta=simdi.get(Calendar.WEEK_OF_YEAR);
            int ayi=0;

            try{
                Calendar call=Calendar.getInstance();
                int  aylarimm=call.get(Calendar.MONTH)+1;
                String ayay=aylarimm+"";
                Cursor cursor = db.ayveriler();
                while (cursor.moveToNext()) {
                    String ayim=cursor.getString(1);
                    String[] aybilgi=ayim.trim().split(":");

                    if(aybilgi[1].trim().equals(ayay.trim()) && cursor.getString(0).equals(sp.getSelectedItem().toString())){
                        toplamay += Integer.parseInt(cursor.getString(2));
                    }
                }

                tv3.setText(toplamay + " Kilo");}
            catch(Exception ex){
                Toast.makeText(Raporla.this,ex.getMessage(),Toast.LENGTH_LONG).show();
            }
            try{
                Cursor cur = db.haftaveriler();

                while (cur.moveToNext()) {

                    Calendar cal=Calendar.getInstance();
                    int hafta_simdi=cal.get(Calendar.WEEK_OF_YEAR);
                    String yilim=cur.getString(1);
                    String format= "dd:MM:yyyy";
                    SimpleDateFormat df=new SimpleDateFormat(format);
                    Date date=df.parse(yilim);
                    cal.setTime(date);
                    int hafta_veritab=cal.get(Calendar.WEEK_OF_YEAR);
                    cur.getString(2);
                    if(hafta_simdi==hafta_veritab && cur.getString(0).equals(sp.getSelectedItem().toString()))
                    toplamhafta += Integer.parseInt(cur.getString(2));
                }
                tv2.setText(toplamhafta + " Kilo");
                }
            catch(Exception ex){
                Toast.makeText(Raporla.this,ex.getMessage(),Toast.LENGTH_LONG).show();
            }
            try{
                Cursor res = db.yilveriler();

                while (res.moveToNext()) {
                    String yilim=res.getString(2);
                    yeniyil=yilim.trim().split(":");
                    if(yeniyil[2].equals("2017") && res.getString(1).trim().equals(sp.getSelectedItem().toString()))
                    toplamyil += res.getInt(3);

                }

                tv4.setText(toplamyil + " Kilo");}
            catch(Exception ex){

            }

            try{
                Cursor res = db.getMiktar(String.valueOf(sp.getSelectedItem()));

                while (res.moveToNext()) {
                    toplamozeltarih += res.getInt(0); // miktar gelecek

                }
                tv.setText(toplamozeltarih + " Kilo");}
            catch(Exception ex){
                ex.printStackTrace();
            }
        }


        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };
}