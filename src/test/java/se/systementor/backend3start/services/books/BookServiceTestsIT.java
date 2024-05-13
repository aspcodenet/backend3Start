// https://dzone.com/articles/splitting-unit-and-integration-tests-using-maven-a

package se.systementor.backend3start.services.books;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.systementor.backend3start.model.Book;
import se.systementor.backend3start.model.BookRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;


//INTEGRATIONSTESTER - skulle kunna köras VARJE NATT och eller i s.k CI/CD - innan DEPLOY av ny version
// externa saker - om dom byter author till <TheAuthor>
// Integrationstester som failar på natten -> SKICKA MAIL TILL ALLA UTVECKLARE
// LAGA OM FEL !!!!


@SpringBootTest
class BookServiceTestsIT {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    XmlStreamProvider xmlStreamProvider;

    BookService sut;

    @Test
    void getBooksWillFetch() throws IOException {
        sut = new BookService(xmlStreamProvider,bookRepository);
        Scanner s = new Scanner(sut.xmlStreamProvider.getDataStream()).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        assertTrue(  result.contains("<catalog>") );
        assertTrue(  result.contains("</catalog>") );
        assertTrue(  result.contains("<author>") );
        assertTrue(  result.contains("</author>") );
        assertTrue(  result.contains("<title>") );
        assertTrue(  result.contains("</title>") );
        assertTrue(  result.contains("<genre>") );
        assertTrue(  result.contains("</genre>") );
        assertTrue(  result.contains("<price>") );
        assertTrue(  result.contains("</price>") );
        assertTrue(  result.contains("<publish_date>") );
        assertTrue(  result.contains("</publish_date>") );
        assertTrue(  result.contains("<description>") );
        assertTrue(  result.contains("</description>") );

    }


    @Test
    void fetchAndSaveBooksShouldSaveToDatabase() throws IOException {
        XmlStreamProvider xmlStreamProvider = mock(XmlStreamProvider.class);
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("books.xml"));

        sut = new BookService(xmlStreamProvider,bookRepository);

        // Arrange
        bookRepository.deleteAll();

        // Act
        sut.FetchAndSaveBooks();

        //Assert
        assertEquals(3,bookRepository.count());
    }


}