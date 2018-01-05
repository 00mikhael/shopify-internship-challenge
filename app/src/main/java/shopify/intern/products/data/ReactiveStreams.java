package shopify.intern.products.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;

import io.reactivex.Flowable;

/**
 * Created by gravity on 1/4/18.
 */

// converts RxJava Flowables to LiveData
public class ReactiveStreams<T> {
    public LiveData<T> toLiveData(Flowable<T> responseFlowable) {
        return LiveDataReactiveStreams.fromPublisher(responseFlowable);
    }
}
