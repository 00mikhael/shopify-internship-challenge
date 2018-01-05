package shopify.intern.products.data.callbacks;

import shopify.intern.products.data.Resource;
import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/4/18.
 */

public interface DetailCallBacks {

    interface ApiCallback {
        void onApiResponse(Detail detail);

        void apiError(Throwable t);
    }

    interface DBCallback {
        void initialData(Detail detail);

        void finalData(Detail detail);

        void dbError(Throwable t);

        void onApiError(Throwable t);

        void onComplete();
    }

    interface VmCallback {
        void onResponse(Resource<Detail> resource);
    }
}
