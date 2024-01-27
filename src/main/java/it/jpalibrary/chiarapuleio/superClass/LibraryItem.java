package it.jpalibrary.chiarapuleio.superClass;


import com.github.javafaker.Faker;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="library_items")
public abstract class LibraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long itemId;
    private String isbnCode;
    private String title;
    private int publicationYear;
    private int numPages;

    public LibraryItem(String title, int publicationYear, int numPages) {
        Faker faker = new Faker();
        String isbnRand = faker.code().isbn13();
        this.isbnCode = isbnRand;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numPages = numPages;
    }
    public String getIsbnCode() {
        return isbnCode;
    }
    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }


    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumPages() {
        return numPages;
    }

    @Override
    public String toString() {
        return "LibraryItem{" +
                "isbnCode=" + isbnCode + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear + "\n" +
                ", numPages=" + numPages +
                '}';
    }
}
