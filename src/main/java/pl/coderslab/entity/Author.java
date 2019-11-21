package pl.coderslab.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;

    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Article article;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}

