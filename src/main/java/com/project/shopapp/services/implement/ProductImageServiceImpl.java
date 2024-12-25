package com.project.shopapp.services.implement;

import com.project.shopapp.common.IMAGE_FORMAT;
import com.project.shopapp.common.RESPONSE_STATUS;
import com.project.shopapp.common.base.ResponseWrapper;
import com.project.shopapp.confiuration.exception.ResponseDataException;
import com.project.shopapp.dtos.response.base.PaginatedDataResponse;
import com.project.shopapp.common.constant.Constant;
import com.project.shopapp.confiuration.exception.BadRequestException;
import com.project.shopapp.confiuration.exception.NotFoundException;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.repositories.ProductImageRepository;
import com.project.shopapp.repositories.ProductRepository;
import com.project.shopapp.services.IProductImageService;
import com.project.shopapp.services.implement.base.BaseServiceImpl;
import com.project.shopapp.specs.ProductImageSpec;
import com.project.shopapp.specs.ProductSpec;
import com.project.shopapp.utils.FileUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl extends BaseServiceImpl implements IProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;

    private final ProductImageSpec productImageSpec;
    private final ProductSpec productSpec;

    private final FileUtils fileUtils;

    @Override
    @Transactional
    public PaginatedDataResponse uploadProductImage(Long productId, List<MultipartFile> files) {
        Product product = getProduct(productId, files.size());

        List<ProductImage> productImages = new ArrayList<>();
        List<ResponseWrapper> errorFiles = new ArrayList<>();

        files.forEach(file -> checkFile(file, errorFiles));

        if (!errorFiles.isEmpty()) {
            throw new ResponseDataException(
                    Constant.UPLOAD.MESSAGE.ERROR_IMAGE_UPLOAD,
                    errorFiles
            );
        }

        for (MultipartFile file : files) {
            String uniFileName = fileUtils.getUniqueFileName(file.getOriginalFilename());
            String imageUrl = fileUtils.storeFile(file, uniFileName);
            long imageSize = file.getSize();
            String imageType = file.getContentType();

            ProductImage productImage = new ProductImage(file);
            productImage.setProduct(product);
            productImage.setImageUrl(imageUrl);
            productImage.setImageAltText("Image of product " + productId);
            productImage.setImageSize(imageSize);
            productImage.setImageType(imageType);

            productImages.add(productImage);
        }

        return new PaginatedDataResponse(productImageRepository.saveAll(productImages));
    }

    private Product getProduct(Long productId, int numberOfFile) {
        if (productId == null) {
            throw new BadRequestException(Constant.UPLOAD.MESSAGE.ERROR_PRODUCT_ID_REQUIRED);
        }

        long numberOfFileExist = productImageRepository.count(
                productImageSpec.countProductImageByProductId(productId)
        );
        if (numberOfFile > Constant.UPLOAD.MAX_UPLOAD_IMAGE_PER_PRODUCT ||
                numberOfFileExist >= Constant.UPLOAD.MAX_UPLOAD_IMAGE_PER_PRODUCT) {
            throw new BadRequestException(Constant.UPLOAD.MESSAGE.ERROR_MAX_UPLOAD_IMAGE_PER_PRODUCT);
        }

        long totalNumberOfFiles = numberOfFileExist + numberOfFile;
        if (totalNumberOfFiles > Constant.UPLOAD.MAX_UPLOAD_IMAGE_PER_PRODUCT) {
            long remainingOfFiles = Constant.UPLOAD.MAX_UPLOAD_IMAGE_PER_PRODUCT - numberOfFileExist;
            throw new BadRequestException(
                    Constant.UPLOAD.MESSAGE.REMAINING_UPLOAD_IMAGE_PER_PRODUCT + remainingOfFiles
            );
        }

        return productRepository
                .findOne(productSpec.getProductById(productId))
                .orElseThrow(() -> new NotFoundException(Constant.UPLOAD.MESSAGE.ERROR_PRODUCT_NOT_FOUND));
    }

    private void checkFile(MultipartFile file, List<ResponseWrapper> errorFiles) {

        if (file.getSize() == Constant.UPLOAD.MIN_UPLOAD_SIZE) {
            String message = Constant.UPLOAD.MESSAGE.ERROR_MIN_UPLOAD_SIZE;
            ResponseWrapper error = new ResponseWrapper(
                    RESPONSE_STATUS.ERROR,
                    HttpStatus.METHOD_NOT_ALLOWED.value(),
                    message + file.getOriginalFilename()
            );
            errorFiles.add(error);
        }

        if (file.getSize() > Constant.UPLOAD.MAX_UPLOAD_SIZE) {
            String message = Constant.UPLOAD.MESSAGE.ERROR_MAX_UPLOAD_SIZE;
            ResponseWrapper error = new ResponseWrapper(
                    RESPONSE_STATUS.ERROR,
                    HttpStatus.METHOD_NOT_ALLOWED.value(),
                    message + file.getOriginalFilename()
            );
            errorFiles.add(error);
        }

        String extension = fileUtils.getFileExtension(file);
        List<String> defaultExtensions = IMAGE_FORMAT.getExtensions();
        if (!defaultExtensions.contains(extension)) {
            String message = Constant.CHARACTER.COLON + (String.format(Constant.UPLOAD.MESSAGE.ERROR_FILE_EXTENSION, defaultExtensions));
            ResponseWrapper error = new ResponseWrapper(
                    RESPONSE_STATUS.ERROR,
                    HttpStatus.METHOD_NOT_ALLOWED.value(),
                    file.getOriginalFilename() + message
            );
            errorFiles.add(error);
        }
    }


    @Override
    @Transactional
    public void deleteProductImage(List<Long> productImageIds) {
        List<ProductImage> imageExists = productImageRepository.findAll(productImageSpec.getProductImageByIds(productImageIds));

        List<ResponseWrapper> errorFiles = new ArrayList<>();

        imageExists.forEach(imageExist -> fileUtils.removeImage(imageExist, errorFiles));

        if (!errorFiles.isEmpty()) {
            throw new ResponseDataException(
                    Constant.UPLOAD.MESSAGE.ERROR_IMAGE_DELETE,
                    errorFiles
            );
        }

        productImageRepository.saveAll(imageExists);
    }
}
