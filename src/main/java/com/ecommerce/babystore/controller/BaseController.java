package com.ecommerce.babystore.controller;

import com.ecommerce.babystore.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Autowired
    protected BaseService baseService;
}
