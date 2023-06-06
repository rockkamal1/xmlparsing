package com.example.inventryservice.controller;

import com.example.inventryservice.dto.InventoryResponse;
import com.example.inventryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

   // http://localhost:9092/api/inventory/iphone-13,iphone13-red
    // http://localhost:9092/api/inventory?skuCode=iphone-13&skucCode=iphone13-red

  //  @GetMapping("/{sku-code}")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> IsInStock(@RequestParam List<String> skuCode){
    return inventoryService.IsInStock(skuCode);


    }
}
