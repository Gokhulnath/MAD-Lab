package ssn.gokhul.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.List;
import java.util.Map;

import ssn.gokhul.product.custom.KeyPairBoolData;
import ssn.gokhul.product.custom.SingleSpinnerListener;
import ssn.gokhul.product.custom.SingleSpinnerSearch;

import static android.content.ContentValues.TAG;

public class InsertActivity extends AppCompatActivity {

    List<KeyPairBoolData> brandListArray;
    SingleSpinnerSearch brandSS;
    FirebaseFirestore db;
    TextInputLayout idTIL;
    TextInputLayout pNameTIL;
    TextInputLayout pDescTIL;
    TextInputLayout pPriceTIL;
    TextInputEditText idTIET;
    TextInputEditText pNameTIET;
    TextInputEditText pDescTIET;
    TextInputEditText pPriceTIET;
    TextView errorTV;
    Button addBT;
    Button cancelBT;
    String brandID;
    ArrayList<Integer> productIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        db = FirebaseFirestore.getInstance();

        brandSS = findViewById(R.id.brandSS);
        idTIL = findViewById(R.id.idTIL);
        pNameTIL = findViewById(R.id.pNameTIL);
        pDescTIL = findViewById(R.id.pDescTIL);
        pPriceTIL = findViewById(R.id.pPriceTIL);
        idTIET = findViewById(R.id.idTIET);
        pNameTIET = findViewById(R.id.pNameTIET);
        pDescTIET = findViewById(R.id.pDescTIET);
        pPriceTIET = findViewById(R.id.pPriceTIET);
        errorTV = findViewById(R.id.errorTV);
        addBT = findViewById(R.id.addBT);
        cancelBT = findViewById(R.id.cancelBT);
        brandID=null;

        Map<String, Object> product = new HashMap<>();

        brandListArray = new ArrayList<>();
        brandSS.setColorseparation(false);
        brandSS.setSearchEnabled(true);
        db.collection("Brand")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("bug", document.getId() + " => " + document.getData());
                                KeyPairBoolData keyPairBoolData = new KeyPairBoolData();
                                keyPairBoolData.setIds(document.getId());
                                keyPairBoolData.setName(document.getData().get("Name").toString());
                                brandListArray.add(keyPairBoolData);
                            }
                        } else {
                            Log.w("bug", "Error getting documents.", task.getException());
                        }
                    }
                });

        brandSS.setItems(brandListArray, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.d("debug", "Selected Item : " + selectedItem.getIds());
                brandID=selectedItem.getIds();
            }

            @Override
            public void onClear() {
                brandID=null;
            }
        });

        productIdList = new ArrayList<>();
        db.collection("Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("bug", document.getId() + " => " + document.getData());
                                productIdList.add(Integer.valueOf(document.getData().get("id").toString()));
                            }
                        } else {
                            Log.w("bug", "Error getting documents.", task.getException());
                        }
                    }
                });

        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = idTIET.getEditableText().toString().length()==0 ? 0:Integer.valueOf(idTIET.getEditableText().toString());
                String name = pNameTIET.getEditableText().toString();
                String desc = pDescTIET.getEditableText().toString();
                float price = pPriceTIET.getEditableText().toString().length()==0 ? 0:Float.valueOf(pPriceTIET.getEditableText().toString());

                if(id>999 && id<10000 && !productIdList.contains(id) && name.length()!=0 && desc.length()!=0 && price!=0 && brandID!=null){
                    product.put("id",id);
                    product.put("Name",name);
                    product.put("Description",desc);
                    product.put("Price",price);
                    product.put("Brand",db.document("Brand/" + brandID));
                    db.collection("Product")
                            .add(product)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(InsertActivity.this, "Product Added Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }else{
                    if(id<=999 || id>=10000 || productIdList.contains(id)){
                        idTIL.setError("Please enter a valid or non-existence ID!");
                    }else{
                        idTIL.setError(null);
                    }
                    if(name.length()==0){
                        pNameTIL.setError("Please enter a valid Product Name!");
                    }else{
                        pNameTIL.setError(null);
                    }
                    if(desc.length()==0){
                        pDescTIL.setError("Please enter a valid Product Description!");
                    }else{
                        pDescTIL.setError(null);
                    }
                    if(price<=0){
                        pPriceTIL.setError("Please enter a valid Product Price!");
                    }else{
                        pPriceTIL.setError(null);
                    }
                    if(brandID==null){
                        errorTV.setVisibility(View.VISIBLE);
                    }else{
                        errorTV.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        db = FirebaseFirestore.getInstance();
        brandListArray.clear();
        db.collection("Brand")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("bug", document.getId() + " => " + document.getData());
                                KeyPairBoolData keyPairBoolData = new KeyPairBoolData();
                                keyPairBoolData.setIds(document.getId());
                                keyPairBoolData.setName(document.getData().get("Name").toString());
                                brandListArray.add(keyPairBoolData);
                            }
                        } else {
                            Log.w("bug", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}