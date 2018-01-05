package shopify.intern.products.data;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import shopify.intern.products.data.api.RemoteProductSource;
import shopify.intern.products.data.callbacks.ProductCallBack;
import shopify.intern.products.data.database.LocalProductSource;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public abstract class ProductSource {

    private LocalProductSource mLocal;
    private RemoteProductSource mRemote;
    private ProductCallBack.ApiCallbak apiCallbak;
    private ProductCallBack.DBCallbak dbCallbak;

    ProductSource(@NonNull LocalProductSource local, @NonNull RemoteProductSource remote,
               @NonNull String fields,@NonNull String page,@NonNull String limit,@NonNull String token) {
        mLocal = local;
        mRemote = remote;

        // first sets data to null and state to loading
        onResponse(Resource.loading(null));

        // callback for db events
        dbCallbak = new ProductCallBack.DBCallbak() {
            @Override
            public void initialData(List<Product> productList) {
                onResponse(Resource.loading(productList));
                Log.e("PRODUCT STORE", ""+ fields + page + limit + token);
                mRemote.getProductList(fields, page, limit, token, apiCallbak);
            }

            @Override
            public void finalData(List<Product> productList) {
                mLocal.saveProductList(productList);
                onResponse(Resource.success(productList));
            }

            @Override
            public void dbError(Throwable t) {
                onErr(Resource.error(t.toString(), null));
                mRemote.getProductList(fields, page, limit, token, apiCallbak);
            }

            @Override
            public void onApiError(Throwable t) {
                onErr(Resource.error(t.toString(), null));
            }

            @Override
            public void onComplete() {
                onFinish();
            }
        };

        // callback for network events
        apiCallbak = new ProductCallBack.ApiCallbak() {
            @Override
            public void onApiResponse(List<Product> productList) {
                Log.e("ON API RESPONSE", productList.toString());
                dbCallbak.finalData(productList);
            }

            @Override
            public void apiError(Throwable t) {
                dbCallbak.onApiError(t);
            }
        };
        mLocal.getProductList(dbCallbak);
    }

    protected abstract void onResponse(Resource<List<Product>> resource);

    protected abstract void onErr(Resource<List<Product>> resource);

    protected abstract void onFinish();
}
