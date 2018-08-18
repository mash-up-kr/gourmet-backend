package com.kodachaya.gourmet.api.entity.review;

import com.kodachaya.gourmet.api.dto.Stamp;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Stamp")
public class StampEntity {

    @Id
    @GeneratedValue
    private int id;


    @Enumerated(EnumType.STRING)
    @Column(name = "expression", nullable = false)
    private Stamp expression;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stamp getExpression() {
        return expression;
    }

    public void setExpression(Stamp expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StampEntity that = (StampEntity) o;
        return id == that.id &&
                expression == that.expression;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expression);
    }

    @Override
    public String toString() {
        return "StampEntity{" +
                "id=" + id +
                ", expression=" + expression +
                '}';
    }
}
