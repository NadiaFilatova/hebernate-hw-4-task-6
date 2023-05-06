package org.example.entity.task6;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//@Slf4j
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final Faker faker = new Faker();

    public static void main(String[] args) {
        AuthorHelper authorHelper = new AuthorHelper();
        BookHelper bookHelper = new BookHelper();

        // random books and authors generation
        //Из пакета ex_002_insert_and_update создайте в цикле 200 объектов author и сохраните в БД.
        // Значения полей могут быть любыми.
        // Используйте метод flush для каждых 10 объектов. Метод сommit выполняйте один раз в конце.
        //List<Author> authors = IntStream.range(1, 201)
        //      .mapToObj(n -> randomAuthor())
        //    .toList();
        // authorHelper.saveAuthors(authors);


        // hw - 6
        //Обновить поле name для всех записей, у которых длина значения поля last_name больше 7
        // В поле name записать значение «1»
        // * Задание на самостоятельный поиск решений.
        authorHelper.updateByLastNameLengthGreaterThan(7);

        // В класс BookHelper пакета ex_004_relations дописать методы удаления книге по id и по автору.
        bookHelper.deleteByID(4L);
        bookHelper.deleteByAuthorName("Art");

    }

    private static Author randomAuthor() {
        Name fakeName = faker.name();

        Author author = new Author();
        author.setName(fakeName.firstName());
        author.setLastName(fakeName.lastName());

        Random random = new Random();
        List<Book> authorBooks = IntStream.range(1, random.nextInt(5))
                .mapToObj(n -> randomBook())
                .peek(book -> book.setAuthor(author))
                .toList();

        author.setBooks(authorBooks);
        return author;
    }

    private static Book randomBook() {
        Book book = new Book();
        book.setName(faker.book().title());
        return book;
    }
}
