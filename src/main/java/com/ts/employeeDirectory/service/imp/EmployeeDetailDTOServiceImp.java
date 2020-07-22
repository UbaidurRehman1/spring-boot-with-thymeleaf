package com.ts.employeeDirectory.service.imp;

import com.ts.employeeDirectory.dto.EmployeeDetailDTO;
import com.ts.employeeDirectory.exception.ManOfMonthNotFoundException;
import com.ts.employeeDirectory.exception.QueryResultNotUniqueException;
import com.ts.employeeDirectory.exception.UserNotFoundException;
import com.ts.employeeDirectory.repo.EmployeeRepo;
import com.ts.employeeDirectory.service.EmployeeDetailDTOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailDTOServiceImp implements EmployeeDetailDTOService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDetailDTOService.class);
    private final EmployeeRepo employeeRepo;

    public EmployeeDetailDTOServiceImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeDetailDTO getManOfTheMonthEmployee() {
        LOGGER.info("Getting Man of the Month Employee");
        try {
            EmployeeDetailDTO employeeDetailDTO = employeeRepo.findByManOfMonthTrue().orElseThrow(() -> {
                throw new ManOfMonthNotFoundException("Man of Month Not Found");
            }).getEmployeeDetailDTO();
            LOGGER.info("Found Man of the Month: {}", employeeDetailDTO);
            return employeeDetailDTO;
        } catch (ManOfMonthNotFoundException manOfTheMonthEmployee) {
            throw manOfTheMonthEmployee;
        } catch (Exception exp) {
            throw new QueryResultNotUniqueException("There are more than one man of the month. Delete them so, that, only one remains");
        }
    }

    @Override
    public EmployeeDetailDTO get(Long id) {
        LOGGER.info("Finding Employee Details of id {}", id);
        EmployeeDetailDTO employeeDetailDTO = employeeRepo.findById(id).orElseThrow(() -> {
            throw new UserNotFoundException("The request user not found");
        }).getEmployeeDetailDTO();
        LOGGER.info("Employee Detail Found: {}", employeeDetailDTO);
        return employeeDetailDTO;
    }

}
