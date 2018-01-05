package shopify.intern.products.data.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gravity on 1/4/18.
 */

public interface RetrofitService {
    @GET("/admin/product.json")
    Observable<ProductResponse> getProductsList(@Query(value = "fields", encoded = true) String fields, @Query(value = "page", encoded = true) String page,
                                                @Query(value = "limit", encoded = true) String limit, @Query(value = "access_token", encoded = true) String token);

    @GET("/admin/products/{id}.json")
    Observable<DetailResponse> getDetail(@Path("id") String id, @Query(value = "fields", encoded = true) String fields, @Query(value = "access_token", encoded = true) String token);
}
