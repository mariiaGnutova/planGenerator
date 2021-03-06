package com.plangenerator.dataTransferObject;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepaymentDTO {
    @JsonIgnore
    private Long id;

    private LocalDateTime date;

    @NotNull(message = "Annuity can not be null!")
    private Double annuity;

    @NotNull(message = "Principal can not be null!")
    private Double principal;

    @NotNull(message = "Interest can not be null!")
    private Double interest;

    @NotNull(message = "Initial Outstanding Principal can not be null!")
    private Double initialOutstandingPrincipal;

    @NotNull(message = "Remaining Outstanding Principal can not be null!")
    private Double remainingOutstandingPrincipal;

    private RepaymentDTO(){}

    private RepaymentDTO(Long id, LocalDateTime date, Double annuity, Double principal, Double interest,
                         Double initialOutstandingPrincipal, Double remainingOutstandingPrincipal) {
        this.id = id;
        this.date = date;
        this.annuity = annuity;
        this.principal = principal;
        this.interest = interest;
        this.initialOutstandingPrincipal = initialOutstandingPrincipal;
        this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
    }

    public static RepaymentDTOBuilder newBuilder()
    {
        return new RepaymentDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public LocalDateTime getDate() {
        return date;
    }

    public double getAnnuity() {
        return annuity;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getInterest() {
        return interest;
    }

    public double getInitialOutstandingPrincipal() {
        return initialOutstandingPrincipal;
    }

    public double getRemainingOutstandingPrincipal() {
        return remainingOutstandingPrincipal;
    }

    public static class RepaymentDTOBuilder{
        private Long id;
        private LocalDateTime date;
        private Double annuity;
        private Double principal;
        private Double interest;
        private Double initialOutstandingPrincipal;
        private Double remainingOutstandingPrincipal;

        public RepaymentDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public RepaymentDTOBuilder setDate(LocalDateTime date){
            this.date = date;
            return this;
        }

        public RepaymentDTOBuilder setAnnuity(Double annuity) {
            this.annuity = annuity;
            return this;
        }

        public RepaymentDTOBuilder setPrincipal(Double principal) {
            this.principal = principal;
            return this;
        }

        public RepaymentDTOBuilder setInterest(Double interest) {
            this.interest = interest;
            return this;
        }

        public RepaymentDTOBuilder setInitialOutstandingPrincipal(Double initialOutstandingPrincipal) {
            this.initialOutstandingPrincipal = initialOutstandingPrincipal;
            return this;
        }

        public RepaymentDTOBuilder setRemainingOutstandingPrincipal(Double remainingOutstandingPrincipal) {
            this.remainingOutstandingPrincipal = remainingOutstandingPrincipal;
            return this;
        }

        public RepaymentDTO createRepaymentDTO()
        {
            return new RepaymentDTO(id, date, annuity, principal, interest,
                    initialOutstandingPrincipal, remainingOutstandingPrincipal);

        }

    }
}
