package org.example.entity.task6;

import org.example.entity.task6.Book;
import org.example.entity.task6.BookHelper;

public class Main {
    public static void main(String[] args) {
        BookHelper helper = new BookHelper();

        Book book1 = new Book();
        book1.setName("Book 1");
        book1.setAuthorName("Author 1");
        helper.createBook(book1);

        Book book2 = new Book();
        book2.setName("Book 2");
        book2.setAuthorName("Author 1");
        helper.createBook(book2);

        Book book3 = new Book();
        book3.setName("Book 3");
        book3.setAuthorName("Author 2");
        helper.createBook(book3);

        // getting books list
        helper.getBooks().forEach(System.out::println);

        // getting book by id
        System.out.println(helper.getById(2));
    }
}
