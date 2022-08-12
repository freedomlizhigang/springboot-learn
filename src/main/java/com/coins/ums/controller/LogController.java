package com.coins.ums.controller;


import com.coins.ums.request.LogRequest;
import com.coins.ums.serviceimpl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李志刚
 * @since 2020-04-02
 */
@UmsApiRestController("log")
public class LogController {
    @Autowired
    private LogServiceImpl logService;
    @GetMapping("/list")
    public Object getList(LogRequest logRequest){
        return logService.getList(logRequest);
    }
    @PostMapping("/clear")
    public Object postClear(){
        return logService.postClear();
    }
}

