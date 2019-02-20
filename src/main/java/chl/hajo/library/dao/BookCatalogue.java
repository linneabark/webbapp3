package chl.hajo.library.dao;

import chl.hajo.library.core.Author;
import chl.hajo.library.core.Book;
import chl.hajo.library.service.DataSupplier;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 * This is an Data access object (DAO) used to access model objects
 *
 * It's transaction aware because it's an Enterprise Java Bean (EJB)
 * (thou we don't use a database for now, ... but used later)
 * Look at annotation. It a stateless session bean EJB (unique per session).
 *
 * @author hajo
 */
@Stateless
public class BookCatalogue {
    // Simulate database
    private final List<Book> books = DataSupplier.getBooks();

    public List<Book> findByName(String name) {

        return null;
    }

    public Book find(String id) {
        for( Book a : books){
            if( a.getIsbn().equals(id)){
                return a;
            }
        }
        return null;
    }

    public List<Book> findAll() {
        return books;
    }

    public void create(Book book) {
        books.add(book);
    }

    public void update(Book book) {
        delete(book.getIsbn());
        create(book);
    }

    public void delete(String id) {
        Book a = find(id);
        if( a != null){
            books.remove(a);
        }
    }

    public List <Book> findAllFromAuthor (Author author){
        List <Book> bookList = new ArrayList<>();
        for(int i = 0; i<books.size(); i++){
            if(books.get(i).getAuthor().equals(author)){
                bookList.add(books.get(i));
            }
        }
        return bookList;
    }
}
