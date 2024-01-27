package it.jpalibrary.chiarapuleio.classes;


import it.jpalibrary.chiarapuleio.enums.Periodicity;
import it.jpalibrary.chiarapuleio.superClass.LibraryItem;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Random;
@Entity
@DiscriminatorValue("magazine")
public class Magazine extends LibraryItem {
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(){
        super("", 0, 0);
    }

    public Magazine(String title, int publicationYear, int numPages, Periodicity periodicity) {
        super(title, publicationYear, numPages);
        this.periodicity = periodicity;
    }
    static Periodicity getRandomPeriodicity(){
        Periodicity[] options = Periodicity.values();
        int indexRand = new Random().nextInt(options.length);
        return options[indexRand];
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "isbn='" + getIsbnCode() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", year='" + getPublicationYear() + '\'' +
                ", pages='" + getNumPages() + '\'' +
                ", periodicity=" + periodicity +
                '}' + "\n";
    }
}