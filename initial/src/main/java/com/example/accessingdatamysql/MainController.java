package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// controller 라고 불리우는 요 친구가 사실상 web site의 router역할을 해줌. 
@Controller // This means that this class is a Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {
   // which is auto-generated by Spring, we will use it to handle the data
   /* 아무리봐도 Autowired 이거 뭔지 모르겠어서 찾아본 결과 
   필요한 의존 객체의 type에 해당하는 bean을 찾아 주입한다고 나와있다. 아무래도 react의 <SWITCH /> 와 유사하다고 생각하면 될 듯하다. 
   음.. 이때 필요한 의존 객체의 type.... 여기서는 User type이겠지? , bean..은 spring에서 만들어주는 함수라고 생각한다. ..
   예시에는 생성자, setter, filed 요 세개에 @Autowired를 사용할 수 있다고 나와있네용. 

   음 찾아보니 이 코드에서 @Autowired를 사용한 부분은 생성자 선언에 활용한 것같다. 
   그렇다면 왜 쓰는지 궁금해서 찾아본 결과 장점은 많지만 내게 와닿는 것만 추리면

   1. 필수적으로 사용해야 하는 레퍼런스 없이는 인스턴스를 만들지 못하도록 강제함 . -> 접근성 관리
   2. Circular Dependency / 순환 참조 의존성을 알아차릴 수 있음. -> 아직 잘 모르겠으나 상당히 강조해 적어둠. 
   [출처: https://devlog-wjdrbs96.tistory.com/166] 이거 설명 진짜 자세히 나와있다 정말 강추한다. 

   ...결국은 의존성 관리를 위해 Autowired를 사용하는 것같다. 
   */


   @Autowired // This means to get the bean called userRepository
   private UserRepository userRepository;

   @PostMapping(path = "/add") // Map ONLY POST requests
   public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String email) {
      // @ResponseBody means the returned String is the response, not a view name
      // @ResponseParam means it is a parameter from the GET or POST request

      User n = new User();
      n.setName(name);
      n.setEmail(email);
      userRepository.save(n);
      return "Saved";
   }

   @GetMapping(path = "/all")
   public @ResponseBody Iterable<User> getAllUsers() {
      // this returns a JSON or XML with the users
      return userRepository.findAll();
   }
}

/*
 *
 * GET 방식으로 curl 보내는 방법
 * -------------------------------
 * 
 * Invoke-WebRequest
 * "http://localhost:8888/api/Send?param1=PARAM1&param2=PARAM2"
 * Invoke-WebRequest -Method GET -Uri
 * http://localhost:8888/api/Send?param1=PARAM1&param2=PARAM2
 * 
 * 
 * ----------------------------------
 * 
 * POST 방식으로 curl 보내는 방법
 * -------------------------------
 * 
 * $postParams = @{username='me';moredata='qwerty'}
 * 
 * Invoke-WebRequest -Uri http://example.com/foobar -Method POST -Body
 * $postParams
 * 
 * -----------------------------------
 */

// 성공 --

// $postParams=@{name='hyeon min';email='gusals121234 @google.com'}

// Invoke-WebRequest -Uri http://localhost:8080/demo/add -Method POST -Body
// $postParams

// ------------- security 보안에 관하여

/*
 * 제품에 해커가 특정 정보를 강제로 injection하거나 drop시키는 것을 방지하기 위한 SQL단계의 보안 정책
 * 
 * mysql> revoke all on db_example.* from 'springuser'@'%';
 * // mysql database 접근권한에 제한을 둔다.
 * 위 제한으로 인해 spring user는 application에 아무 것도 할 수 없다.
 * 
 * // 하지만 application에 최소한의 권한부여가 필요한 경우
 * mysql> grant select, insert, delete, update on db_example.* to
 * 'springuser'@'%';
 * 
 * 위 문장으로 특정유저에 대한 dtatabase의 CRUD 작업권한을 부여할 수 있다.
 * 
 * 
 */