package com.example.gesionetudiant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    FirestoreRecyclerAdapter adapter;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recycler_contact);
        mRecyclerView.setLayoutManager(new LinearLayoutManager( this));
        mRecyclerView.setHasFixedSize(true);
        db=FirebaseFirestore.getInstance();

        CollectionReference collRef=db.collection("Etudiants");
        Query query=collRef.orderBy("nom");
        FirestoreRecyclerOptions<Contact>options= new FirestoreRecyclerOptions.Builder<Contact>().setQuery(query, Contact.class).build();
        adapter= new FirestoreRecyclerAdapter<Contact,ContactVH> (options){

            @NonNull
            @Override
            public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
                return new ContactVH(layout);

            }

            @Override
            protected void onBindViewHolder(@NonNull ContactVH holder, int position, @NonNull Contact model) {
                         holder.mNon.setText(model.getNom());
                holder.mTel.setText(model.getTel());
                Glide.with(MainActivity.this)
                        .load(model.getImage())
                        .placeholder(R.mipmap.ic_person)
                        .error(R.mipmap.ic_person)
                        .centerCrop()
                        .into(holder.mImage);
            }
        };

          mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                startActivity(new Intent(MainActivity.this, AddContactActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);

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

    class ContactVH extends RecyclerView.ViewHolder{
            TextView mNon;
            TextView mTel;
            CircleImageView mImage;

            public ContactVH(@NonNull View itemView) {
                super(itemView);
                mNon=itemView.findViewById(R.id.tv_nom);
                mTel=itemView.findViewById(R.id.tv_tel);
                mImage=itemView.findViewById(R.id.im_personne);
            }
        }
    }





