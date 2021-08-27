package ssn.gokhul.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button insertBT;
    Button updateBT;
    Button deleteBT;
    Button retrieveIDBT;
    Button retrieveAllBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertBT = findViewById(R.id.insertBT);
        updateBT = findViewById(R.id.updateBT);
        deleteBT = findViewById(R.id.deleteBT);
        retrieveIDBT = findViewById(R.id.retrieveIDBT);
        retrieveAllBT = findViewById(R.id.retrieveAllBT);

        insertBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert = new Intent(MainActivity.this,InsertActivity.class);
                startActivity(insert);
            }
        });
        updateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent update = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(update);
            }
        });
        deleteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(MainActivity.this,DeleteActivity.class);
                startActivity(delete);
            }
        });
        retrieveIDBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retrieveID = new Intent(MainActivity.this,RetrieveIDActivity.class);
                startActivity(retrieveID);
            }
        });
        retrieveAllBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retrieveAll = new Intent(MainActivity.this,RetrieveAllActivity.class);
                startActivity(retrieveAll);
            }
        });
    }
}