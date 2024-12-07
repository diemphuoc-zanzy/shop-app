package com.project.shopapp.repositories;

import com.project.shopapp.repositories.base.BaseRepository;
import com.project.shopapp.models.OrderDetail;

import java.util.List;

public interface OrderDetailRepository extends BaseRepository<OrderDetail, Long> {

    List<OrderDetail> findByOrderId(Long orderId);

}
