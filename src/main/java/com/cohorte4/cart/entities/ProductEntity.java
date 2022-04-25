package com.cohorte4.cart.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String brand;
    private String barcode;
    private Integer stock;

    @Transient
    @Column(name = "average_rating")
    private double promRate;

    @JsonIgnoreProperties("products")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_category")
    private CategoryEntity categoryId;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_photo")
    private PhotoEntity photoId;

    @Generated( value = GenerationTime.ALWAYS)
    @Column (name = "created_at", length = 30, nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Generated( value = GenerationTime.ALWAYS)
    @Column (name = "updated_at", length = 30, nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updatedAt;

    @JsonIgnoreProperties("productId")
    @OneToMany(mappedBy = "productId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RatingEntity> ratings = new ArrayList<>();

    public double getPromRate() {
        double sum = 0;
        for (RatingEntity ratingEntity : ratings) {
            sum += ratingEntity.getValue();
        }
        if(ratings.size()!= 0){
            return sum / ratings.size();
        }   else{
            return 0;
        }

    }
}
