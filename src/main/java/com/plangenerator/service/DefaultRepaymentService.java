package com.plangenerator.service;

import com.plangenerator.dataAccessObject.RepaymentDAO;
import com.plangenerator.entity.RepaymentDO;
import com.plangenerator.init.DataInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;


/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultRepaymentService implements RepaymentService{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultRepaymentService.class);

    private final RepaymentDAO repaymentDAO;


    public DefaultRepaymentService(final RepaymentDAO repaymentDAO)
    {
        this.repaymentDAO = repaymentDAO;
    }

    /**
     * Creates a new repayment plan.
     *
     * @param amount
     * @param rate
     * @param duration
     * @param startDate
     * @return
     * @throws EntityExistsException if a car already exists with the given license plate, ... .
     */
    @Override
    public List<RepaymentDO> create(@RequestParam double amount,@RequestParam double rate, @RequestParam int duration,
                                    @RequestParam Date startDate) throws EntityExistsException
    {
        RepaymentDO repayment;

        try
        {
            DataInit dataInit = new DataInit(repaymentDAO, amount, rate, duration, startDate);
            dataInit.run();

        }
        catch (Exception e)
        {
            throw new EntityExistsException(e.getMessage());
        }

        return repaymentDAO.findAll();
    }
}
