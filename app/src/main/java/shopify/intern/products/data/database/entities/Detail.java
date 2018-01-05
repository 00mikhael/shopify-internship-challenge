package shopify.intern.products.data.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 1/4/18.
 */
@Entity(tableName = "detail")
public class Detail {
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

    @SerializedName("vendor")
    @ColumnInfo(name = "vendor")
    public String vendor;

    @SerializedName("product_type")
    @ColumnInfo(name = "type")
    public String type;

    @SerializedName("published_scope")
    @ColumnInfo(name = "scope")
    public String scope;

    @SerializedName("tags")
    @ColumnInfo(name = "tags")
    public String tags;

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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Detail() {}

    @Ignore
    public Detail(@NonNull long id, String title, String description,
                  ProductImage image, String vendor, String type, String scope, String tags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.vendor = vendor;
        this.type = type;
        this.scope = scope;
        this.tags = tags;
    }
}
