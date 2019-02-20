package chl.hajo.library.control;

import chl.hajo.library.core.Author;
import chl.hajo.library.core.Book;
import chl.hajo.library.dao.AuthorRegistry;
import chl.hajo.library.dao.BookCatalogue;
import chl.hajo.library.service.DataSupplier;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import net.bootsfaces.utils.FacesMessages;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import net.bootsfaces.component.dataTable.DataTable;


/**
 * This is an CDI bean, look at annotations
 * https://docs.oracle.com/javaee/7/tutorial/cdi-basic.htm
 *
 * Also using library Lombok for setter/getters
 *
 * This is part of the control layer handling interaction between view
 * and model
 *
 * @author hajo
 */
@Named("book")
//@RequestScoped
@SessionScoped
public class BookBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuthorBean.class.getName());
    @EJB        // Injecting an Enterprise bean
    private BookCatalogue areg;
    @Getter     // Lombok
    @Setter
    private Book tmp = new Book();


    @PostConstruct // CDI life cycle callbacks
    void post() {
        out.println(this + "Alive");
    }

    public void page() {
        // Faces context hold all data relevant for this request
        DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("bookForm:bookTable");
        LOG.log(Level.INFO, "Test {0}", dt.getJQueryEvents());
    }
    // ------------ Navigation -------------------

    public void cancel() {
        tmp = new Book();
    }

    // --------- Call backend -------------------------
    public void setBook() {
        tmp = areg.find(tmp.getIsbn());
    }

    public List<Book> findAll() {
        return areg.findAll();
    }

    public List<Book> findAllFromAuthor(Author author){
        return areg.findAllFromAuthor(author);
    }

    public void add() {
        //tmp.setAddress(DataSupplier.getRandomAddress());
        try {
            areg.create(tmp);
            FacesMessages.info("Success");
        } catch (RuntimeException sql) {
            String message = sql.getMessage();
            FacesMessages.info("Fail " + message);
        }
        tmp = new Book();
    }

    public void update() {
        areg.update(tmp);
        tmp = new Book();
    }

    public void delete() {
        areg.delete(tmp.getIsbn());
        tmp = new Book();
    }

}
