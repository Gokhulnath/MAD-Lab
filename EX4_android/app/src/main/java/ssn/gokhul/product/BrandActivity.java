package ssn.gokhul.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class BrandActivity extends AppCompatActivity {

    TextInputLayout bNameTIL;
    TextInputLayout bDescTIL;
    TextInputEditText bNameTIET;
    TextInputEditText bDescTIET;
    Button addBT;
    Button cancelBT;
    ArrayList<String> brandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);

        bNameTIL = findViewById(R.id.bNameTIL);
        bDescTIL = findViewById(R.id.bDescTIL);
        bNameTIET = findViewById(R.id.bNameTIET);
        bDescTIET = findViewById(R.id.bDescTIET);
        addBT = findViewById(R.id.addBT);
        cancelBT = findViewById(R.id.cancelBT);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> brand = new HashMap<>();

        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        brandName = new ArrayList<>();
        db.collection("Brand")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("bug", document.getId() + " => " + document.getData());
                                brandName.add(document.getData().get("Name").toString());
                            }
                        } else {
                            Log.w("bug", "Error getting documents.", task.getException());
                        }
                    }
                });

        addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (brandName.contains(bNameTIET.getEditableText().toString()) || bNameTIET.getEditableText().length() == 0) {
                    bNameTIL.setErrorEnabled(true);
                    bNameTIL.setError("The Brand name already exists or invalid!");
                }
                if (bDescTIET.getEditableText().length() == 0) {
                    bDescTIL.setErrorEnabled(true);
                    bDescTIL.setError("Please enter a Description");
                }

                if (bDescTIET.getEditableText().length() != 0 && bNameTIET.getEditableText().length() != 0 && !brandName.contains(bNameTIET.getEditableText().toString())) {
                    bDescTIL.setError(null);
                    bNameTIL.setError(null);
                    brand.put("Name", bNameTIET.getEditableText().toString());
                    brand.put("Description", bDescTIET.getEditableText().toString());
                    db.collection("Brand")
                            .add(brand)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(BrandActivity.this, "Brand Added Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                    Toast.makeText(BrandActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
    }
}