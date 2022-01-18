package com.ipaddressmanagement.ipaddressmanagementapi.RepositoryTests;

import com.ipaddressmanagement.ipaddressmanagementapi.Entity.IPManagement;
import com.ipaddressmanagement.ipaddressmanagementapi.Repository.IPManagementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class IPManagementRepositoryTest {

    @Autowired
    private IPManagementRepository myIPManagementRepository;

    @Test
    void iPManagementRepositorySavingTest(){
        IPManagement myIPManagement = new IPManagement(
                "10.0.0.1/24",
                "available"
        );
        myIPManagementRepository.save(myIPManagement);
        Assertions.assertNotNull(myIPManagementRepository);
    }

}
