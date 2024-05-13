package se.systementor.backend3start.services.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import se.systementor.backend3start.demos.book;
import se.systementor.backend3start.model.Book;
import se.systementor.backend3start.model.BookRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//Unittest MAPPNING - fångaatt ngn ändrar i book-klassen och dess mappning
// unittest körs "alltid" - när vi kör tester

class BookServiceTests {
    private XmlStreamProvider xmlStreamProvider = mock(XmlStreamProvider.class);

    private BookRepository bookRepository  = mock(BookRepository.class);

    BookService sut;

    @BeforeEach()
    void setUp() {
        sut = new BookService(xmlStreamProvider,bookRepository);

    }

    @Test
    void whenGetBooksShouldMapCorrectly() throws IOException {
        // Arrange
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("books.xml"));

        // Act
        List<book> result = sut.GetBooks();

        //Assert
        assertEquals(3, result.size() );
        assertEquals("Stefan 1", result.get(0).author );
        assertEquals("XML Developer's Guide", result.get(0).title );
        assertEquals("Computer", result.get(0).category );

    }

    @Test
    void fetchAndSaveBooksShouldInsertNewRecords() throws IOException {
        // Arrange
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("books.xml"));
        when(bookRepository.findByExternalSystemId(Mockito.anyString())).thenReturn(Optional.empty());

        // Act
        sut.FetchAndSaveBooks();

        //Assert
        verify(bookRepository,times(3)).save(argThat(book -> book.id == 0 ));

    }
§
    @Test
    void fetchAndSaveBooksShouldUpdateExistingRecords() throws IOException {
        // Arrange
        Book existing = new Book();
        existing.id = 123;
        existing.setExternalSystemId("aaa111");

        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("books.xml"));
        when(bookRepository.findByExternalSystemId(Mockito.anyString())).thenReturn(Optional.empty());
        when(bookRepository.findByExternalSystemId("aaa111")).thenReturn(Optional.of(existing));


        // Act
        sut.FetchAndSaveBooks();

        //Assert
        verify(bookRepository,times(2)).save(argThat(book -> book.id == 0 ));
        verify(bookRepository,times(1)).save(argThat(book -> book.id == 123 ));

    }



}