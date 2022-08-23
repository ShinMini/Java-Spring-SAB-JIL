package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// this will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

// spring repository에서 지원하지 않는, User 테이블과 관련한 추가적인 sql query 문장이 필요할 경우 이곳에서 구현
public interface UserRepository extends CrudRepository<User, Integer> {

}
