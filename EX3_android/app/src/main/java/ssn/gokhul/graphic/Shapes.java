package ssn.gokhul.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class Shapes extends AppCompatActivity {

    Bitmap bg;
    ImageView shapeIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes);
        shapeIV = findViewById(R.id.shapeIV);
        bg=Bitmap.createBitmap(1080,1920,Bitmap.Config.ARGB_8888);
        shapeIV.setImageBitmap(bg);
        Canvas canvas = new Canvas(bg);
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setTextSize(50);

        canvas.drawText("Rectangle",720,350,paint);
        canvas.drawRect(700,400,950,900,paint);

        canvas.drawText("Circle",220,350,paint);
        canvas.drawCircle(300,550,150,paint);

        canvas.drawText("Square",220,1100,paint);
        canvas.drawRect(150,1150,450,1450,paint);

        canvas.drawText("Line",780,1100,paint);
        canvas.drawLine(820,1150,820,1450,paint);
    }
}