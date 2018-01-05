package shopify.intern.products.ui.list;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import shopify.intern.products.data.Repository;
import shopify.intern.products.data.Resource;
import shopify.intern.products.data.database.AppDatabase;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/5/18.
 */

public class ProductListViewModel extends AndroidViewModel {
    private MutableLiveData<Resource<List<Product>>> productList = new MutableLiveData<>();
    private MutableLiveData<String> vmSnackMessage = new MutableLiveData<>();

    private Repository mRepository;

    public ProductListViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(application);
        mRepository = Repository.getInstance(database);
        getResponse();
    }

    public MutableLiveData<Resource<List<Product>>> getVmProductList() {
        return productList;
    }

    public void getResponse() {
        mRepository.getProductList(resource -> productList.setValue(resource));
    }

    public void saveProductList(List<Product> productList) {
        mRepository.saveProductList(productList);
    }

    public MutableLiveData<String> getVmSnackMessage() {
        return vmSnackMessage;
    }

    public void setVmSnackMessage(String message) {
        vmSnackMessage.setValue(message);
    }

}
