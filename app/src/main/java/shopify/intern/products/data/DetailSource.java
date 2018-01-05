package shopify.intern.products.data;

import android.support.annotation.NonNull;

import shopify.intern.products.data.api.RemoteProductSource;
import shopify.intern.products.data.callbacks.DetailCallBacks;
import shopify.intern.products.data.database.LocalProductSource;
import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/4/18.
 */

public abstract class DetailSource {

    private LocalProductSource mLocal;
    private RemoteProductSource mRemote;
    private DetailCallBacks.ApiCallback apiCallbak;
    private DetailCallBacks.DBCallback dbCallbak;

    DetailSource(@NonNull LocalProductSource local, @NonNull RemoteProductSource remote,
                  @NonNull String id, @NonNull String fields, @NonNull String token) {
        mLocal = local;
        mRemote = remote;

        // first sets data to null and state to loading
        onResponse(Resource.loading(null));

        // callback for db events
        dbCallbak = new DetailCallBacks.DBCallback() {
            @Override
            public void initialData(Detail detail) {
                onResponse(Resource.loading(detail));
                mRemote.getDetail(id, fields, token, apiCallbak);
            }

            @Override
            public void finalData(Detail detail) {
                mLocal.saveDetail(detail);
                onResponse(Resource.success(detail));
            }

            @Override
            public void dbError(Throwable t) {
                onErr(Resource.error(t.toString(), null));
                mRemote.getDetail(id, fields, token, apiCallbak);
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
        apiCallbak = new DetailCallBacks.ApiCallback() {
            @Override
            public void onApiResponse(Detail detail) {
                dbCallbak.finalData(detail);
            }

            @Override
            public void apiError(Throwable t) {
                dbCallbak.onApiError(t);
            }
        };
        mLocal.getDetail(id, dbCallbak);
    }

    protected abstract void onResponse(Resource<Detail> resource);

    protected abstract void onErr(Resource<Detail> resource);

    protected abstract void onFinish();
}
