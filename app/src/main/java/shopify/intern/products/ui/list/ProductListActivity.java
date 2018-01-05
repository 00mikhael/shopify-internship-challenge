package shopify.intern.products.ui.list;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import java.util.List;

import shopify.intern.products.AppConstants;
import shopify.intern.products.R;
import shopify.intern.products.data.Status;
import shopify.intern.products.data.database.entities.Product;
import shopify.intern.products.ui.GridSpacingItemDecoration;
import shopify.intern.products.ui.ProductAdapter;
import shopify.intern.products.ui.detail.ProductDetailsActivity;

public class ProductListActivity extends AppCompatActivity implements ProductAdapter.ProductListener {

    // private fields declaration
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;
    private ProductAdapter mAdapter;
    private List<Product> mProductList;
    private Toolbar mToolbar;
    private ProductListViewModel mViewModel;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // creating an instance of ConversionListViewModel from ViewModelProviders
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);

        // initializing fields
        mCoordinatorLayout = findViewById(R.id.list_coordinator);
        mRecyclerView = findViewById(R.id.products_recycler);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(AppConstants.EMPTY_STRING);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(dpToPx(this)));
        mAdapter = new ProductAdapter(this, R.layout.item_product, this);
        mRecyclerView.setAdapter(mAdapter);

        // observe and show snackbar messages
        mViewModel.getVmSnackMessage().observe(this, s -> {
            if (s != null) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, s, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Observe product list
        mViewModel.getVmProductList().observe(this, resource -> {
            if (resource.status.equals(Status.LOADING)) {
                if (resource.data != null) {
                    mProductList = resource.data;
                    onProductListResponse(mProductList);
                    mViewModel.setVmSnackMessage(getString(R.string.loading));
                }
            }else if (resource.status.equals(Status.SUCCESS)) {
                mProductList = resource.data;
                onProductListResponse(mProductList);
                mViewModel.setVmSnackMessage(getString(R.string.success));
            }else {
                //error
                mViewModel.setVmSnackMessage(getString(R.string.error));
            }
        });
    }

    private void onProductListResponse(List<Product> productList) {
        mAdapter.add(productList);
    }

    @Override
    public void onProCardClicked(int position) {
        Product product = mProductList.get(position);
        long id = product.getId();
        String pro_id = String.valueOf(id);
        startActivity(ProductDetailsActivity.createIntent(this, pro_id));
    }

    public static int dpToPx(@NonNull Context context) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics()));
    }
}
