package pp.facerecognizer.tracking;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import pp.facerecognizer.R;

public class MainActivityw extends AppCompatActivity {
    Button sub,g;
    EditText loc,ser,num;
    Firebase reference1;
    FirebaseDatabase database;
    DatabaseReference ref;

    String vloc,vser,vnum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityw);
        sub=(Button)findViewById(R.id.Submitv);
        g=(Button)findViewById(R.id.g);
        loc=(EditText)findViewById(R.id.loc);
        ser=(EditText)findViewById(R.id.ser);
        database=FirebaseDatabase.getInstance();

        ref = FirebaseDatabase.getInstance().getReference();

        num=(EditText)findViewById(R.id.numv);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/volunteer/");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vloc=loc.getText().toString();
                vser=ser.getText().toString();
                vnum=num.getText().toString();
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("volunteer/"+vloc);

                if (!vnum.equals("")&&!vloc.equals("")&&!vser.equals("")) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("location", vloc);
                    map.put("numberreq", vnum);
                    map.put("service", vser);
                    map.put("numreg","0");
                    usersRef.updateChildren(map);

                    loc.setText("");
                    num.setText("");
                    ser.setText("");


                }
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivityw.this,Retrive.class);
                startActivity(i);
            }
        });
    }
}