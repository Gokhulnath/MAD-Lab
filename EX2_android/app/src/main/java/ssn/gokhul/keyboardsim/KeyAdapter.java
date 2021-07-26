package ssn.gokhul.keyboardsim;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.KeyHolder> {

    ArrayList<String> keys;
    Context context;
    TextView inputTV;
    Vibrator vibrator;

    public KeyAdapter(ArrayList<String> keys, Context context, TextView inputTV, Vibrator vibrator) {
        this.keys = keys;
        this.context = context;
        this.inputTV = inputTV;
        this.vibrator = vibrator;
    }

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public TextView getInputTV() {
        return inputTV;
    }

    public void setInputTV(TextView inputTV) {
        this.inputTV = inputTV;
    }

    public Vibrator getVibrator() {
        return vibrator;
    }

    public void setVibrator(Vibrator vibrator) {
        this.vibrator = vibrator;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public KeyHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_item,parent,false);
        KeyHolder keyHolder = new KeyHolder(v);
        return keyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull KeyAdapter.KeyHolder holder, int position) {
        String keyData = keys.get(position);
        holder.keyBT.setText(keyData);
        holder.keyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibrator.vibrate(10);
                inputTV.setText(inputTV.getText()+keyData);
            }
        });

    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    public class KeyHolder extends RecyclerView.ViewHolder{
        Button keyBT;
        public KeyHolder(@NonNull View itemView) {
            super(itemView);
            keyBT=itemView.findViewById(R.id.keyBT);
        }
    }
}
