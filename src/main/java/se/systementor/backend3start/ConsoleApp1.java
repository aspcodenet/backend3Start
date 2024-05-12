package se.systementor.backend3start;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import se.systementor.backend3start.demos.Catalog;
import se.systementor.backend3start.demos.book;

import java.net.URL;


@ComponentScan
public class ConsoleApp1 implements CommandLineRunner {

    @Autowired
    private se.systementor.backend3start.Utils.DataSeeder dataSeeder;

    @Override
    public void run(String... args) throws Exception {

        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        Catalog theBooks = xmlMapper.readValue(new URL("https://axmjqhyyjpat.objectstorage.eu-amsterdam-1.oci.customer-oci.com/n/axmjqhyyjpat/b/aspcodeprod/o/books.xml"),
                Catalog.class
        );

        for( book s : theBooks.books ){
            System.out.println(s.title);
            System.out.println(s.id);
        }

    }

}
