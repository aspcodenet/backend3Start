package se.systementor.backend3start.services.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.systementor.backend3start.demos.book;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTests {
    private XmlStreamProvider xmlStreamProvider = mock(XmlStreamProvider.class);
    BookService sut;

    @BeforeEach()
    void setUp() {
        sut = new BookService(xmlStreamProvider);

    }

    @Test
    void getBooksShouldMapCorrectly() throws IOException {
        when(xmlStreamProvider.getDataStream()).thenReturn(getClass().getClassLoader().getResourceAsStream("books.xml"));

        List<book> result = sut.GetBooks();
        assertEquals(3, result.size() );
        assertEquals("Stefan 1", result.get(0).author );
        assertEquals("XML Developer's Guide", result.get(0).title );
        assertEquals("Computer", result.get(0).category );

    }

}