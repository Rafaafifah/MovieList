package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import java.io.Serializable;

/**
 * Created by Smktelkom on 5/13/2017.
 */

class KomediItem implements Serializable {
    private String imageUrl;
    private String title;

    public KomediItem(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }
}
