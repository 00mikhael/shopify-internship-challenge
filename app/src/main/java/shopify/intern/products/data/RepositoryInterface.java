package shopify.intern.products.data;

import java.util.List;

import shopify.intern.products.data.callbacks.DetailCallBacks;
import shopify.intern.products.data.callbacks.ProductCallBack;
import shopify.intern.products.data.database.entities.Detail;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public interface RepositoryInterface {

    void getProductList(ProductCallBack.VmCallback callback);

    void getDetail(String id, DetailCallBacks.VmCallback callback);

    void saveProductList(List<Product> productList);

    void saveDetail(Detail detail);

}
