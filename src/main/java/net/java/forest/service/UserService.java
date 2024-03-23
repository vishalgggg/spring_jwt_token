package net.java.forest.service;

import net.java.forest.model.users_table;
import net.java.forest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<users_table> findByUsername(String username) {
        Mono<users_table> data = userRepository.findByEmail(username);
        return data.switchIfEmpty(Mono.empty());
    }

    public Flux<users_table> getAllUser() {
        return userRepository.findAll();
    }

    public Mono<users_table> addUpdateUser(users_table userdata) {
        return userRepository.save(userdata);
    }
    public Mono<Void> deleteById(Long Id)
    {
        return userRepository.deleteById(Id);
    }
    public Mono<users_table> updateById(Long Id,users_table newuserdata)
    {
        return userRepository.findById(Id).map(oldUserdata -> {
            oldUserdata.setId(Id);
            return oldUserdata;
    //         oldUserdata.setAddress(newuserdata.getAddress());
    //         oldUserdata.setEmail(newuserdata.getEmail());
    //         oldUserdata.setEnabled(newuserdata.getEnabled());
    //         oldUserdata.setFirstName(newuserdata.getFirstName());
    //         oldUserdata.setLastName(newuserdata.getLastName());
    //         oldUserdata.se

        });
    }
   

}
