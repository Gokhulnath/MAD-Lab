package ssn.gokhul.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class RotateZoom extends AppCompatActivity {

    ImageView wheelIV;
    ImageView zoomIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate_zoom);

        wheelIV = findViewById(R.id.wheelIV);
        zoomIV = findViewById(R.id.zoomIV);

        Animation Rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        wheelIV.startAnimation(Rotate);
        Animation zoom = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom);
        zoomIV.startAnimation(zoom);
    }
}