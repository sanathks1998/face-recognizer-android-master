package pp.facerecognizer.tracking;

import
        android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import pp.facerecognizer.R;


public class Donate extends AppCompatActivity {
    EditText nm,cc;
    CheckBox de,mn,ut;
    RadioGroup r1;
    RadioButton genderradioButton;
    Button  dn;
    StringBuilder result;
    DatabaseReference ref;
    String n1,n2,n3;
    FirebaseDatabase database;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate);
        nm=(EditText)findViewById(R.id.nm);
        de=(CheckBox)findViewById(R.id.dr);
        mn=(CheckBox)findViewById(R.id.mn);
        ut=(CheckBox)findViewById(R.id.ut);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        result=new StringBuilder();

        Firebase.setAndroidContext(this);
// cc=(EditText)findViewById(R.id.cc);
        r1=(RadioGroup)findViewById(R.id.r1);
        dn=(Button)findViewById(R.id.dn);

        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(de.isChecked())
                {
                    result.append(("dress, "));

                }
                if(mn.isChecked())
                {
                    result.append(("money, "));

                }

                if(ut.isChecked())
                {
                    result.append(("Utilities, "));

                }

                Toast.makeText(Donate.this,result, Toast.LENGTH_SHORT).show();
                int selectedId = r1.getCheckedRadioButtonId();
                genderradioButton = (RadioButton) findViewById(selectedId);
                if(selectedId==-1){
                    Toast.makeText(Donate.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Donate.this, genderradioButton.getText(), Toast.LENGTH_SHORT).show();//               Toast.makeText(Donate.this, "hi", Toast.LENGTH_SHORT).show();
                }
                Firebase reference1 = new Firebase("https://mchat-b2462.firebaseio.com/donate/");

                DatabaseReference usersRef = ref.child("Donate/");
                if (!nm.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Donor", nm.getText().toString());
                    map.put("Donates", result.toString());
                    map.put("Collectioncamp", genderradioButton.getText().toString());
                    reference1.push().setValue(map);

                }

        }
     });
    }
}
