package ssn.gokhul.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button shapeBT;
    Button gifBT;
    Button animateBT;
    Button carBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeBT = findViewById(R.id.shapeBT);
        gifBT = findViewById(R.id.gifBT);
        animateBT = findViewById(R.id.animateBT);
        carBT = findViewById(R.id.carBT);

        shapeBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shape = new Intent(MainActivity.this,Shapes.class);
                startActivity(shape);
            }
        });

        gifBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gif = new Intent(MainActivity.this,GIFAnimation.class);
                startActivity(gif);
            }
        });
        animateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent animate = new Intent(MainActivity.this, RotateZoom.class);
                startActivity(animate);
            }
        });

        carBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent car = new Intent(MainActivity.this, CarAnimation.class);
                startActivity(car);
            }
        });

    }
}