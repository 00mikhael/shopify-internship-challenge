package shopify.intern.products.data.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 1/4/18.
 */
@Entity(tableName = "product", indices = {@Index(value = {"id"}, unique = true)})
public class Product {
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    public long id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    public String title;

    @SerializedName("body_html")
    @ColumnInfo(name = "description")
    public String description;

    @Ignore
    @SerializedName("image")
    public ProductImage image;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    public Product() {}

    @Ignore
    public Product(@NonNull long id, String title, String description, ProductImage image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }
}
