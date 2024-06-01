package it.sportandreview.payment;

import it.sportandreview.exception.CreateEntityException;
import it.sportandreview.util.Sha256Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;

    }

    @Override
    public PaymentDTO create(PaymentDTO paymentDto) {
        if (Objects.nonNull(paymentDto.getId()) && paymentRepository.existsById(paymentDto.getId())){
            throw new CreateEntityException("payment", "Payment" + " not exists into database");
        }
        // Setting Payment UUID
        String uuid = StringUtils.isBlank(paymentDto.getUuid()) ? Sha256Utils.getEncodedRandomUUID() : paymentDto.getUuid();
        paymentDto.setUuid(uuid);
        PaymentDTO payment = paymentMapper.toDto( paymentRepository.save(paymentMapper.toEntity(paymentDto)));
        log.debug("payment created");
        return payment;
    }
}
