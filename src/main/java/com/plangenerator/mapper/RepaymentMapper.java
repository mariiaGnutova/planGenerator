package com.plangenerator.mapper;

import com.plangenerator.dataTransferObject.RepaymentDTO;
import com.plangenerator.entity.RepaymentDO;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RepaymentMapper {


    public static RepaymentDO makeRepaymentDO(RepaymentDTO repaymentDTO)
    {
        return new RepaymentDO(repaymentDTO.getDate(),
                repaymentDTO.getAnnuity(),
                repaymentDTO.getPrincipal(),
                repaymentDTO.getInterest(),
                repaymentDTO.getInitialOutstandingPrincipal(),
                repaymentDTO.getRemainingOutstandingPrincipal()
        );
    }

    public static RepaymentDTO makeRepaymentDTO(RepaymentDO repaymentDO) {
        RepaymentDTO.RepaymentDTOBuilder repaymentDTOBuilder = RepaymentDTO.newBuilder()
                .setId(repaymentDO.getId())
                .setDate(repaymentDO.getDate())
                .setAnnuity(repaymentDO.getAnnuity())
                .setPrincipal(repaymentDO.getPrincipal())
                .setInterest(repaymentDO.getInterest())
                .setInitialOutstandingPrincipal(repaymentDO.getInitialOutstandingPrincipal())
                .setRemainingOutstandingPrincipal(repaymentDO.getRemainingOutstandingPrincipal());
        return repaymentDTOBuilder.createRepaymentDTO();
    }


    public static List<RepaymentDTO> makeRepaymentDTOList(Collection<RepaymentDO> drivers)
    {
        return drivers.stream()
                .map(RepaymentMapper::makeRepaymentDTO)
                .collect(Collectors.toList());
    }
}
