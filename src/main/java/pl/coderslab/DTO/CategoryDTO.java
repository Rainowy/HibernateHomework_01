package pl.coderslab.DTO;

import pl.coderslab.entity.Article;

import java.util.Set;

public class CategoryDTO {

    private long id;

    private String description;

    private String name;

//    private Set<Article> articles;

    private long artId;

    public CategoryDTO(long id, String description, String name, long artId ) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.artId = artId;
    }


    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

//    public Set<Article> getArticles() {
//        return articles;
//    }

    public long getArtId() {
        return artId;
    }
}
