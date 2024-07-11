package com.ecommerce.babystore.controller;

import com.ecommerce.babystore.dto.request.BaseRequest;
import com.ecommerce.babystore.dto.request.TestRequest;
import com.ecommerce.babystore.dto.response.BaseResponse;
import com.ecommerce.babystore.entity.Role;
import com.ecommerce.babystore.repository.RoleRepository;
import com.ecommerce.babystore.service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
    @Autowired
    private TestService testService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("test")
    public ResponseEntity<BaseResponse> doTest(HttpServletRequest httpServletRequest, BaseRequest<TestRequest> baseRequest) {
        return ResponseEntity.ok(testService.doTest(baseRequest));
    }

    @GetMapping("test-get")
    public ResponseEntity<String> doTestGet() {
        Role role = new Role();
        role.setRoleName("USER");
        role.setStatus(true);
        role.setDescription("Tôi là role user");
        role.setCode("ROLE_USER_V1");
        roleRepository.save(role);
        return ResponseEntity.ok("Ôk");
    }
}
