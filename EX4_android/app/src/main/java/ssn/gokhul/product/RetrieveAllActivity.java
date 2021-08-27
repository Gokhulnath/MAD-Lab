package ssn.gokhul.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ssn.gokhul.product.custom.KeyPairBoolData;
import ssn.gokhul.product.custom.SingleSpinnerListener;
import ssn.gokhul.product.custom.SingleSpinnerSearch;

public class RetrieveAllActivity extends AppCompatActivity {

    List<KeyPairBoolData> brandListArray;
    SingleSpinnerSearch brandSS;
    FirebaseFirestore db;
    String brandID;
    Button cancelBT;
    RecyclerView productRV;
    ArrayList<Map<String,Object>> productlist;
    ProductAdapter productAdapter;
    TextView errorTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_all);

        db = FirebaseFirestore.getInstance();

        brandSS = findViewById(R.id.brandSS);
        cancelBT = findViewById(R.id.cancelBT);
        productRV = findViewById(R.id.productRV);
        errorTV = findViewById(R.id.errorTV);
        brandID=null;

        productAdapter = new ProductAdapter(new ArrayList<Map<String, Object>>(),this);
        productlist = new ArrayList<Map<String, Object>>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        productRV.setLayoutManager(linearLayoutManager);
        productRV.setAdapter(productAdapter);

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
                                keyPairBoolData.setIds(document.getReference().toString());
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
                db.collection("Product")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    productlist.clear();
                                    productAdapter.setProductResponseList(productlist);
                                    productAdapter.notifyDataSetChanged();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d("bug", document.getId() + " => " + document.getData());

                                        if(brandID.equals(document.getData().get("Brand").toString())){
                                            Map<String,Object> temp = (Map<String,Object>) document.getData();
                                            temp.put("Brand",selectedItem.getName());
                                            Log.d("fuck", temp.toString());
                                            productlist.add(temp);
                                        }
                                    }
                                    if(productlist.size()==0){
                                        errorTV.setVisibility(View.VISIBLE);
                                        productRV.setVisibility(View.GONE);
                                    }else{
                                        errorTV.setVisibility(View.GONE);
                                        productRV.setVisibility(View.VISIBLE);
                                    }
                                    productAdapter.setProductResponseList(productlist);
                                    productAdapter.notifyDataSetChanged();
                                } else {
                                    Log.w("bug", "Error getting documents.", task.getException());
                                }
                            }
                        });
            }

            @Override
            public void onClear() {
                brandID=null;
                productlist.clear();
                productAdapter.setProductResponseList(productlist);
                productAdapter.notifyDataSetChanged();
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