package shopify.intern.products.data.database;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import shopify.intern.products.data.callbacks.DetailCallBacks;
import shopify.intern.products.data.callbacks.ProductCallBack;
import shopify.intern.products.data.database.dao.DetailDao;
import shopify.intern.products.data.database.dao.ProductDao;
import shopify.intern.products.data.database.entities.Detail;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public class LocalProductSource {
    private static LocalProductSource INSTANCE;

    private ProductDao mProductDao;
    private DetailDao mDetailDao;

    public static LocalProductSource getInstance(AppDatabase database) {
        if (INSTANCE == null) {
            INSTANCE = new LocalProductSource(database);
        }
        return INSTANCE;
    }

    private LocalProductSource(AppDatabase database) {
        mProductDao = database.productDao();
        mDetailDao = database.detailDao();
    }

    public void saveDetail(Detail detail) {
        Single.just(detail)
                .subscribeOn(Schedulers.io())
                .subscribe((Detail detail1) -> mDetailDao.insertDetail(detail1));

    }

    public void saveProductList(List<Product> products) {
        Single.just(products)
                .subscribeOn(Schedulers.io())
                .subscribe((List<Product> productList) -> mProductDao.insertProductList(productList));

    }

    public void getProductList(ProductCallBack.DBCallbak callBack) {
        mProductDao.getProductList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Product> productList) {
                        callBack.initialData(productList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.dbError(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }

    public void getDetail(String id, DetailCallBacks.DBCallback callBack) {
        mDetailDao.getDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .subscribe(new Observer<Detail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Detail detail) {
                        callBack.initialData(detail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.dbError(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });
    }
}
