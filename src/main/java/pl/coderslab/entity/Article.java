package pl.coderslab.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.NotEmpty;
import pl.coderslab.validation.CatRequired;
import pl.coderslab.validation.DraftArticle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "article")
public class Article implements Serializable {



    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue()
    private Long id;

    @NotEmpty(groups = DraftArticle.class)
    @Size(max = 200,groups = DraftArticle.class)
    @Column(length = 200)
    private String title;

    @OneToOne(
            mappedBy = "article", //relacja jest ustalana przez author
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Author author;


    @CatRequired(groups = DraftArticle.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty(groups = DraftArticle.class)
    @Size(max = 500, groups = DraftArticle.class)
    @Column(length = 1000)
    private String content;

    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    private boolean draft;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Article))
            return false;

        return
                id != null &&
                        id.equals(((Article) o).getId());
    }
    @Override
    public int hashCode() {
        return 31;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    /**
     * Synchro
     */
    public void setAuthor(Author author) {
        if (author == null) {
            if (this.author != null) {
                this.author.setArticle(null);
            }
        } else {
            author.setArticle(this);
        }
        this.author = author;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Article(String title, String content, LocalDateTime createdOn) {
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
    }

    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", draft=" + draft +
                '}';
    }
}
