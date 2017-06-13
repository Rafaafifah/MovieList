package id.sch.smktelkom_mlg.privateassigment.xirpl116.projectakhir;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Smktelkom on 5/16/2017.
 */

class MovieDBItem extends SugarRecord implements Serializable {
    public String imageUrl;
    public String title;
    public String year;
    public String jenis;
    public String desc;
    public int id;

    public MovieDBItem() {

    }

    public MovieDBItem(String imageUrl, String title, String year, String jenis, String desc) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.year = year;
        this.jenis = jenis;
        this.desc = desc;
    }
}
