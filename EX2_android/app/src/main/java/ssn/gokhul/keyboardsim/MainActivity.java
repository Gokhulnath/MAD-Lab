package ssn.gokhul.keyboardsim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView row1RV;
    RecyclerView row2RV;
    RecyclerView row3RV;
    KeyAdapter keyAdapterRow1;
    KeyAdapter keyAdapterRow2;
    KeyAdapter keyAdapterRow3;
    TextView inputTV;
    MaterialButton capsMBT;
    MaterialButton deleteMBT;
    MaterialButton enterMBT;
    Button numAlphaBT;
    Button numBT;
    Button commaBT;
    Button dotBT;
    Button spaceBT;
    int capsFlag;
    int numFlag;
    int numAltFlag;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        row1RV = findViewById(R.id.row1RV);
        row2RV = findViewById(R.id.row2RV);
        row3RV = findViewById(R.id.row3RV);
        inputTV = findViewById(R.id.inputTV);
        capsMBT = findViewById(R.id.capsMBT);
        deleteMBT = findViewById(R.id.deleteMBT);
        enterMBT = findViewById(R.id.enterMBT);
        numAlphaBT = findViewById(R.id.numAlphaBT);
        numBT = findViewById(R.id.numBT);
        commaBT = findViewById(R.id.commaBT);
        dotBT = findViewById(R.id.dotBT);
        spaceBT = findViewById(R.id.spaceBT);
        capsFlag=0;
        numFlag=0;
        numAltFlag=0;
        vibrator = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);

        keyAdapterRow1 = new KeyAdapter(Constants.smallAlphaRow1, MainActivity.this,inputTV,vibrator);
        keyAdapterRow2 = new KeyAdapter(Constants.smallAlphaRow2, MainActivity.this,inputTV,vibrator);
        keyAdapterRow3 = new KeyAdapter(Constants.smallAlphaRow3, MainActivity.this,inputTV,vibrator);
        LinearLayoutManager linearLayoutManagerRow1 = new LinearLayoutManager(MainActivity.this);
        linearLayoutManagerRow1.setOrientation(RecyclerView.HORIZONTAL);
        row1RV.setLayoutManager(linearLayoutManagerRow1);
        LinearLayoutManager linearLayoutManagerRow2 = new LinearLayoutManager(MainActivity.this);
        linearLayoutManagerRow2.setOrientation(RecyclerView.HORIZONTAL);
        row2RV.setLayoutManager(linearLayoutManagerRow2);
        LinearLayoutManager linearLayoutManagerRow3 = new LinearLayoutManager(MainActivity.this);
        linearLayoutManagerRow3.setOrientation(RecyclerView.HORIZONTAL);
        row3RV.setLayoutManager(linearLayoutManagerRow3);
        row1RV.setAdapter(keyAdapterRow1);
        row2RV.setAdapter(keyAdapterRow2);
        row3RV.setAdapter(keyAdapterRow3);

        deleteMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                String temp = inputTV.getText().toString();
                if(!temp.isEmpty())
                    inputTV.setText(temp.subSequence(0,temp.length()-1));
            }
        });

        deleteMBT.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText("");
                return false;
            }
        });

        capsMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if(capsFlag==0){
                    keyAdapterRow1.setKeys(Constants.capsAlphaRow1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.capsAlphaRow2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.capsAlphaRow3);
                    keyAdapterRow3.notifyDataSetChanged();
                    capsFlag=1;
                }else{
                    keyAdapterRow1.setKeys(Constants.smallAlphaRow1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.smallAlphaRow2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.smallAlphaRow3);
                    keyAdapterRow3.notifyDataSetChanged();
                    capsFlag=0;
                }
            }
        });

        numAlphaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if(numFlag==0){
                    keyAdapterRow1.setKeys(Constants.num1Row1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.num1Row2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.num1Row3);
                    keyAdapterRow3.notifyDataSetChanged();
                    numAlphaBT.setText("ABC");
                    numBT.setVisibility(View.VISIBLE);
                    capsMBT.setVisibility(View.GONE);
                    numFlag=1;
                }else{
                    keyAdapterRow1.setKeys(Constants.smallAlphaRow1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.smallAlphaRow2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.smallAlphaRow3);
                    keyAdapterRow3.notifyDataSetChanged();
                    numAlphaBT.setText("123");
                    numBT.setVisibility(View.GONE);
                    capsMBT.setVisibility(View.VISIBLE);
                    numFlag=0;
                    capsFlag=0;
                }
            }
        });
        numBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                if(numAltFlag==0){
                    keyAdapterRow1.setKeys(Constants.num2Row1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.num2Row2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.num2Row3);
                    keyAdapterRow3.notifyDataSetChanged();
                    numBT.setText("123");
                    numAltFlag=1;
                }else{
                    keyAdapterRow1.setKeys(Constants.num1Row1);
                    keyAdapterRow1.notifyDataSetChanged();
                    keyAdapterRow2.setKeys(Constants.num1Row2);
                    keyAdapterRow2.notifyDataSetChanged();
                    keyAdapterRow3.setKeys(Constants.num1Row3);
                    keyAdapterRow3.notifyDataSetChanged();
                    numBT.setText("=\\+");
                    numAltFlag=0;
                }
            }
        });

        commaBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText(inputTV.getText()+",");
            }
        });
        dotBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText(inputTV.getText()+".");
            }
        });
        spaceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText(inputTV.getText()+" ");
            }
        });

        enterMBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText(inputTV.getText()+"\n");
            }
        });
    }
}