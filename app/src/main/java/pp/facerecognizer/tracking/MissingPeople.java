package pp.facerecognizer.tracking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Map;

import pp.facerecognizer.R;

public class MissingPeople extends AppCompatActivity {
    EditText find1;
    TextView f1,f2,f3;
    String y;
    ImageButton searchf;
    Firebase reference1;
    String f1s,f2s,f3s,find1s;
    ImageView imgs;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Firebase.setAndroidContext(this);
        find1=(EditText)findViewById(R.id.searchname);
        searchf=(ImageButton)findViewById(R.id.find);
        imgs=(ImageView)findViewById(R.id.imgs) ;
        final long ONE_MEGABYTE = 1024 * 1024;
        f2=(TextView)findViewById(R.id.names);
          File imgFile = new File("/storage/emulated/0/11.jpg");

        f3=(TextView)findViewById(R.id.loc);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/missing/" );
        searchf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                find1s=find1.getText().toString();
                reference1.orderByChild("searchname").equalTo(find1s).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                            f2s= dataSnapshot1.child("contact").getValue().toString();
                            f2.setText(f2s);
                            f3s= dataSnapshot1.child("location").getValue().toString();
                            f3.setText(f3s);
//                            if(imgFile.exists()){
//
//                                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//
//                                imgs.setImageBitmap(myBitmap);
//
//                            }


                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                storage = FirebaseStorage.getInstance();
                storageRef = storage.getReferenceFromUrl("gs://mchat-b2462.appspot.com/").child(find1s+".jpg");;

                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.i("Main", "File uri: " + uri.toString());
                        y=uri.toString();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(MissingPeople.this, "Failed", Toast.LENGTH_SHORT).show();

                    }

                });
                Picasso.with(MissingPeople.this).load(y).into(imgs);}

        });

    }
}
