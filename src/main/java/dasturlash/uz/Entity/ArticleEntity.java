package dasturlash.uz.Entity;

import dasturlash.uz.enums.ArticleStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private String content;

    @Column(name = "image_id")
    private String imageId;

    @Column(name = "region_id")
    private Integer regionId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public List<Integer> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Integer> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Integer> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Integer> sectionList) {
        this.sectionList = sectionList;
    }

    @Enumerated(EnumType.STRING)
    private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;

    private LocalDateTime createdDate = LocalDateTime.now();

    @ElementCollection
    @CollectionTable(name = "article_category", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "category_id")
    private List<Integer> categoryList;


    @ElementCollection
    @CollectionTable(name = "article_section", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "section_id")
    private List<Integer> sectionList;
}