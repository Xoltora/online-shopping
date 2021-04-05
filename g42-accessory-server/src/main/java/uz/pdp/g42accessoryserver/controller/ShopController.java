package uz.pdp.g42accessoryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g42accessoryserver.payload.ApiResponse;
import uz.pdp.g42accessoryserver.payload.ShopDto;
import uz.pdp.g42accessoryserver.service.ShopService;
import uz.pdp.g42accessoryserver.utills.AppConst;

@RestController
@PreAuthorize("hasRole({'ROLE_SUPER_ADMIN'})")
@RequestMapping("/api/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

//    @PreAuthorize("hasRole({'ROLE_SUPER_ADMIN'})")
    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody ShopDto dto) {
        ApiResponse response = shopService.saveOrEdit(dto);
        return ResponseEntity.status(response.isSuccess() ? response.getMessage().equals("Edited") ? 202 : 201 : 409).body(response);
    }

//    @PreAuthorize("hasRole({'ROLE_SUPER_ADMIN'})")
    @GetMapping("/all")
    public HttpEntity<?> all(@RequestParam(value = "page", defaultValue = AppConst.PAGE_DEFAULT_NUMBER) Integer page,
                             @RequestParam(value = "size", defaultValue = AppConst.PAGE_DEFAULT_SIZE) Integer size) throws IllegalAccessException {
        ApiResponse response = shopService.all(page, size);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

//    @PreAuthorize("hasAnyRole({'ROLE_SUPER_ADMIN','ROLE_MANAGER'})")
    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable Integer id){
        ApiResponse response = shopService.changeActive(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
}