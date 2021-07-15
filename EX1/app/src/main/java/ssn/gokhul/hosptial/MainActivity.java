package ssn.gokhul.hosptial;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner martialSpinner;
    EditText nameET;
    EditText addressET;
    EditText ageET;
    DatePicker dobDP;
    RadioButton maleRB;
    RadioButton femaleRB;
    EditText contactET;
    TimePicker regTP;
    CheckBox alcoholCB;
    CheckBox smokingCB;
    Button submitBT;
    Button resetBT;
    UserData userdata;
    ArrayList<String> addiction;
    String martial;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        martialSpinner = findViewById(R.id.martialSpinner);
        nameET = findViewById(R.id.nameET);
        addressET = findViewById(R.id.addressET);
        ageET = findViewById(R.id.ageET);
        dobDP = findViewById(R.id.dobDP);
        maleRB = findViewById(R.id.maleRB);
        femaleRB = findViewById(R.id.femaleRB);
        contactET = findViewById(R.id.contactET);
        regTP = findViewById(R.id.regTP);
        alcoholCB = findViewById(R.id.alcoholCB);
        smokingCB = findViewById(R.id.smokingCB);
        submitBT = findViewById(R.id.submitBT);
        resetBT = findViewById(R.id.resetBT);

        addiction = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.martial_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        martialSpinner.setAdapter(adapter);
        martialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                martial = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitBT.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (nameET.getText().length()!=0&&addressET.getText().length()!=0&&
                        ageET.getText().length()!=0&&(maleRB.isChecked()||femaleRB.isChecked())&&
                        contactET.getText().length()==10) {
                    if (maleRB.isChecked()) {
                        gender = new String("male");
                    }
                    if (femaleRB.isChecked()) {
                        gender = new String("female");
                    }
                    if (alcoholCB.isChecked()) {
                        addiction.add("Alcohol");
                    }
                    if (smokingCB.isChecked()) {
                        addiction.add("Smoking");
                    }
                    userdata = new UserData(nameET.getText().toString(),
                            addressET.getText().toString(),
                            Integer.parseInt(ageET.getText().toString()),
                            dobDP.getDayOfMonth() + "-" + dobDP.getMonth() + "-" + dobDP.getYear(),
                            gender,
                            contactET.getText().toString(),
                            martial,
                            regTP.getHour() + ":" + regTP.getMinute(),
                            addiction);
//                    Log.d("bug", userdata.toString());
                    Intent data = new Intent(MainActivity.this,DataViewActivity.class);
                    data.putExtra("data",userdata);
                    startActivity(data);
                }else{
                    Toast.makeText(MainActivity.this, "Please fill all the details correctly!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET.setText("");
                addressET.setText("");
                ageET.setText("");
                maleRB.setChecked(false);
                femaleRB.setChecked(false);
                contactET.setText("");
                alcoholCB.setChecked(false);
                smokingCB.setChecked(false);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        nameET.setText("");
        addressET.setText("");
        ageET.setText("");
        maleRB.setChecked(false);
        femaleRB.setChecked(false);
        contactET.setText("");
        alcoholCB.setChecked(false);
        smokingCB.setChecked(false);
        addiction.clear();
    }
}