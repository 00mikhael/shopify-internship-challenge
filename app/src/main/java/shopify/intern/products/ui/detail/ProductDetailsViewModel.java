package shopify.intern.products.ui.detail;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import shopify.intern.products.AppConstants;
import shopify.intern.products.data.Repository;
import shopify.intern.products.data.Resource;
import shopify.intern.products.data.database.AppDatabase;
import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/5/18.
 */

public class ProductDetailsViewModel extends AndroidViewModel {
    private MutableLiveData<Resource<Detail>> productDetail = new MediatorLiveData<>();
    private String _id = AppConstants.EMPTY_STRING;
    private MutableLiveData<String> vmSnackMessage = new MutableLiveData<>();

    private Repository mRepository;

    public ProductDetailsViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        mRepository = Repository.getInstance(database);
    }

    public void set_id(String id) {
        this._id = id;
        getResponse();
    }

    public MutableLiveData<Resource<Detail>> getVmDetail() {
        return productDetail;
    }

    public void getResponse() {
        mRepository.getDetail(_id, resource -> productDetail.setValue(resource));
    }

    public void saveDetail(Detail detail) {
        mRepository.saveDetail(detail);
    }

    public MutableLiveData<String> getVmSnackMessage() {
        return vmSnackMessage;
    }

    public void setVmSnackMessage(String message) {
        vmSnackMessage.setValue(message);
    }
}
