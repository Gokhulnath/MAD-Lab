package ssn.gokhul.product;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ssn.gokhul.product.custom.KeyPairBoolData;
import ssn.gokhul.product.custom.SingleSpinnerListener;
import ssn.gokhul.product.custom.SingleSpinnerSearch;

public class RetrieveIDActivity extends AppCompatActivity {
    List<KeyPairBoolData> productListArray;
    Map<String, String> brandListArray;
    SingleSpinnerSearch productSS;
    FirebaseFirestore db;
    TextInputLayout pBrandTIL;
    TextInputLayout pNameTIL;
    TextInputLayout pDescTIL;
    TextInputLayout pPriceTIL;
    TextInputEditText pBrandTIET;
    TextInputEditText pNameTIET;
    TextInputEditText pDescTIET;
    TextInputEditText pPriceTIET;
    TextView errorTV;
    Button cancelBT;
    int flag;
    String productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_idactivity);
        db = FirebaseFirestore.getInstance();

        productSS = findViewById(R.id.productSS);
        pBrandTIL = findViewById(R.id.pBrandTIL);
        pNameTIL = findViewById(R.id.pNameTIL);
        pDescTIL = findViewById(R.id.pDescTIL);
        pPriceTIL = findViewById(R.id.pPriceTIL);
        pBrandTIET = findViewById(R.id.pBrandTIET);
        pNameTIET = findViewById(R.id.pNameTIET);
        pDescTIET = findViewById(R.id.pDescTIET);
        pPriceTIET = findViewById(R.id.pPriceTIET);
        errorTV = findViewById(R.id.errorTV);
        cancelBT = findViewById(R.id.cancelBT);
        flag = 0;
        productID = null;
        productListArray = new ArrayList<>();
        productSS.setColorseparation(false);
        productSS.setSearchEnabled(true);
        db.collection("Product")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("bug", document.getId() + " => " + document.getData());
                                KeyPairBoolData keyPairBoolData = new KeyPairBoolData();
                                keyPairBoolData.setIds(document.getId());
                                keyPairBoolData.setName(document.getData().get("id").toString());
                                keyPairBoolData.setObject(document.getData());
                                productListArray.add(keyPairBoolData);
                            }
                        } else {
                            Log.w("bug", "Error getting documents.", task.getException());
                        }
                    }
                });


        productSS.setItems(productListArray, new SingleSpinnerListener() {
            @Override
            public void onItemsSelected(KeyPairBoolData selectedItem) {
                Log.d("debug", "Selected Item : " + selectedItem.getName());
                Map<String, Object> product = (Map<String, Object>) selectedItem.getObject();
                pNameTIET.setText(product.get("Name").toString());
                pDescTIET.setText(product.get("Description").toString());
                pPriceTIET.setText(product.get("Price").toString());
                productID = selectedItem.getIds();
                db.collection("Brand")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    flag = 1;
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("bug", document.getId() + " => " + document.getData() + " " + document.getReference());
                                        if (document.getReference().equals(product.get("Brand"))) {
                                            pBrandTIET.setText(document.getData().get("Name").toString());
                                        }
                                    }
                                } else {
                                    Log.w("bug", "Error getting documents.", task.getException());
                                    flag = 0;
                                }
                            }
                        });

            }

            @Override
            public void onClear() {
                flag = 0;
                pNameTIET.setText(null);
                pBrandTIET.setText(null);
                pDescTIET.setText(null);
                pPriceTIET.setText(null);
            }
        });

        cancelBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}