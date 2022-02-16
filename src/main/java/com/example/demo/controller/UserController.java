package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.security.RolesAllowed;

import com.example.demo.data.User;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping( "/v1/user" )
public class UserController {
    private final UserService userService;
    private final AtomicLong counter = new AtomicLong(0);

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        return new ResponseEntity<List<User>>(userService.getAll() , HttpStatus.OK );

    }

    @GetMapping( "/{id}" )
    public ResponseEntity<User> findById(@PathVariable String id ) {


        return new ResponseEntity<User>(userService.findById(id) , HttpStatus.OK );
    }


    @PostMapping
    public ResponseEntity<User> create( @RequestBody UserDto userDto ) {
        User userCreation = new User((Integer.toString((int) counter.incrementAndGet())), userDto.getName() , userDto.getEmail() ,
                userDto.getLastName() , new Date());
        return new ResponseEntity<User>(userService.create(userCreation), HttpStatus.OK );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<User> update( @RequestBody UserDto userDto, @PathVariable String id ) {
        User user = userService.findById(id);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        return new ResponseEntity<User>(userService.update(user , id ), HttpStatus.OK );
    }

    @DeleteMapping( "/{id}" )
    @RolesAllowed("ADMIN")
    public ResponseEntity<Boolean> delete( @PathVariable String id ) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
    @GetMapping( "/FindByName/{qweryText}" )
     public ResponseEntity<List<User>> findUsersWithNameOrLastNameLike(@PathVariable String qweryText ) {
        return new ResponseEntity<List<User>>(userService.findUsersWithNameOrLastNameLike(qweryText), HttpStatus.OK );
    }
     
    @GetMapping( "/FindByDate/{date}" )
     public ResponseEntity<List<User>> findUsersCreatedAfter(@PathVariable String date ) throws ParseException {
        Date dateFormat =new SimpleDateFormat("dd-MM-yyyy").parse(date);  
        dateFormat.setTime(0);
        System.out.println(date.toString()); 
        
        return new ResponseEntity<List<User>>(userService.findUsersCreatedAfter(dateFormat), HttpStatus.OK );
    }
}
