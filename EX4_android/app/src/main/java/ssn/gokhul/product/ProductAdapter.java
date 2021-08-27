package ssn.gokhul.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductNameHolder> {
    ArrayList<Map<String,Object>> productResponseList;
    Context context;

    public ProductAdapter(ArrayList<Map<String, Object>> productResponseList, Context context) {
        this.productResponseList = productResponseList;
        this.context = context;
    }

    public ArrayList<Map<String, Object>> getProductResponseList() {
        return productResponseList;
    }

    public void setProductResponseList(ArrayList<Map<String, Object>> productResponseList) {
        this.productResponseList = productResponseList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductNameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productlistview_single,parent,false);
        ProductNameHolder productNameHolder = new ProductNameHolder(v);
        return productNameHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductNameHolder holder, int position) {
        final Map<String, Object> product = (Map<String, Object>) productResponseList.get(position);
        holder.idTV.setText(product.get("id").toString());
        holder.nameTV.setText(product.get("Name").toString());
        holder.descTV.setText(product.get("Description").toString());
        holder.brandTV.setText(product.get("Brand").toString());
        holder.priceTV.setText(product.get("Price").toString());
    }

    @Override
    public int getItemCount() {
        return productResponseList.size();
    }

    public class ProductNameHolder extends RecyclerView.ViewHolder{
        TextView idTV;
        TextView nameTV;
        TextView descTV;
        TextView brandTV;
        TextView priceTV;

        public ProductNameHolder(@NonNull View itemView){
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            nameTV = itemView.findViewById(R.id.nameTV);
            descTV = itemView.findViewById(R.id.descTV);
            brandTV = itemView.findViewById(R.id.brandTV);
            priceTV = itemView.findViewById(R.id.priceTV);
        }
    }
}
