// https://dzone.com/articles/splitting-unit-and-integration-tests-using-maven-a

package se.systementor.backend3start.services.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;


//INTEGRATIONSTESTER - skulle kunna köras VARJE NATT och eller i s.k CI/CD - innan DEPLOY av ny version
// externa saker - om dom byter author till <TheAuthor>
// Integrationstester som failar på natten -> SKICKA MAIL TILL ALLA UTVECKLARE
// LAGA OM FEL !!!!


@SpringBootTest
class BookServiceTestsIT {
    @Autowired
    BookService sut;

    @Test
    void getBooksShouldContainCorrectTags() throws IOException {
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

}