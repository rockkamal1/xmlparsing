package com.example.inventryservice.service;

import com.example.inventryservice.dto.InventoryResponse;
import com.example.inventryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> IsInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                                InventoryResponse.builder()
                                        .skuCode(inventory.getSkuCode())
                                        .isInStock(inventory.getQuantity() > 0)
                                        .build()
                        ).collect(toList());
    }
}
