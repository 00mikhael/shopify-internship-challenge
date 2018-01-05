package shopify.intern.products.ui.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import shopify.intern.products.AppConstants;
import shopify.intern.products.R;
import shopify.intern.products.data.Status;
import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/4/18.
 */

public class ProductDetailsActivity extends AppCompatActivity {

    private static final String PRO_ID = AppConstants.PRO_ID;
    private String mProId;

    private CoordinatorLayout mCoordinatorLayout;
    private ProductDetailsViewModel mViewModel;
    private Detail mDetail;
    private ImageView mImage;
    private Toolbar mToolbar;
    private TextView mTitle, mDescription, mVendor, mScope, mTags;

    @NonNull
    public static Intent createIntent(Context context, String proId) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        intent.putExtra(PRO_ID, proId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mViewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel.class);

        mProId = getIntent().getStringExtra(PRO_ID);

        if (mProId != null) {
            mViewModel.set_id(mProId);
        }

        // initializing fields
        mCoordinatorLayout = findViewById(R.id.details_coordinator);
        mImage = findViewById(R.id.details_product_image);
        mTitle = findViewById(R.id.details_product_title);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(AppConstants.EMPTY_STRING);
        mDescription = findViewById(R.id.details_product_description);
        mVendor = findViewById(R.id.details_product_vendor);
        mScope = findViewById(R.id.details_product_scope);
        mTags = findViewById(R.id.details_product_tags);

        // observe and show snackbar messages
        mViewModel.getVmSnackMessage().observe(this, s -> {
            if (s != null) {
                Snackbar snackbar = Snackbar.make(mCoordinatorLayout, s, Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        mViewModel.getVmDetail().observe(this, resource -> {
            if (resource.status.equals(Status.LOADING)) {
                if (resource.data != null) {
                    mDetail = resource.data;
                    onDetailResponse(mDetail);
                    mViewModel.setVmSnackMessage(getString(R.string.loading));
                }
            }else if (resource.status.equals(Status.SUCCESS)) {
                mDetail = resource.data;
                onDetailResponse(mDetail);
                mViewModel.setVmSnackMessage(getString(R.string.success));
            }else {
                //error
                mViewModel.setVmSnackMessage(getString(R.string.error));
            }
        });
    }

    private void onDetailResponse(Detail detail) {
        Glide.with(this).load(detail.getImage()).thumbnail(0.5f).into(mImage);
        mTitle.setText(detail.getTitle());
        mDescription.setText(detail.getDescription());
        mVendor.setText(getString(R.string.vendor) + detail.getVendor());
        mScope.setText(getString(R.string.scope) + detail.getScope());
        mTags.setText(getString(R.string.tags) + detail.getTags());
    }

}
