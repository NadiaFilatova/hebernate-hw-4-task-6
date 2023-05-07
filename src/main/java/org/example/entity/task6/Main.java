package org.example.entity.task6;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
public class Main {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final AuthorService authorService = new AuthorService();
    private static final BookService bookService = new BookService();

    public static void main(String[] args) {
        //Задание 4
        //Используя MySQL Workbench переписать базу данных так, чтобы одну книгу могли б написать несколько
        // авторов, также один автор может написать несколько книг.
        // Реализовать связь многие ко многим.
        List<Author> authors = new ArrayList<>();
        while (authors.size() < 200) {
            authors.addAll(generateRandomAuthorsAndBooks());
        }
        authorService.saveAuthors(authors);
        //Задание 5
        //  Из пакета ex_002_select_where написать отдельный метод для выборки по поиску выражения
        authorService.findByFirstNameOrLastNameLike("ro").forEach(author -> log.info("{}", author));
    }

    private static List<Author> generateRandomAuthorsAndBooks() {
        int authorsCount = randomInt(1, 4);
        log.info("Authors count: {}", authorsCount);

        List<Author> authors = IntStream.range(1, authorsCount)
                .mapToObj(n -> randomAuthor())
                .toList();

        int booksCount = randomInt(4, 10);
        log.info("Books count: {}", booksCount);

        List<Book> books = IntStream.range(1, booksCount)
                .mapToObj(n -> randomBook())
                .toList();

        books.forEach(book -> book.getAuthors().addAll(authors));
        authors.forEach(author -> author.getBooks().addAll(books));

        log.info("");
        return authors;
    }

    private static Book randomBook() {
        Book book = new Book();
        book.setName(faker.book().title());
        return book;
    }

    private static Author randomAuthor() {
        Author author = new Author();
        Name fakeName = faker.name();

        author.setName(fakeName.firstName());
        author.setLastName(fakeName.lastName());
        return author;
    }

    private static int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
