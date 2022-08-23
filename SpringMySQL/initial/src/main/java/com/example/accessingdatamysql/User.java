package com.example.accessingdatamysql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User {
   @Id // id값 인지 -> spring framework
   @GeneratedValue(strategy = GenerationType.AUTO) // 자동으로 값 생성
   private Integer id;

   private String name;

   private String email;

   // ---------- GETTER -------------
   public Integer getId() {
      return id;
   }

   public String getEmail() {
      return email;
   }

   public String getName() {
      return name;
   }

   // ---------- SETTER -------------
   public void setId(Integer id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setEmail(String email) {
      this.email = email;
   }
}

/*
 * spring framework를 사용해 JPA에서 자동으로 만들어준 user table
 * 
 * mysql> desc user;
 * 
 * +-------+--------------+------+-----+---------+-------+
 * | Field | Type | Null | Key | Default | Extra |
 * +-------+--------------+------+-----+---------+-------+
 * | id | int | NO | PRI | NULL | |
 * | email | varchar(255) | YES | | NULL | |
 * | name | varchar(255) | YES | | NULL | |
 * +-------+--------------+------+-----+---------+-------+
 * 
 * 
 * // 얘는 아직 뭔지 모르겠음.
 * // column명과 vlaue로 유츄해보았을 때 user talbe의 main id값에 들어갈 숫자를 표기하기 위한 테이블인 것같음.
 * 
 * mysql> desc hibernate_sequence;
 * 
 * +----------+--------+------+-----+---------+-------+
 * | Field | Type | Null | Key | Default | Extra |
 * +----------+--------+------+-----+---------+-------+
 * | next_val | bigint | YES | | NULL | |
 * +----------+--------+------+-----+---------+-------+
 */