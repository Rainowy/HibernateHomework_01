package pl.coderslab.DTO;

import java.time.LocalDateTime;

public class ArticleDTO {

    private long id;

    private String content;

    private LocalDateTime createdOn;

    private String title;

    private String categoryName;

    private LocalDateTime updatedOn;

    public ArticleDTO(long id, String content, LocalDateTime createdOn, String title, String categoryName, LocalDateTime updatedOn) {
        this.id = id;
        this.content = content;
        this.createdOn = createdOn;
        this.title = title;
        this.categoryName = categoryName;
        this.updatedOn = updatedOn;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getTitle() {
        return title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }
}
