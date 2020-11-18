package com.haotian.admin.controller.v1;

import com.haotian.admin.service.AdSensitiveService;
import com.haotian.apis.admin.AdSensitiveControllerApi;
import com.haotian.model.admin.dtos.SensitiveDto;
import com.haotian.model.admin.pojos.AdSensitive;
import com.haotian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/sensitive")
public class AdSensitiveController implements AdSensitiveControllerApi {

    @Autowired
    private AdSensitiveService adSensitiveService;


    @PostMapping("/list")
    @Override
    public ResponseResult list(@RequestBody SensitiveDto dto) {
        return adSensitiveService.list(dto);
    }

    @PostMapping("/save")
    @Override
    public ResponseResult save(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.insert(adSensitive);
    }

    @PostMapping("/update")
    @Override
    public ResponseResult update(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.update(adSensitive);
    }

    @DeleteMapping("del/{id}")
    @Override
    public ResponseResult deleteById(@PathVariable("id") Integer id) {
        return adSensitiveService.deleteById(id);
    }
}
