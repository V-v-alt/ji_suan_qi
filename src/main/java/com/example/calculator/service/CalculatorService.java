package com.example.calculator.service;

import com.example.calculator.entity.OperationLog;
import com.example.calculator.repository.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculatorService {

    private final OperationLogRepository operationLogRepository;

    @Autowired
    public CalculatorService(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    public String calculate(double num1, double num2, String operator) {
        BigDecimal result;
        BigDecimal bdNum1 = BigDecimal.valueOf(num1);
        BigDecimal bdNum2 = BigDecimal.valueOf(num2);
        
        switch (operator) {
            case "+":
                result = bdNum1.add(bdNum2);
                break;
            case "-":
                result = bdNum1.subtract(bdNum2);
                break;
            case "*":
                result = bdNum1.multiply(bdNum2);
                break;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("除数不能为零");
                }
                result = bdNum1.divide(bdNum2, 10, RoundingMode.HALF_UP);
                break;
            default:
                throw new IllegalArgumentException("无效的运算符");
        }

        result = result.stripTrailingZeros();
        
        OperationLog log = new OperationLog(num1, num2, operator, result.doubleValue());
        operationLogRepository.save(log);

        return formatResult(result);
    }

    private String formatResult(BigDecimal result) {
        if (result.scale() <= 0) {
            return result.toBigInteger().toString();
        }
        return result.toPlainString();
    }
}
