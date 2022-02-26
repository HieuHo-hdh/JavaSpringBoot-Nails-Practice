package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.orders.OrdersDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.orders.CreateOrdersDetailForm;
import com.landingis.api.form.orders.CreateOrdersForm;
import com.landingis.api.mapper.AccountMapper;
import com.landingis.api.mapper.OrdersDetailMapper;
import com.landingis.api.mapper.OrdersMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.OrdersCriteria;
import com.landingis.api.storage.model.*;
import com.landingis.api.storage.repository.*;
import com.landingis.api.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class OrdersController extends ABasicController{

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrdersDetailRepository ordersDetailRepository;

    @Autowired
    OrdersMapper ordersMapper;

    @Autowired
    OrdersDetailMapper ordersDetailMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @Autowired
    AccountMapper accountMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<OrdersDto>> getList(OrdersCriteria ordersCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ORDERS_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<OrdersDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Orders> listOrders = ordersRepository.findAll(ordersCriteria.getSpecification(), pageable);

        ResponseListObj<OrdersDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(ordersMapper.fromEntityListToOrdersDtoList(listOrders.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listOrders.getTotalPages());
        responseListObj.setTotalElements(listOrders.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List orders success");
        return responseListObjApiMessageDto;
    }
    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<OrdersDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.ORDERS_ERROR_UNAUTHORIZED, "Not allow get orders");
        }
        ApiMessageDto<OrdersDto> result = new ApiMessageDto<>();
        Orders orders = ordersRepository.findById(id).orElse(null);

        if (orders == null)
        {
            throw new RequestException(ErrorCode.ORDERS_ERROR_NOT_FOUND, "Not found orders");
        }
        result.setData(ordersMapper.fromEntityToOrdersDto(orders));
        result.setMessage("Set Orders Success");
        return result;
    }

    //If phone is new, not belongs to any customers => create a customer with that phone
    //Else => return found customer

    private Customer createNewCustomerWithPhoneNotFound (String phone, String email, String address, String fullName)
    {
        Customer customer = customerRepository.findByAccountPhone(phone);
        //Account => phone, email, address, fullName, group, kind, status => Customer: account + address
        if (customer == null)
        {
            Account account = new Account ();

            account.setPhone(phone);
            account.setEmail(email);
            account.setFullName(fullName);

            Group group = groupRepository.findFirstByKind(LandingISConstant.GROUP_KIND_CUSTOMER);
            account.setGroup(group);
            account.setKind(LandingISConstant.USER_KIND_CUSTOMER);
            account.setStatus(LandingISConstant.STATUS_ACTIVE);

            customer = new Customer();
            customer.setAddress(address);
            customer.setAccount(account);

            customerRepository.save(customer);
        }
        return customer;
    }

    //Randomly generate a 8-digit Code
    private String generateCode() {
        String code = StringUtils.generateRandomString(8);
        return code;
    }

    //Calculate TotalPrice = (eact ordersDetail in ordersDetailList where price * amount) - discount + Vat
    //**: Must set price for ordersDetailList (taken from productList by productId) first
    private Double calculateTotalPriceOrders(
            List<OrdersDetail> ordersDetailList,
            Integer saleOff,
            Integer vat
    ) {
        double totalPrice = 0d;
        for (OrdersDetail ordersDetail: ordersDetailList)
        {
            totalPrice += ordersDetail.getPrice() * ordersDetail.getAmount();
        }
        double priceAfterSaleOff = totalPrice * (1 - saleOff/100);

        double priceAfterVAT = priceAfterSaleOff * (1 + vat/100);
        return priceAfterVAT;
    }

    private void setPriceOrderDetailList(List<OrdersDetail> ordersDetailList) {
        for (int i = 0; i < ordersDetailList.size(); i++) {
            OrdersDetail ordersDetail = ordersDetailList.get(i);
            Product product = productRepository.findById(ordersDetail.getProduct().getId()).orElse(null);
            if(product == null) {
                throw new RequestException(ErrorCode.ORDERS_ERROR_NOT_FOUND_PRODUCT);
            }
            ordersDetail.setPrice(product.getPrice() * (1 - product.getSaleOff() / 100.0));
            ordersDetailList.set(i, ordersDetail);
        }
    }

    //  ordersAddress, ordersDocument, ordersSaleOff from createForm
    //  ordersState, ordersPrevState – Set as state.created
    //  ordersVat – fixed value
    //  ordersCode, - generateCode()
    //  ordersTotalMoney – calculateTotalPriceOrders()
    //  set Customer and his Account - customerEmail, customerFullName, customerPhone, ordersAddress

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateOrdersForm createOrdersForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ORDERS_ERROR_UNAUTHORIZED, "Not allow to create");
        }

        List<CreateOrdersDetailForm> createOrdersDetailFormList = createOrdersForm.getOrdersDetailDtos();
        //ordersDetailList: for calculating price and save into ordersDetailRepository
        List<OrdersDetail> ordersDetailList = ordersDetailMapper.fromCreateOrdersFormToEntityList(createOrdersDetailFormList);

        //Set price for all orders
        setPriceOrderDetailList(ordersDetailList);


        Orders orders = ordersMapper.fromCreateOrdersFormToEntity(createOrdersForm);
        orders.setCustomer(createNewCustomerWithPhoneNotFound(createOrdersForm.getCustomerPhone(), createOrdersForm.getCustomerEmail(), createOrdersForm.getOrdersAddress(), createOrdersForm.getCustomerFullName()));

        orders.setState(LandingISConstant.ORDERS_STATE_CREATED);
        orders.setPrevState(LandingISConstant.ORDERS_STATE_CREATED);
        orders.setVat(LandingISConstant.VAT);

        //Calculate TotalMoney
        orders.setTotalMoney(calculateTotalPriceOrders(ordersDetailList, orders.getSaleOff(), orders.getVat()));

        //Auto generate Code
        orders.setCode(generateCode());
        Orders createdOrders = ordersRepository.save(orders);
        // Save all orders detail
        for (int i = 0; i < ordersDetailList.size(); i++) {
            OrdersDetail ordersDetail = ordersDetailList.get(i);
            ordersDetail.setOrders(createdOrders);
            ordersDetailList.set(i, ordersDetail);
        }
        ordersDetailRepository.saveAll(ordersDetailList);


        ApiMessageDto<String> result = new ApiMessageDto<>();
        result.setMessage("Create orders success");
        return result;

    }



}
