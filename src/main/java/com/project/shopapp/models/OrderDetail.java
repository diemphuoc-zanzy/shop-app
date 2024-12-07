package com.project.shopapp.models;

import com.project.shopapp.dtos.request.OrderDetailRequestDto;
import com.project.shopapp.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Double price;

    @Column(name = "number_of_products", nullable = false)
    private Integer numberOfProducts;

    @Column(name = "total_money", nullable = false)
    private Double totalMoney;

    private String color;

    public OrderDetail(OrderDetailRequestDto orderDetailDto) {
        this.price = orderDetailDto.getPrice();
        this.numberOfProducts = orderDetailDto.getNumberOfProducts();
        this.totalMoney = orderDetailDto.getTotalMoney();
        this.color = orderDetailDto.getColor();
    }

    public void update(OrderDetailRequestDto orderDetailDto) {
        this.price = orderDetailDto.getPrice();
        this.numberOfProducts = orderDetailDto.getNumberOfProducts();
        this.totalMoney = orderDetailDto.getTotalMoney();
        this.color = orderDetailDto.getColor();

    }
}
