package shopify.intern.products.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/4/18.
 */
@Dao
public interface DetailDao {

    @Query("SELECT * FROM detail WHERE id LIKE :id")
    Flowable<Detail> getDetail(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDetail(Detail detail);

}
