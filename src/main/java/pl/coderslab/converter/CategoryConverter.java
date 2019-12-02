package pl.coderslab.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.entity.Category;
import pl.coderslab.service.CategoryService;

public class CategoryConverter implements Converter<String, Category>{

    @Autowired
    CategoryService categoryService;


    @Override
    public Category convert(String id) {
        return categoryService.findOne(id);
    }
}

