package com.alison.spring5webapp.bootstrap;

import com.alison.spring5webapp.model.Author;
import com.alison.spring5webapp.model.Book;
import com.alison.spring5webapp.model.Publisher;
import com.alison.spring5webapp.repositories.AuthorRepository;
import com.alison.spring5webapp.repositories.BookRepository;
import com.alison.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class DevBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookrepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookrepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookrepository = bookrepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        initData();
    }

    private void initData(){

        Publisher publisher = new Publisher();
        publisher.setName("Lord Harper Collins");

        publisherRepository.save(publisher);

        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driver design","1224",publisher);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookrepository.save(ddd);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "223333",publisher);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookrepository.save(noEJB);


    }

}
