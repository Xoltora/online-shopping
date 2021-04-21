package uz.pdp.g42accessoryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.g42accessoryserver.entity.User;
import uz.pdp.g42accessoryserver.payload.ApiResponse;
import uz.pdp.g42accessoryserver.payload.UserDto;
import uz.pdp.g42accessoryserver.secret.CurrentUser;
import uz.pdp.g42accessoryserver.service.UserService;
import uz.pdp.g42accessoryserver.utills.AppConst;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody UserDto dto,
                                    @CurrentUser User user){
        ApiResponse response = userService.saveOrEdit(dto,user);
        return ResponseEntity.status(response.isSuccess()?response.getMessage().equals("Edited")?202:201:409).body(response);
    }

    @GetMapping("/changePassword")
    public HttpEntity<?> changePassword(@RequestParam String oldPassword,
                                        @RequestParam String newPassword,
                                        @CurrentUser User user){
        ApiResponse response = userService.changePassword(oldPassword,newPassword,user);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @PreAuthorize("hasAnyRole({'ROLE_SUPER_ADMIN','ROLE_MANAGER'})")
    @GetMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse response = userService.changeActive(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @PreAuthorize("hasAnyRole({'ROLE_SUPER_ADMIN','ROLE_MANAGER'})")
    @GetMapping("/remove/{id}")
    public HttpEntity<?> remove(@PathVariable UUID id){
        ApiResponse response = userService.remove(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

    @PreAuthorize("hasAnyRole({'ROLE_SUPER_ADMIN','ROLE_MANAGER'})")
    @GetMapping("/all")
    public HttpEntity<?> all(@RequestParam(value = "page",defaultValue = AppConst.PAGE_DEFAULT_NUMBER)Integer page,
                                @RequestParam(value = "size",defaultValue = AppConst.PAGE_DEFAULT_SIZE)Integer size) throws IllegalAccessException {
        ApiResponse response = userService.all(page,size);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }

}
