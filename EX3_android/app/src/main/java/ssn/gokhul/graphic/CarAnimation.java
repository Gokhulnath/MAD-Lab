package ssn.gokhul.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class CarAnimation extends AppCompatActivity {

    ImageView shapeIV;
    Paint paint;
    Bitmap carBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_animation);

        shapeIV = findViewById(R.id.shapeIV);

        paint = new Paint();
        paint.setColor(Color.RED);

        carBM = Bitmap.createBitmap(900, 900, Bitmap.Config.ARGB_8888);
        shapeIV.setImageBitmap(carBM);

        Canvas carC = new Canvas(carBM);
        carC.drawRect(50, 50, 450, 200, paint);
        paint.setColor(Color.BLUE);
        carC.drawCircle(150, 200, 50, paint);
        carC.drawCircle(350, 200, 50, paint);
        ObjectAnimator animCar = ObjectAnimator.ofFloat(shapeIV, "x",  700);
        animCar.setDuration(1000);
        AnimatorSet animation = new AnimatorSet();
        animation.play(animCar).before(ObjectAnimator.ofFloat(shapeIV, "x", 0).setDuration(1000));
        animation.start();

    }
}