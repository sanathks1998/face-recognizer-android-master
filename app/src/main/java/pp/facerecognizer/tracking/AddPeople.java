package pp.facerecognizer.tracking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pp.facerecognizer.R;

public class AddPeople extends AppCompatActivity {
    Button sub;
    ImageButton up;
    private final int PICK_IMAGE_REQUEST = 71;
    public Uri filePath;
    EditText avnum, aname, aloc;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QRCode/";
    FirebaseStorage storage;
    ImageView imageView;
    StorageReference storageReference;
    Firebase reference1;

    String avnum1, avloc, aname1, aphnum1, imgname;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmissing);
        up = (ImageButton) findViewById(R.id.btnChoose);
        imageView = (ImageView) findViewById(R.id.imgView);
        sub = (Button) findViewById(R.id.Submit);
        avnum = (EditText) findViewById(R.id.acontact);
        aname = (EditText) findViewById(R.id.aname);

        aloc = (EditText) findViewById(R.id.aloc);
        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/missing/");
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                avloc = aloc.getText().toString();
                aname1 = aname.getText().toString();
                avnum1 = avnum.getText().toString();
                if (!avnum1.equals("") && !avloc.equals("") && !aname1.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("searchname", aname1);
                    map.put("contact", avnum1);
                    map.put("location", avloc);

                    reference1.push().setValue(map);
                    uploadImage();
                    aname.setText("");
                    aloc.setText("");
                    avnum.setText("");

                }
            }
        });
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            imgname = filePath.getLastPathSegment();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();


            }
        }
    }

    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();


            final StorageReference ref = storageReference.child("gs://mchat-b2462.appspot.com/missing" + imgname);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPeople.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddPeople.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}


