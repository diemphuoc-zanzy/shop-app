package com.project.shopapp.models;

import com.project.shopapp.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "product_images")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url", length = 300)
    private String imageUrl;

    @Column(name = "image_alt_text", length = 150)
    private String imageAltText;

    @Column(name = "is_primary")
    private Boolean isPrimary = false;

    @Column(name = "image_size")
    private Long imageSize;

    @Column(name = "image_type", length = 50)
    private String imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(MultipartFile file) {
    }
}
