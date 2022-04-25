package com.cohorte4.cart.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private ProductEntity productId;

    @Column(name = "quantity")
    private int quantity;

    @JsonIgnoreProperties("itemsList")
    @ManyToOne
    @JoinColumn(name = "id_cart")
    private CartEntity cartId;
}
