package org.task6;

import org.task6.entity.Author;
import org.task6.entity.Book;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        String[] names = {"Pushkin", "Lermontov", "Shevchenko"};

        for (String name : names) {
            Author author = new Author();
            author.setName(name);
            ah.addAuthor(author);
        }

        List<Author> authorList = ah.getAuthorList();

        for (Author author : authorList) {
            System.out.println(author.getId() + " " + author.getName());
        }

        Author author = ah.getAuthorById(5);
        System.out.println(author.getName());

        BookHelper bookHelper = new BookHelper();
        Book[] books = {new Book("Руслан і людмила", 1),
                        new Book("Парус", 2),
                        new Book("Кобзар", 3)};

        for (Book b : books) {
            bookHelper.addBook(b);
        }
        List<Book> bookList  = bookHelper.getBookList();

        for (Book b: bookList) {
            System.out.println(b.getId() + " " + b.getName() + " with author " + b.getAuthor_id() );
        }

        Book book = bookHelper.getBookById(2);
        System.out.println(book.getName());
    }
}

