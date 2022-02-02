package pp.facerecognizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pp.facerecognizer.tracking.AddPeople;
import pp.facerecognizer.tracking.CampRetrive;
import pp.facerecognizer.tracking.Donate;
import pp.facerecognizer.tracking.MainActivityw;
import pp.facerecognizer.tracking.Refugee;
import pp.facerecognizer.tracking.Retrive;

public class Old extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.old);
        Button data=(Button) findViewById(R.id.ca);
        Button data3=(Button) findViewById(R.id.camp);
        Button data2=(Button) findViewById(R.id.vol);
        Button data1=(Button) findViewById(R.id.am);
        Button data4=(Button) findViewById(R.id.don);
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Old.this,
                        MainActivity.class);
                startActivity(i);

            }

        });data2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Old.this,
                        Retrive.class);
                startActivity(i);

            }

        });

        data1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Old.this,
                        AddPeople.class);
                startActivity(i);

            }

        });

        data3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Old.this,
                        CampRetrive.class);
                startActivity(i);

            }

        });
        data4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Old.this,
                        Donate.class);
                startActivity(i);

            }

        });
      }

}
