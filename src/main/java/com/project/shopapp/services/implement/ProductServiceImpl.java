package com.project.shopapp.services.implement;

import com.project.shopapp.common.RECORD_STATUS;
import com.project.shopapp.dtos.response.ProductResponseDto;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.dtos.request.ProductRequestDto;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.ICategoryService;
import com.project.shopapp.services.IProductService;
import com.project.shopapp.utils.DtoMapper;
import com.project.shopapp.specs.ProductSpec;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ICategoryService categoryService;

    private final ProductSpec productSpec;

    private final DtoMapper dtoMapper;

    @Override
    public Optional<Product> iFindOne(Specification<Product> specification) {
        return productRepository.findOne(specification);
    }

    @Override
    public Product iFindById(Long productId) {
        return productRepository
                .findOne(productSpec.getProductById(productId))
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public Product iFindByName(String productName) {
        return productRepository.findByName(productName);
    }

    @Override
    public PaginatedDataResponse getProducts(ProductRequestDto productRequestDto) {
        Page<Product> products = productRepository.findAll(productSpec.getProducts(productRequestDto), productRequestDto.toPageable());

        return dtoMapper.makeResponse(ProductResponseDto.class, products);
    }

    @Override
    public PaginatedDataResponse getProductById(Long id) {
        if (id == null)
            throw new NotFoundException("Product not found");

        Product product = this.iFindById(id);

        return dtoMapper.makeResponse(ProductResponseDto.class, product);
    }

    @Override
    @Transactional
    public PaginatedDataResponse createProduct(ProductRequestDto productRequestDto) {
        Product product = this.iFindByName(productRequestDto.getName());
        Category category = null;

        if (productRequestDto.getCategoryId() != null) {
            category = categoryService.iFindById(productRequestDto.getCategoryId());
        }

        if (productRequestDto.getCategoryName() != null) {
            category = categoryService.iFindByName(productRequestDto.getCategoryName());
        }

        if (product!= null) {
            product.updateRecordStatus(RECORD_STATUS.ACTIVE);
            product.update(productRequestDto);
        } else {
            product = new Product(productRequestDto);
        }

        product.setCategory(category);

        product = productRepository.save(product);
        return dtoMapper.makeResponse(ProductResponseDto.class, product);
    }

    @Override
    @Transactional
    public PaginatedDataResponse updateProduct(ProductRequestDto productRequestDto) {
        Product productExist = this.iFindById(productRequestDto.getId());
        Category category = null;

        if (productRequestDto.getCategoryName()!= null) {
            category = categoryService.iFindByName(productRequestDto.getCategoryName());
        }

        productExist.update(productRequestDto);
        productExist.setCategory(category);

        productExist = productRepository.save(productExist);
        return dtoMapper.makeResponse(ProductResponseDto.class, productExist);
    }

    @Override
    public void deleteProducts(List<Long> id) {
        List<Product> productExists = productRepository.findAll(productSpec.getProductByIds(id));
        productExists.forEach(productExist -> productExist.updateRecordStatus(RECORD_STATUS.DELETED));

        productRepository.saveAll(productExists);
    }
}
