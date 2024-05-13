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

    public void FetchAndSaveBooks() throws IOException {
        for(book b : GetBooks()){
            Optional<Book> theBook = bookRepository.findByExternalSystemId(b.id);
            if(theBook.isEmpty()){
                theBook = Optional.of(new Book());
            }
            theBook.get().setAuthor(b.author);
            theBook.get().setExternalSystemId(b.id);
            theBook.get().setTitle(b.title);
            theBook.get().setCategory(b.category);
            theBook.get().setPrice(b.price);
            theBook.get().setExternalSystemId(b.id);
            theBook.get().setDescription(b.description);
            theBook.get().setPublishDate(b.publishDate);
            bookRepository.save(theBook.get());
        }

    }

}
