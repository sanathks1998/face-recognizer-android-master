package pp.facerecognizer.tracking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import pp.facerecognizer.R;

public class Retrive extends AppCompatActivity {
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    Volunteer volunteer;
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
        setContentView(R.layout.listvolunteer);
        recyclerView=(RecyclerView)findViewById(R.id.listvoc);
        Firebase.setAndroidContext(this);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        reference1 = new Firebase("https://mchat-b2462.firebaseio.com/volunteer/");

//        Query query=FirebaseDatabase.getInstance().getReference().child("volunteer");
//
//        FirebaseListOptions<Volunteer> options=new FirebaseListOptions.Builder<Volunteer>().setLayout(R.layout.volunteerlist).setLifecycleOwner(Retrive.this).setQuery(query,Volunteer.class).build();
//        adapter=new FirebaseListAdapter(options) {
//            @Override
//            protected void populateView(View v, Object model, int position) {
//                TextView loc=v.findViewById(R.id.loc);
//                TextView ser=v.findViewById(R.id.ser);
//                TextView             bal=v.findViewById(R.id.balance);
//
//                Button           join=v.findViewById(R.id.joins);
//
//
//                Volunteer volunteer=(Volunteer) model;
//
//                v1=Integer.parseInt(volunteer.getNumberreq().toString());
//                v2=Integer.parseInt(volunteer.getNumreg().toString());
//                v3=v1-v2;
//                int i=0;
//                t=volunteer.getLocation().toString();
//                loc.setText(volunteer.getLocation().toString());
//                ser.setText(volunteer.getService().toString());
//                bal.setText(String.valueOf(v3));
//                join.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
////                        Map<String, String> map = new HashMap<String, String>();
////                        map.put("numreg", String.valueOf(v2+1));
////             //           bal.setText(String.valueOf(v3));
////                        reference1.push().setValue(map);
//                        Toast.makeText(getApplicationContext(),t,Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//
//        };
//
//        listview.setAdapter(adapter1);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

    }
    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("volunteer");

        FirebaseRecyclerOptions<Volunteer> options =
                new FirebaseRecyclerOptions.Builder<Volunteer>()
                        .setQuery(query, new SnapshotParser<Volunteer>() {
                            @NonNull
                            @Override
                            public Volunteer parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Volunteer(snapshot.child("location").getValue().toString(),
                                        snapshot.child("service").getValue().toString(),
                                        snapshot.child("numberreq").getValue().toString(),

                                        snapshot.child("numreg").getValue().toString()

                                );
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Volunteer, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.volunteerlist1, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(final ViewHolder holder, final int position, final Volunteer model) {
                holder.setTxtLocation("Location: "+model.getLocation());
                holder.setTxtService("Service: "+model.getService());
                v1=Integer.parseInt(model.getNumberreq());
                v2=Integer.parseInt(model.getNumreg());
                v3=0;

                //String.valueOf(v1-v2)
                holder.setTxtNumreg("Slots Left: "+String.valueOf(Math.abs(v1-v2)));
                if(v2>=v1)
                {
                    holder.setTxtNumreg("Slots Left: "+String.valueOf("0"));
                    holder.join.setEnabled(false);
                    holder.join.setText("filled");

                }
                holder.join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //ref.child("volunteer/"+model.getLocation()+"/numberreq").setValue(String.valueOf(v1-1));
                        ref.child("volunteer/"+model.getLocation()+"/numreg").setValue(String.valueOf(v2+1));

                            holder.setjoin(model.getLocation());
                            Toast.makeText(Retrive.this, t, Toast.LENGTH_SHORT).show();

                        dis(holder); }


                });


                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  Toast.makeText(Retrive.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(Retrive.this, g, Toast.LENGTH_SHORT).show();

                    }
                });
            }

        };

        recyclerView.setAdapter(adapter);
    }

    public  void dis(ViewHolder holder)
    {holder.join.setText("Joined");
        holder.join.setEnabled(false);
        holder.join.setBackgroundResource(R.drawable.dd);}

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
        public TextView ser;
        public TextView num;
        public Button join;
        public ViewHolder(View itemView) {
            super(itemView);
            join=itemView.findViewById(R.id.joins);
            root = itemView.findViewById(R.id.list_root);
            loc = itemView.findViewById(R.id.loc);
            ser = itemView.findViewById(R.id.ser);
            num=itemView.findViewById(R.id.balance);
            names=itemView.findViewById(R.id.names);
            final String tt=names.getText().toString();
        }

        public void setTxtLocation(String string) {
            loc.setText(string);
        }


        public void setTxtService(String string) {
            ser.setText(string);
        }
        public void setTxtNumreg(String string) {
            num.setText(string);
        }
        public void setjoin(String g){
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child(g);
            Toast.makeText(Retrive.this, t, Toast.LENGTH_SHORT).show();


                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", names.getText().toString());
                usersRef.updateChildren(map);
        }
    }
}
