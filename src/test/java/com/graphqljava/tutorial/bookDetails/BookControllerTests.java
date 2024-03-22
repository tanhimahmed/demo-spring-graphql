package com.graphqljava.tutorial.bookDetails;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetFirstBook() {
        this.graphQlTester
				.documentName("bookDetails")
				.variable("id", "book-1")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "book-1",
                        "name": "Harry Potter and the Philosopher's Stone",
                        "pageCount": 223,
                        "author": {
                          "firstName": "Joanne",
                          "lastName": "Rowling"
                        }
                    }
                """);
    }

    @Test
    void shouldGetMobyDick() {
        this.graphQlTester
            .documentName("bookNameDetails")
            .variable("name", "Moby Dick")
            .execute()
            .path("bookByName")
            .matchesJson("""
                {
                    "id": "book-2",
                    "name": "Moby Dick",
                    "pageCount": 635,
                    "author": {
                      "firstName": "Herman",
                      "lastName": "Melville"
                    }
                }s
            """);

    }
}