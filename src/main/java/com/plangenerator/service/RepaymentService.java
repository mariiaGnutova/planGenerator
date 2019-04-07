package com.plangenerator.service;

import com.plangenerator.entity.RepaymentDO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface RepaymentService {
    List<RepaymentDO> create(@RequestParam double amount, @RequestParam double rate, @RequestParam int duration,
                             @RequestParam Date startDate);
}
