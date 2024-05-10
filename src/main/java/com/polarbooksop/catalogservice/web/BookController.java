package com.polarbooksop.catalogservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@Slf4j
public class BookController {

    @GetMapping
    public ResponseEntity<String> getBook() {
        log.info("Getting all book from the DB.");
        return ResponseEntity.ok("Here are all the books from the DB.");
    }
}
