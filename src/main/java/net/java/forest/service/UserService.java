package net.java.forest.service;

import net.java.forest.Exception.UserNotFound;
import net.java.forest.model.users_table;
import net.java.forest.repository.UserRepository;

import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private users_table UserNotExist(users_table user_detail) {
        if (user_detail == null) {
            throw new UserNotFound("user not found");
        }
        return user_detail;
    }

    public Mono<users_table> findByUsername(String username) {
        Mono<users_table> data = userRepository.findByEmail(username);
        return data.map(user_detail -> UserNotExist(user_detail));

    }

    public Flux<users_table> getAllUser() {
        return userRepository.findAll()
                .map(user_detail -> UserNotExist(user_detail));
    }

    public Mono<users_table> addUpdateUser(users_table userdata) {

        return userRepository.save(userdata);

    }

    public Mono<Void> deleteById(Long Id) {
        return userRepository.deleteById(Id)
                .map(user_detail -> {
                    if (user_detail == null) {
                        throw new UserNotFound("user already Deleted");
                    }
                    return user_detail;
                });
    }

    // return repository.findById(id)
    // .flatMap(p->productDtoMono.map(AppUtils::dtoToEntity)
    // .doOnNext(e->e.setId(id)))
    // .flatMap(repository::save)
    // .map(AppUtils::entityToDto);
    // refernce -

    public Mono<users_table> updateById(Long Id, users_table newuserdata) {
        return userRepository.findById(Id).flatMap(oldUserdata -> {
            // oldUserdata.setId(Id);
            // oldUserdata.setId(newuserdata.getId());
            // oldUserdata.setAddress(newuserdata.getAddress());
            if (oldUserdata == null)
                throw new UserNotFound("user not found");
            String email = newuserdata.getEmail();
            if (email != null) {
                oldUserdata.setEmail(email);
            }
            List<String> roles = newuserdata.getRoles();
            if (roles != null) {
                oldUserdata.setRoles( roles);
            }

            
            return userRepository.save(oldUserdata);
            // return oldUserdata;

        });
    }

}
