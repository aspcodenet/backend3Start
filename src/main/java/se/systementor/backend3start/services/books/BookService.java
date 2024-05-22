package se.systementor.backend3start.services.books;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.backend3start.demos.Catalog;
import se.systementor.backend3start.demos.book;
import se.systementor.backend3start.model.Book;
import se.systementor.backend3start.model.BookRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;


// UNITTESTER ska vara bloxtsnabba ! Bara finnas en anledning till att faila!
// 3000 unittester . som körs på kanske 3 sekunder

@Service
public class BookService {


    @Autowired
    BookService(XmlStreamProvider xmlStreamProvider, BookRepository bookRepository){
        this.xmlStreamProvider = xmlStreamProvider;
        this.bookRepository = bookRepository;
    }


    final XmlStreamProvider xmlStreamProvider;
    public BookRepository bookRepository;


    public List<book> GetBooks() throws IOException {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        InputStream stream =  xmlStreamProvider.getDataStream();
        Catalog theBooks = xmlMapper.readValue(stream,
                Catalog.class
        );

        return theBooks.books;
    }

    // CLASS (UNIT)
    // som en dator
    // knappar PUBLIC
    // testa bara PUBLIC -> vi testar aldrig INTERNAL DETAILS

    protected void MapBook(Book theBook, book fromBook){
        theBook.setAuthor(fromBook.author);
        theBook.setExternalSystemId(fromBook.id);
        theBook.setTitle(fromBook.title);
        theBook.setCategory(fromBook.category);
        theBook.setPrice(fromBook.price);
        theBook.setExternalSystemId(fromBook.id);
        theBook.setDescription(fromBook.description);
        theBook.setPublishDate(fromBook.publishDate);
    }

    protected void SaveBook(Book theBook){
        bookRepository.save(theBook);
    }

    public void FetchAndSaveBooks() throws IOException {
        for(book b : GetBooks()){

            Book theBook;
            Optional<Book> fromDatabase = bookRepository.findByExternalSystemId(b.id);
            if(fromDatabase.isPresent()){
                theBook = fromDatabase.get();
            } else{
                theBook = new Book();
            }

            MapBook(theBook,b);

            SaveBook(theBook);
        }

    }

}
