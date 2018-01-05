package shopify.intern.products.data.database.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gravity on 1/4/18.
 */

public class ProductImage {

    @SerializedName("src")
    public String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
