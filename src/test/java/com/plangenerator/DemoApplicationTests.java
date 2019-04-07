package com.plangenerator;

import com.google.common.collect.Iterators;
import com.plangenerator.dataAccessObject.RepaymentDAO;
import com.plangenerator.entity.RepaymentDO;
import com.plangenerator.service.DefaultRepaymentService;
import com.plangenerator.service.RepaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
//	private RepaymentService repaymentService;
//	private RepaymentDAO repaymentDAO;
//	private double longAmount;
//	private double debitInterest;
//	private int duration;
//	private LocalDate startDate;
//	private double initialOutstandingPrincipal;
//	private double annuity;
//	private boolean firstInitial;
//	private LocalDate date;
//	private long newID = 1;
//
//	@Autowired
//	public void setRepaymentService (RepaymentService repaymentService){
//		this.repaymentService = repaymentService;
//	}
//
//	@Autowired
//	public void setRepaymentDAO (RepaymentDAO repaymentDAO){
//		this.repaymentDAO = repaymentDAO;
//	}
//
//	@Test
//	public void contextLoads() {
//		assertThat(repaymentService, instanceOf(DefaultRepaymentService.class));
//	}
//
//	@Test
//	public void testCreateRepaymentInit(){
//		RepaymentDO newRepayment = new RepaymentDO();
//		newID = Iterators.size(repaymentDAO.findAll().iterator());
//		newRepayment.setAnnuity(219.36);
//		newRepayment.setDate(LocalDate.now());
//		newRepayment.setPrincipal(198.53);
//		newRepayment.setInitialOutstandingPrincipal(5000);
//		newRepayment.setInterest(5);
//		newRepayment.setRemainingOutstandingPrincipal(4801.47);
//		newRepayment.setId(newID);
//		assertNotNull(repaymentDAO.save(newRepayment));
//
//	}
//
////	@Test(expected = EntityNotFoundException.class)
//	@Test
//	public void testCreateRepaymentInitNegativ(){
//		RepaymentDO newRepayment = new RepaymentDO();
//		newID = Iterators.size(repaymentDAO.findAll().iterator());
//		newRepayment.setAnnuity(219.36);
//		newRepayment.setDate(LocalDate.now());
//		newRepayment.setPrincipal(198.53);
//	//	newRepayment.setInitialOutstandingPrincipal(5000);
//		newRepayment.setInterest(5);
//		newRepayment.setRemainingOutstandingPrincipal(4801.47);
//		newRepayment.setId((long) 0);
//		repaymentDAO.save(newRepayment);
//		assertNull(newRepayment);
//		assertNotNull(repaymentDAO);
//
//	}

}
