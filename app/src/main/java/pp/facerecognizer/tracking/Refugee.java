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

public class Refugee extends AppCompatActivity {
    Button sub,g;
    EditText locr,numc,numm,numw,nap,util,uten,dress,dol,det,bat,band,burn,dust,h2o,snack,glu,cons;
    Firebase reference1;
    FirebaseDatabase database;
    DatabaseReference ref;

    String vcon,vlocr,vnumc,vnumm,vnumw,vnap,vutil,vuten,vdress,vdol,vdet,vbat,vband,vburn,vdust,vh2o,vsnack,vglu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refugee);
        sub=(Button)findViewById(R.id.Submitr);
        g=(Button)findViewById(R.id.g);
        locr=(EditText)findViewById(R.id.locr);
        numc=(EditText)findViewById(R.id.numc);
        numm=(EditText)findViewById(R.id.numm);
        numw=(EditText)findViewById(R.id.numw);
        nap=(EditText)findViewById(R.id.san);
        util=(EditText)findViewById(R.id.util);
        uten=(EditText)findViewById(R.id.uten);
        dress=(EditText)findViewById(R.id.dress);
        dol=(EditText)findViewById(R.id.dol);
        det=(EditText)findViewById(R.id.det);
        band=(EditText)findViewById(R.id.band);
        burn=(EditText)findViewById(R.id.burn);
        dust=(EditText)findViewById(R.id.dust);
        h2o=(EditText)findViewById(R.id.h20);

        snack=(EditText)findViewById(R.id.snack);
        glu=(EditText)findViewById(R.id.glu);
        cons=(EditText)findViewById(R.id.conr);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference();

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/volunteer/");
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Refugee.this,CampRetrive.class);
                startActivity(i);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vlocr=locr.getText().toString();
                vnumc=numc.getText().toString();
                vnumm=numm.getText().toString();
                vnumw=numw.getText().toString();
                vnap=nap.getText().toString();
                vutil=util.getText().toString();
                vuten=uten.getText().toString();
                vdress=dress.getText().toString();
                vdol=dol.getText().toString();
                vdet=det.getText().toString();
                vband=band.getText().toString();
                vburn=burn.getText().toString();
                vdust=dust.getText().toString();
                vh2o=h2o.getText().toString();
                vsnack=snack.getText().toString();
                vglu=glu.getText().toString();
                vcon=cons.getText().toString();

                DatabaseReference usersRef = ref.child("refugee/"+vlocr+"rc/");
                if (!vlocr.equals("")) {
//                    Map<String, Volunteer> users = new HashMap<>();
//                    users.put(vloc, new Volunteer(vloc,vser,vnum,"0"));
//
//                    usersRef.setValue(users);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("location", vlocr);
                    map.put("children", vnumc);
                    map.put("men", vnumm);
                    map.put("women",vnumw);
                    map.put("napkins",vnap);
                    map.put("utility",vutil);
                    map.put("utensil",vuten);
                    map.put("dress",vdress);
                    map.put("Mdollor",vdol);
                    map.put("Mbatadin",vbat);
                    map.put("Mfirstaid",vband);
                    map.put("Mburnol",vburn);
                    map.put("Mdustingpowder",vdust);
                    map.put("watercan",vh2o);
                    map.put("snacksAndFood",vsnack);
                    map.put("glucose",vglu);
                    map.put("contact",vcon);


                    usersRef.updateChildren(map);
//                    reference1.push().setValue(map);

                    locr.setText("");
                    numc.setText("");
                    numm.setText("");
                    numw.setText("");
                    nap.setText("");
                    util.setText("");
                    uten.setText("");
                    dress.setText("");
                    dust.setText("");
                    dol.setText("");
                    burn.setText("");
                    band.setText("");
                    bat.setText("");
                    h2o.setText("");
                    snack.setText("");
                    cons.setText("");
                    glu.setText("");




                }
            }
        });

    }
}