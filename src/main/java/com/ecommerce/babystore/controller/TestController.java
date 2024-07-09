package com.ecommerce.babystore.controller;

import com.ecommerce.babystore.dto.request.BaseRequest;
import com.ecommerce.babystore.dto.request.TestRequest;
import com.ecommerce.babystore.dto.response.BaseResponse;
import com.ecommerce.babystore.service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController {
    @Autowired
    private TestService testService;

    @GetMapping("test")
    public ResponseEntity<BaseResponse> doTest(HttpServletRequest httpServletRequest, BaseRequest<TestRequest> baseRequest) {
        return ResponseEntity.ok(testService.doTest(baseRequest));
    }

    @GetMapping("test-get")
    public ResponseEntity<String> doTestGet() {
        return ResponseEntity.ok("Hello");
    }
}
