package com.cohorte4.cart.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private UserEntity userId;

    @Generated(value = GenerationTime.ALWAYS)
    @Column (name = "created_at", length = 30, nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Transient
    @Column(name = "total_price")
    private double totalPrice;

    @Transient
    @Column(name = "items_number")
    private int itemsNumber;

    @JsonIgnoreProperties("cartId")
    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL)
    private List<CartItemEntity> itemsList = new ArrayList<>();

    @Column(name = "means_of_payment", length = 50)
    private String meansOfPayment;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    public double getTotalPrice(){
        double sum = 0;
        for (CartItemEntity c: itemsList) {
            sum += c.getProductId().getPrice()* c.getQuantity();
        }
        return sum;
    }

    public int getItemsNumber() {
        return itemsList.size();
    }
}
