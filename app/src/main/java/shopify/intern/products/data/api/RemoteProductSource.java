package shopify.intern.products.data.api;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import shopify.intern.products.data.callbacks.DetailCallBacks;
import shopify.intern.products.data.callbacks.ProductCallBack;
import shopify.intern.products.data.database.entities.Detail;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public class RemoteProductSource {

    private static RemoteProductSource INSTANCE;

    public static RemoteProductSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteProductSource();
        }
        return INSTANCE;
    }

    private RemoteProductSource() {
    }

    public void getProductList(String fields, String page, String limit, String token,
                               ProductCallBack.ApiCallbak callback) {
        RetrofitClient.getInstance()
                .getProductList(fields, page, limit, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProductResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProductResponse productResponse) {
                        List<Product> products = productResponse.getProducts();
                        callback.onApiResponse(products);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.apiError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getDetail(String id, String fields, String token, DetailCallBacks.ApiCallback callback) {
        RetrofitClient.getInstance()
                .getDetail(id, fields, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailResponse detailResponse) {
                        Detail detail = detailResponse.getDetail();
                        callback.onApiResponse(detail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.apiError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
