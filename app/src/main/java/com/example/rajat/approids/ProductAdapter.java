package com.example.rajat.approids;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajat on 28-Feb-18.
 */
class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_list,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.textViewTitle.setText(product.getTitle());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder  {

        TextView textViewTitle;
        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    Product product=productList.get(position);
                    Intent intent=new Intent(v.getContext(),Display.class);
                    intent.putExtra("description",product.getdesc());
                    v.getContext().startActivity(intent);
                }
            });
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        }


    }
    public  void  setFilter(ArrayList<Product> newList)
    {
     productList=new ArrayList<>();
        productList.addAll(newList);
        notifyDataSetChanged();
    }
}