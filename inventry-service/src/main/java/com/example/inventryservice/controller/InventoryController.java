package com.example.inventryservice.controller;

import com.example.inventryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean IsInStock(@PathVariable("sku-code") String skuCode){
    return inventoryService.IsInStock(skuCode);


    }
}
