package ponkberry.hoandemo.bean;

import java.io.Serializable;

/**
 * Created by Ponk on 2/15/2017.
 */

public class Book implements Serializable {
    // Serializable can convert objects into packages, which packages can be accepted by the Bundle

    private String name;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
