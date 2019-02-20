package chl.hajo.library.core;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A book written by some author(s)
 * Core model class
 * @author hajo
 */
@NoArgsConstructor
@EqualsAndHashCode(of = {"isbn"})
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Setter
    @Getter
    private String isbn;
    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private Genre genre;
    @Setter
    @Getter
    private double price;
    @Setter
    @Getter
    private Author author;

    public Book(String isbn, String genre, int price, String title) {
        this.isbn = isbn;
        //this.genre = genre;
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title="
                + title + ", genre=" + genre
                + ", price=" + price + '}';
    }

}
