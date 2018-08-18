package com.kodachaya.gourmet.api.entity.restaurant;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "Menu")
public class MenuEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;


    @ManyToOne
    @JoinColumn(name = "restaurantId", foreignKey = @ForeignKey(name = "FK_Menu_Restaurant"))
    private RestaurantEntity restaurant;

    @Column(name = "price")
    private Integer price;


    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;


    public MenuEntity() {}

    public MenuEntity(String name, RestaurantEntity restaurant) {
        this(name, restaurant, null);
    }

    public MenuEntity(String name, RestaurantEntity restaurant, Integer price) {
        this.name = name;
        this.restaurant = restaurant;
        this.price = price;
    }

    @PrePersist
    public void persist() {
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public Optional<Integer> getPrice() {
        return Optional.ofNullable(price);
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(restaurant, that.restaurant) &&
                Objects.equals(price, that.price) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, restaurant, price, createdAt);
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
