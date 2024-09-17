package se.systementor.backend3start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import se.systementor.backend3start.configuration.IntegrationProperties;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.String.valueOf;


@ComponentScan
public class ConsoleApp1 implements CommandLineRunner {

    @Autowired
    private se.systementor.backend3start.Utils.DataSeeder dataSeeder;


/*
* ### check one
GET https://javabl.systementor.se/api/stefan/blacklistcheck/eh@test.com

### get all
GET https://javabl.systementor.se/api/stefan/blacklist


### add one
POST  https://javabl.systementor.se/api/stefan/blacklist
Content-Type: application/json

{
    "email":"stefan6@aaa.com",
    "name":"Stefan Holmberg",
    "isOk":true
}


### UPDATE one
PUT  https://javabl.systementor.se/api/stefan/blacklist/stefan6@aaa.com
Content-Type: application/json

{
    "name":"Stefan Holmberg",
    "isOk":"false"
}

*
* */

    @Autowired
    IntegrationProperties properties;

    @Override
    public void run(String... args) throws Exception {
        dataSeeder.Seed();
//        String email = properties.getBlacklist().getEmailtocheck();
//        System.out.println(isOk(email));
//
//
//        setAsBad(email);
//        System.out.println(isOk(email));
//        setAsOk(email);
//        System.out.println(isOk(email));


//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(s-> System.out.println(s))
//                .join();


//        JacksonXmlModule module = new JacksonXmlModule();
//        module.setDefaultUseWrapper(false);
//        XmlMapper xmlMapper = new XmlMapper(module);
//        Catalog theBooks = xmlMapper.readValue(new URL("https://axmjqhyyjpat.objectstorage.eu-amsterdam-1.oci.customer-oci.com/n/axmjqhyyjpat/b/aspcodeprod/o/books.xml"),
//                Catalog.class
//        );
//
//        for( book s : theBooks.books ){
//            System.out.println(s.title);
//            System.out.println(s.id);
//        }

    }

    private boolean isOk(String email) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/stefan/blacklistcheck/" + email))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        if(response.body().contains("true")) {
            return true;
        }
        return false;
   }


    private void setAsBad(String email) throws IOException, InterruptedException {
        addOrUpdate(email,false);
    }

    private void setAsOk(String email) throws IOException, InterruptedException {
        addOrUpdate(email,true);
    }

    private void addOrUpdate(String email, boolean ok) throws IOException, InterruptedException {
            if(update(email,ok)) return;
            add(email,ok);
    }

    private boolean update(String email, boolean ok) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/stefan/blacklist/" + email))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{ \"name\":\"Kalle\", \"isOk\":\"" + valueOf(ok) + "\" }"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() < 300;
    }



    private boolean add(String email, boolean ok) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://javabl.systementor.se/api/stefan/blacklist"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"email\":\"" + email +"\", \"name\":\"Kalle\", \"isOk\":\"" + valueOf(ok) + "\" }"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.statusCode() < 300;
    }



}
