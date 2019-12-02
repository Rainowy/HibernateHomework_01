package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Author;
import pl.coderslab.service.AuthorService;

public class AuthorConverter implements Converter<String, Author> {

    @Autowired
    AuthorService authorService;

    @Override
    public Author convert(String id) {

        return authorService.findOne(id);
    }
}