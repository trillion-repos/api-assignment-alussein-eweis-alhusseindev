package com.ipaddressmanagement.ipaddressmanagementapi.ControllerTests;

import com.ipaddressmanagement.ipaddressmanagementapi.Controller.IPManagementController;
import com.ipaddressmanagement.ipaddressmanagementapi.Entity.IPManagement;
import com.ipaddressmanagement.ipaddressmanagementapi.Repository.IPManagementRepository;
import com.ipaddressmanagement.ipaddressmanagementapi.Service.IPManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import org.junit.jupiter.api.Assertions;

@WebMvcTest(IPManagementController.class)
public class IPManagementControllerTest {

    @MockBean
    private IPManagementService myIPManagementService;
    

    @Autowired
    private MockMvc mvcMock;

    @Test
    void controllerNotNullTest(){
        Assertions.assertNotNull(myIPManagementService);
    }


    @Test
    void newIPEndpointTest() throws Exception{
        RequestBuilder myRequest = MockMvcRequestBuilders.post("/new").content("10.0.0.1/24")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult myRequestResults = mvcMock.perform(myRequest).andReturn();
        Assertions.assertNotNull(myRequestResults);
    }


    @Test
    void listIPsEndpointTest() throws Exception{
        RequestBuilder myRequest = MockMvcRequestBuilders.get("/list");
        MvcResult myRequestResults = mvcMock.perform(myRequest).andReturn();

        Assertions.assertNotNull(myRequestResults.getResponse().getContentAsString());
    }


    @Test
    void acquireIPEndpointTest() throws Exception{

        RequestBuilder myRequest = MockMvcRequestBuilders.patch("/acquire/{ipAddress}", "10.0.0.1");
        MvcResult myRequestResults = mvcMock.perform(myRequest).andReturn();
        Assertions.assertEquals(myRequestResults.getResponse().getContentAsString() ,myRequestResults.getResponse().getContentAsString());
    }


    @Test
    void releaseIPEndpointTest() throws Exception{

        RequestBuilder myRequest = MockMvcRequestBuilders.patch("/release/{ipAddress}", "10.0.0.1");
        MvcResult myRequestResults = mvcMock.perform(myRequest).andReturn();
        Assertions.assertEquals(myRequestResults.getResponse().getContentAsString() ,myRequestResults.getResponse().getContentAsString());
    }
}
