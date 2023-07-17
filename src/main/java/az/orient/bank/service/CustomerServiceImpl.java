package az.orient.bank.service;

import az.orient.bank.dto.request.ReqCustomer;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.RespStatus;
import az.orient.bank.dto.response.Response;
import az.orient.bank.entity.Customers;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.exception.BankException;
import az.orient.bank.exception.ExceptionConstants;
import az.orient.bank.repository.CustomerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Response<List<RespCustomer>> getCustomerList() {
        Response<List<RespCustomer>> response = new Response<>();
        List<RespCustomer> respCustomerList = new ArrayList<>();
        try {
            List<Customers> customersList = customerRepository.findAllByActive(EnumAviableStatus.ACTIVE.value);
            if (customersList.isEmpty()) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            respCustomerList = customersList.stream().map(this::mapping).collect(Collectors.toList());
            response.setT(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Response<RespCustomer> getCustomerById(Long customerId) {
        Response<RespCustomer> response = new Response<>();
        RespCustomer respCustomer = new RespCustomer();
        try {
            if (customerId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid ");
            }
            Customers customer = customerRepository.findByIdAndActive(customerId, EnumAviableStatus.ACTIVE.value);
            if (customer == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            respCustomer = mapping(customer);
            response.setT(respCustomer);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Response addCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }
            Customers customers = Customers.builder()
                    .name(name)
                    .surname(surname)
                    .address(reqCustomer.getAddress())
                    .dob(reqCustomer.getDob())
                    .phone(reqCustomer.getPhone())
                    .pin(reqCustomer.getPin())
                    .seria(reqCustomer.getSeria())
                    .build();
            customerRepository.save(customers);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Response updateCustomer(ReqCustomer reqCustomer) {
        Response response = new Response();
        try {
            Long customerId = reqCustomer.getCustomerId();
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            if (customerId == null || name == null || surname == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }

            Customers customers = customerRepository.findByIdAndActive(customerId, EnumAviableStatus.ACTIVE.value);
            if (customers == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "CUSTOMER NOT FOUND");
            }
            customers.setName(name);
            customers.setSurname(surname);
            customers.setAddress(reqCustomer.getAddress());
            customers.setDob(reqCustomer.getDob());
            customers.setPhone(reqCustomer.getPhone());
            customers.setPin(reqCustomer.getPin());
            customers.setSeria(reqCustomer.getSeria());


            customerRepository.save(customers);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Response deleteCustomer(Long customerId) {
        Response response = new Response();
        try {

            if (customerId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }

            Customers customers = customerRepository.findByIdAndActive(customerId, EnumAviableStatus.ACTIVE.value);
            if (customers == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "CUSTOMER NOT FOUND");
            }
            customers.setActive(EnumAviableStatus.DEACTIVE.value);


            customerRepository.save(customers);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }


    private RespCustomer mapping(Customers customers) throws BankException {
        return RespCustomer.builder()
                .id(customers.getId())
                .name(customers.getName())
                .surname(customers.getSurname())
                .address(customers.getAddress())
                .dob(customers.getDob())
                .phone(customers.getPhone())
                .pin(customers.getPin())
                .seria(customers.getSeria())
                .build();
    }

}
