package com.plangenerator.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.plangenerator.dataAccessObject.RepaymentDAO;
import com.plangenerator.dataTransferObject.RepaymentDTO;
import com.plangenerator.entity.RepaymentDO;
import com.plangenerator.mapper.RepaymentMapper;
import com.plangenerator.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * All operations with a repayment will be routed by this controller.
 * <p/>
 */
@RestController
@EnableWebMvc
@RequestMapping("v1/repayment")
public class MainController {

    private final RepaymentService repaymentService;
    private final RepaymentDAO repaymentDAO;


    @Autowired
    private MainController(final RepaymentService repaymentService, RepaymentDAO repaymentDAO){
        this.repaymentService = repaymentService;
        this.repaymentDAO = repaymentDAO;
    }

    @ResponseBody
    @RequestMapping("/")
    public String index(){
        Iterable<RepaymentDO> all = repaymentDAO.findAll();
        StringBuilder output = new StringBuilder();
        all.forEach(p -> output.append(p.getDate() + "<br>"));
        return output.toString();
    }

    @RequestMapping(value = "/response", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam(value = "loanAmount") double amount,
                                              @RequestParam( value = "nominalRate") double rate,
                                              @RequestParam( value = "duration") int duration,
                                              @RequestParam (value = "startDate") String startDate) throws EntityExistsException, ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDateTime parsedDate = LocalDateTime.parse(startDate, formatter);
        return RepaymentMapper.makeRepaymentDTOList(repaymentService.create(amount, rate, duration, parsedDate));
    }
}
