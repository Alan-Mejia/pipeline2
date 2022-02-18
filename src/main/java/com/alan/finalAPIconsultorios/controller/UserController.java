package com.alan.finalAPIconsultorios.controller;

import com.alan.finalAPIconsultorios.dtos.SimpleUserDTO;
//import com.alan.finalAPIconsultorios.grpcservice.UserGrpcServiceImpl;
import com.alan.finalAPIconsultorios.models.User;
import com.alan.finalAPIconsultorios.serviceImpl.UserServiceImpl;
//import com.doctorsrvc.grpc.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends GenericControllerImpl<User,UserServiceImpl> {


    @Autowired
    private UserServiceImpl userService;

    @Override
    @GetMapping("")
    public CompletableFuture<ResponseEntity> getAll(){
        return userService.getAllDTO().thenApply(ResponseEntity::ok);
    }

    @Override
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity> getOne(@PathVariable Long id){
        CompletableFuture<SimpleUserDTO> exists = userService.getOneUserDTO(id);
        return exists.thenApply(ResponseEntity::ok);
    }

    @PostMapping("")
    public CompletableFuture<ResponseEntity> save(@RequestBody User user){
        user.setModificationTime(new Date());
        user.setCreationTime(new Date());
        user.setRole(1);
        user.setStatus(1);
        return userService.save(user).thenApply(ResponseEntity::ok);
    }

    @Override
    @PutMapping("/{id}")
    public CompletableFuture<ResponseEntity> update(@RequestBody User user,@PathVariable("id") Long id){
        return userService.updateUser(user,id).thenApply(ResponseEntity::ok);
    }


    @Override
    @DeleteMapping("/{id}")
    public CompletableFuture<ResponseEntity> delete(@PathVariable("id")Long id){
        return userService.deleteUser(id).thenApply(ResponseEntity::ok);
    }
//
//    @Autowired
//    private UserGrpcServiceImpl userGrpcService;

//    @GetMapping("doctorgrpc/{id}")
//    public Doctor getDoctorFromUsersAPI(@PathVariable Long id){
//        return userGrpcService.grpcGetByIdRRequest(id);
//
//    }

}
