package pp.facerecognizer.tracking;




import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import pp.facerecognizer.R;


public class CampRetrive extends AppCompatActivity {
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference ref;

    Firebase reference1;
    EditText names;
    String t;
    ArrayList<String> list,list1,list2;
    int v1,v2,v3;
    //FirebaseListAdapter adapter;
    FirebaseRecyclerAdapter adapter1;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campr);
        recyclerView=(RecyclerView)findViewById(R.id.listcamp);
        Firebase.setAndroidContext(this);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/volunteer/");


        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

    }
    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("refugee");

        FirebaseRecyclerOptions<Camp> options =
                new FirebaseRecyclerOptions.Builder<Camp>()
                        .setQuery(query, new SnapshotParser<Camp>() {
                            @NonNull
                            @Override
                            public Camp parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Camp(snapshot.child("location").getValue().toString(),
                                        snapshot.child("contact").getValue().toString(),
                                        snapshot.child("children").getValue().toString(),

                                        snapshot.child("men").getValue().toString(),
                                        snapshot.child("women").getValue().toString()

                                );
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Camp, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.camplist, parent, false);

                return new ViewHolder(view);
            }



            @Override
            protected void onBindViewHolder(final ViewHolder holder, final int position, final Camp model) {
                holder.setTxtLocation("Location: "+model.getLocation());
                holder.setTxtCon("contact: "+model.getContact());
                v1=Integer.parseInt(model.getChildren());
                v2=Integer.parseInt(model.getMen());
                v3=Integer.parseInt(model.getWomen());

                //String.valueOf(v1-v2)
                holder.setTxtCour("No. of people: "+String.valueOf(v1+v2+v3));
t="Children: "+model.getChildren()+" Men: "+model.getMen()+" Women: "+model.getWomen();


                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpannableStringBuilder biggerText = new SpannableStringBuilder(t);

                        biggerText.setSpan(new RelativeSizeSpan(1.35f), 0, t.length(), 0);
                        Toast.makeText(CampRetrive.this, biggerText, Toast.LENGTH_LONG).show();
                         // Toast.makeText(CampRetrive.this, t, Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(Retrive.this, g, Toast.LENGTH_SHORT).show();

                    }
                });
            }

        };

        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView loc;
        public TextView con;
        public TextView cour;
      //  public Button join;
        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_camp);
            loc = itemView.findViewById(R.id.locr);
            con = itemView.findViewById(R.id.conr);
            cour=itemView.findViewById(R.id.countr);
           }

        public void setTxtLocation(String string) {
            loc.setText(string);
        }


        public void setTxtCon(String string) {
            con.setText(string);
        }
        public void setTxtCour(String string) {
            cour.setText(string);
        }

    }
}
