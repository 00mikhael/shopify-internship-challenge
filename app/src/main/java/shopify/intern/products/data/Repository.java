package shopify.intern.products.data;

import android.util.Log;

import java.util.List;

import shopify.intern.products.AppConstants;
import shopify.intern.products.data.api.RemoteProductSource;
import shopify.intern.products.data.callbacks.DetailCallBacks;
import shopify.intern.products.data.callbacks.ProductCallBack;
import shopify.intern.products.data.database.AppDatabase;
import shopify.intern.products.data.database.LocalProductSource;
import shopify.intern.products.data.database.entities.Detail;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public class Repository implements RepositoryInterface {
    private static Repository INSTANCE;
    private LocalProductSource mLocalSource;
    private RemoteProductSource mRemoteSource;

    // creates one instance of this class
    public static Repository getInstance(AppDatabase database) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(database);
        }
        return INSTANCE;
    }

    private Repository(AppDatabase database) {
        mLocalSource = LocalProductSource.getInstance(database);
        mRemoteSource = RemoteProductSource.getInstance();
    }

    @Override
    public void getProductList(ProductCallBack.VmCallback callback) {

        new ProductSource(mLocalSource, mRemoteSource, AppConstants.FIELDS_PRODUCT,
                AppConstants.PAGE, AppConstants.LIMIT, AppConstants.TOKEN) {
            @Override
            protected void onResponse(Resource<List<Product>> resource) {
                callback.onResponse(resource);

                Log.e("PRODUCT LIST", "ON RESPONSE CALLED");
            }

            @Override
            protected void onErr(Resource<List<Product>> resource) {
                callback.onResponse(resource);
                Log.e("PRODUCT LIST", "ON ERROR CALLED");
            }

            @Override
            protected void onFinish() {

            }
        };

    }

    @Override
    public void getDetail(String id, DetailCallBacks.VmCallback callback) {
        new DetailSource(mLocalSource, mRemoteSource, id, AppConstants.FIELDS_DETAIL, AppConstants.TOKEN) {
            @Override
            protected void onResponse(Resource<Detail> resource) {
                callback.onResponse(resource);
            }

            @Override
            protected void onErr(Resource<Detail> resource) {
                callback.onResponse(resource);
            }

            @Override
            protected void onFinish() {

            }
        };
    }

    @Override
    public void saveProductList(List<Product> productList) {
        mLocalSource.saveProductList(productList);
    }

    @Override
    public void saveDetail(Detail detail) {
        mLocalSource.saveDetail(detail);
    }
}
