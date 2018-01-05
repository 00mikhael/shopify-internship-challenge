package shopify.intern.products.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */
@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    Flowable<List<Product>> getProductList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductList(List<Product> products);


}
