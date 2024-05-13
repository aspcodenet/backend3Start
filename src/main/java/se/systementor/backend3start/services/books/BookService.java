package se.systementor.backend3start.services.books;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.backend3start.demos.Catalog;
import se.systementor.backend3start.demos.book;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


// UNITTESTER ska vara bloxtsnabba ! Bara finnas en anledning till att faila!
// 3000 unittester . som körs på kanske 3 sekunder

@Service
public class BookService {


    @Autowired
    BookService(XmlStreamProvider xmlStreamProvider){
        this.xmlStreamProvider = xmlStreamProvider;
    }


    XmlStreamProvider xmlStreamProvider;


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

}
