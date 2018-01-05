package shopify.intern.products.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static shopify.intern.products.data.Status.ERROR;
import static shopify.intern.products.data.Status.LOADING;
import static shopify.intern.products.data.Status.SUCCESS;

/**
 * Created by gravity on 1/4/18.
 */

// wrapper class for passing data along with states
public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
