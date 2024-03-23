package net.java.forest.controller;


import lombok.extern.slf4j.Slf4j;
import net.java.forest.model.LoginRequest;
import net.java.forest.model.LoginResponse;
import net.java.forest.model.users_table;
import net.java.forest.security.CustomEncoder;
import net.java.forest.service.UserService;
import net.java.forest.utils.JWTUtil;
import net.java.forest.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Validated
public class LoginSignup {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilService util;

	/**
	 * Login request endpoint
	 * @param request
	 * @return
	 */
    // @RequestMapping(value ="/", method = RequestMethod.POST)
    @PostMapping("/")
    public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest request) {
        return userService.findByUsername(request.getUsername()).map((userDetails) -> {
            if (passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new LoginResponse(jwtUtil.generateToken(userDetails)));
            } else {
                log.info("password not matching");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    @RequestMapping(value = "/{Id}", method = RequestMethod.DELETE)
    // @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<Void> deleteBYId(@PathVariable Long Id)
    {
        return userService.deleteById(Id);
    }
    @GetMapping("/")
    public Flux<users_table> getAllUser()
    {
        return userService.getAllUser();
    }
    @PutMapping("/{Id}")
    public Mono<users_table> updateById(@PathVariable Long  Id,@RequestBody users_table newuser)
    {
        return userService.updateById(Id, newuser);
    }
	/**
	 * Signup endpoint
	 * @param user
	 * @return
	 */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Mono<ResponseEntity<?>> createPerson(@RequestBody users_table user) {
        String message = util.validation(user);
       // String message ="";
        if (message.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return Mono.just(ResponseEntity.ok(userService.addUpdateUser(user)));
        } else {
            return Mono.just(ResponseEntity.badRequest().body(message));
        }
    }
}
