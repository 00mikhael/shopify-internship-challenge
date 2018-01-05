package shopify.intern.products.data.callbacks;

import java.util.List;

import shopify.intern.products.data.Resource;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public interface ProductCallBack {

    interface ApiCallbak {
        void onApiResponse(List<Product> productList);

        void apiError(Throwable t);
    }

    interface DBCallbak {
        void initialData(List<Product> productList);

        void finalData(List<Product> productList);

        void dbError(Throwable t);

        void onApiError(Throwable t);

        void onComplete();
    }

    interface VmCallback {
        void onResponse(Resource<List<Product>> resource);
    }
}
