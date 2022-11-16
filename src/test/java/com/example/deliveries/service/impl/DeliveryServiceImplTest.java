package com.example.deliveries.service.impl;

import com.example.deliveries.entity.Product;
import com.example.deliveries.entity.Provider;
import com.example.deliveries.exception.EntityNotFoundException;
import com.example.deliveries.exception.ProductNotFoundException;
import com.example.deliveries.model.CartModel;
import com.example.deliveries.repository.ProductRepository;
import com.example.deliveries.repository.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class DeliveryServiceImplTest {
    private final static Long PRODUCT_ID = 1284L;
    private final static Long PROVIDER_ID = 3278L;

    @Mock
    private SessionServiceImpl sessionService;
    @Mock
    private ProductRepository productRepo;
    @Mock
    private ProviderRepository providerRepo;

    @InjectMocks
    private DeliveryServiceImpl deliveryServiceImpl;

    @Test
    void testAddProduct() throws EntityNotFoundException {
        var givenHttpRequest = new MockHttpServletRequest();

        var givenProvider = new Provider();
        givenProvider.setId(PROVIDER_ID);

        var givenProduct = new Product();
        givenProduct.setId(PRODUCT_ID);
        givenProduct.setProvider(givenProvider);

        var expectedCartModel = new CartModel();
        expectedCartModel.getProducts().add(givenProduct);

        Mockito.when(sessionService.getSession(givenHttpRequest))
                .thenReturn(expectedCartModel);

        Mockito.when(productRepo.findById(PRODUCT_ID))
                .thenReturn(Optional.of(givenProduct));

        Mockito.when(providerRepo.findById(PROVIDER_ID))
                .thenReturn(Optional.of(givenProvider));

        assertEquals(expectedCartModel, deliveryServiceImpl.addProduct(givenHttpRequest, PRODUCT_ID));
    }

    @Test
    void testAddProductThrowProductNotFoundException() {
        var givenHttpRequest = new MockHttpServletRequest();

        var givenProvider = new Provider();
        givenProvider.setId(PROVIDER_ID);

        var givenProduct = new Product();
        givenProduct.setId(PRODUCT_ID);
        givenProduct.setProvider(givenProvider);

        var expectedCartModel = new CartModel();
        expectedCartModel.getProducts().add(givenProduct);

        Mockito.when(sessionService.getSession(givenHttpRequest))
                .thenReturn(expectedCartModel);

        Mockito.when(productRepo.findById(PRODUCT_ID))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> deliveryServiceImpl.addProduct(givenHttpRequest, PRODUCT_ID));
    }
}