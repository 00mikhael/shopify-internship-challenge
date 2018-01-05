package shopify.intern.products.data.api;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import shopify.intern.products.AppConstants;

/**
 * Created by gravity on 1/4/18.
 */

public class RetrofitClient {

    private static RetrofitClient instance;
    private RetrofitService apiService;

    private RetrofitClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // product list call
    public Observable<ProductResponse> getProductList(String fields, String page, String limit, String token) {
        return apiService.getProductsList(fields, page, limit, token);
    }

    // product detail call
    public Observable<DetailResponse> getDetail(String id, String fields, String token) {
        return apiService.getDetail(id, fields, token);
    }
}
