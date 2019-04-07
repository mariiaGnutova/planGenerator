package com.plangenerator.controller;

import com.plangenerator.dataAccessObject.RepaymentDAO;
import com.plangenerator.dataTransferObject.RepaymentDTO;
import com.plangenerator.entity.RepaymentDO;
import com.plangenerator.mapper.RepaymentMapper;
import com.plangenerator.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * All operations with a repayment will be routed by this controller.
 * <p/>
 */
@RestController
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public  List<RepaymentDTO> createRepayment(@Valid @RequestParam(value = "loanAmount") double amount,
                                              @RequestParam( value = "nominalRate") double rate,
                                              @RequestParam( value = "duration") int duration,
                                              @RequestParam (value = "startDate") String startDate) throws EntityExistsException, ParseException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        LocalDate parsedDate = LocalDate.parse(startDate, formatter);
        System.out.println("Date:  " + parsedDate.plusMonths(3));

        return RepaymentMapper.makeRepaymentDTOList(repaymentService.create(amount, rate, duration, parsedDate));
    }
}
