package pl.coderslab.entity;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    @OneToOne(
            mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Author author;

    @OneToMany(mappedBy = "article",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Category> categories;

    @Column(length = 1000)
    private String content;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", categories=" + categories +
                ", content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                '}';
    }


    public Article(String title, String content, LocalDateTime createdOn) {
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
    }

    public Article() {
    }
}
