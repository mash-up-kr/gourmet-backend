package com.kodachaya.gourmet.api.entity.review;

import com.kodachaya.gourmet.api.dto.review.Taste;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Taste")
public class TasteEntity {

    @Id
    @GeneratedValue
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "taste", nullable = false)
    private Taste taste;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasteEntity that = (TasteEntity) o;
        return id == that.id &&
                taste == that.taste;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taste);
    }

    @Override
    public String toString() {
        return "TasteEntity{" +
                "id=" + id +
                ", taste=" + taste +
                '}';
    }
}
