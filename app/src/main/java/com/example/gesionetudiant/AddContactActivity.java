package com.example.gesionetudiant;

import static com.example.gesionetudiant.R.id.btn_save;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddContactActivity extends AppCompatActivity {
    TextInputEditText mNom;
    TextInputEditText mTel;
    TextInputEditText mEmail;
    TextInputEditText mClasse;
    Button btnSave;


    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        getSupportActionBar().setTitle("Ajouter_Etudiant");

        db=FirebaseFirestore.getInstance();

        mNom=findViewById(R.id.input_nom);
        mTel=findViewById(R.id.input_tel);
        mEmail=findViewById(R.id.input_email);
        mClasse=findViewById(R.id.input_classe);


        btnSave=findViewById(btn_save);

        btnSave.setOnClickListener((view) -> {
            final String nom=mNom.getText().toString();
            final String tel=mTel.getText().toString();
            final String email=mEmail.getText().toString();
            final String classe=mClasse.getText().toString();
            if(nom.isEmpty()){
                mNom.setError("Champ Obligatoire !!!");
                return;
            }
            if(tel.isEmpty()){
                mTel.setError("Champ Obligatoire !!!");
                return;
            }
            if(email.isEmpty()){
                mEmail.setError("Champ Obligatoire !!!");
                return;
            }
            if(classe.isEmpty()){
                mClasse.setError("Champ Obligatoire !!!");
                return;
            }
            CollectionReference colRef= db.collection("Etudiants");
            Contact contact= new Contact(nom,tel,email,classe);;
            colRef.document().set(contact).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Données Ajoutées avec succés",Toast.LENGTH_LONG).show();
                        mNom.setText("");
                        mTel.setText("");
                        mEmail.setText("");
                        mClasse.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_LONG).show();
                    }
                }
            });



        });
    }
}









