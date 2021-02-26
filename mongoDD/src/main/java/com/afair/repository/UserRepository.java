package com.afair.repository;

import com.afair.pojo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author WangBing
 * @date 2020/12/18 16:16
 */
public interface UserRepository extends CrudRepository<User, String> {
    Page<User> findAllBySex(int sex, Pageable pageable);
}
