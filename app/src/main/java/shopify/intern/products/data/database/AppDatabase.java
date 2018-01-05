package shopify.intern.products.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import shopify.intern.products.data.database.dao.DetailDao;
import shopify.intern.products.data.database.dao.ProductDao;
import shopify.intern.products.data.database.entities.Detail;
import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

@Database(entities = {Product.class, Detail.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase sInstance;

    public abstract ProductDao productDao();
    public abstract DetailDao detailDao();

    // Get a database instance
    public static synchronized AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = create(context);
        }
        return sInstance;
    }

    // Create the database
    private static AppDatabase create(Context context) {
        RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                "product-db");
        return builder
                .fallbackToDestructiveMigration()
                .build();
    }

}
