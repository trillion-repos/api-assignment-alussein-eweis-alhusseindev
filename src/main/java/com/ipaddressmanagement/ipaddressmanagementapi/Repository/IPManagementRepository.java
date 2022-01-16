package com.ipaddressmanagement.ipaddressmanagementapi.Repository;

import com.ipaddressmanagement.ipaddressmanagementapi.Entity.IPManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPManagementRepository extends JpaRepository<IPManagement, String> {
}
