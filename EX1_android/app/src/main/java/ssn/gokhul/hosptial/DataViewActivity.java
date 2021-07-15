package ssn.gokhul.hosptial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataViewActivity extends AppCompatActivity {

    TextView nameTV;
    TextView addressTV;
    TextView ageTV;
    TextView dobTV;
    TextView genderTV;
    TextView martialTV;
    TextView contactTV;
    TextView regTV;
    TextView addictionTV;
    Button backBT;
    UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);
        nameTV=findViewById(R.id.nameTV);
        addressTV=findViewById(R.id.addressTV);
        ageTV=findViewById(R.id.ageTV);
        dobTV=findViewById(R.id.dobTV);
        genderTV=findViewById(R.id.genderTV);
        martialTV=findViewById(R.id.martialTV);
        contactTV=findViewById(R.id.contactTV);
        regTV=findViewById(R.id.regTV);
        addictionTV=findViewById(R.id.addictionTV);
        backBT=findViewById(R.id.backBT);

        Intent data = getIntent();
        userdata = (UserData) data.getSerializableExtra("data");
        nameTV.setText(userdata.getName());
        addressTV.setText(userdata.getAddress());
        ageTV.setText(String.valueOf(userdata.getAge()));
        dobTV.setText(userdata.getDob());
        genderTV.setText(userdata.getGender());
        martialTV.setText(userdata.getMartial());
        contactTV.setText(userdata.getContact());
        regTV.setText(userdata.getReg());
        if(userdata.getAddiction().size()==0){
            addictionTV.setText("None");
        }else {
            String addiction="";
            for(String i:userdata.getAddiction()){
                addiction+=i+", ";
            }
            addictionTV.setText(addiction);
        }
        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}