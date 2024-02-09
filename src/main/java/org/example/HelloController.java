package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sql2o.Sql2o;

@RestController
public class HelloController {
    private final Sql2o sql2o = new Sql2o("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","sa", "");

    @GetMapping("/")
    public String index(@RequestParam("id") String id) {
        try (var con = sql2o.open()) {
            con.createQuery("select 1 where id = " + id).executeScalar(Integer.class);
        }

        return "Greetings from Spring Boot!";
    }

    @GetMapping("/connect")
    public String connect(@RequestParam("url") String url) {
        Sql2o sql2o = new Sql2o(url);

        try (var con = sql2o.open()) {
            con.createQuery("select 1").executeScalar(Integer.class);
        }

        System.out.println("Connected to " + url);

        return "Greetings from Spring Boot!";
    }
}
