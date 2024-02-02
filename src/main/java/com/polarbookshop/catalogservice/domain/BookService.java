package com.polarbookshop.catalogservice.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findBookByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book){
        if(bookRepository.findBookByIsbn(book.isbn()).isPresent()){
            throw new BookAlreadyExistException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn){
        bookRepository.deleteBookByIsbn(isbn);
    }

    public Book editeBookDetail(String isbn, Book book) {
        return bookRepository.findBookByIsbn(isbn)
                .map(existingBook -> {
                    var editedBook = new Book(
                            existingBook.id(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.publisher(),
                            book.price(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.version()
                    );
                    return bookRepository.save(editedBook);
                })
                .orElseGet(() -> addBookToCatalog(book));
    }
}
