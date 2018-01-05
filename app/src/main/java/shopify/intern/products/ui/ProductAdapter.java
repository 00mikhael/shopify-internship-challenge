package shopify.intern.products.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shopify.intern.products.R;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private int mRowLayout;
    private List<Product> mProducts;
    private ProductListener listener;

    public ProductAdapter(Context context, int mRowLayout, ProductListener listener) {
        this.mContext = context;
        mProducts = new ArrayList<>();
        this.mRowLayout = mRowLayout;
        this.listener = listener;
    }

    public List<Product> getManufacturers() {
        return mProducts;
    }

    public void add(List<Product> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ConstraintLayout container;
        private ImageView image;


        public ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.product_title);
            description = view.findViewById(R.id.product_description);
            image = view.findViewById(R.id.product_image);
            container = view.findViewById(R.id.product_container);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (mProducts != null) {
            Glide.with(mContext).load(mProducts.get(position).getImage().getSrc()).thumbnail(0.5f).into(holder.image);
            holder.title.setText(mProducts.get(position).getTitle());
            holder.description.setText(mProducts.get(position).getDescription());
        }
        holder.container.setOnClickListener(view -> listener.onProCardClicked(position));
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public interface ProductListener {
        void onProCardClicked(int position);
    }
}

