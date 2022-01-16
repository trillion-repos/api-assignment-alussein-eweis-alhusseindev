package com.ipaddressmanagement.ipaddressmanagementapi.Controller;

import com.ipaddressmanagement.ipaddressmanagementapi.Entity.IPManagement;
import com.ipaddressmanagement.ipaddressmanagementapi.Repository.IPManagementRepository;
import com.ipaddressmanagement.ipaddressmanagementapi.Service.IPManagementService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class IPManagementController {

    private IPManagementService myIPManagementService;

    //constructor
    public IPManagementController(IPManagementService myIPManagementService){
        this.myIPManagementService = myIPManagementService;
    }

    
    @GetMapping("/list")
    public List<IPManagement> listIPs(){
        return myIPManagementService.listIPs();
    }

    @PostMapping("/new")
    public String createIP(@RequestBody IPManagement ipAddress){
        return myIPManagementService.createIP(ipAddress);
    }



     @PatchMapping("/acquire/{ipAddress}")
     public String acquireIP(@RequestBody IPManagement ipAddressManagement, @PathVariable String ipAddress){
        return myIPManagementService.acquireIP(ipAddressManagement, ipAddress);
     }




     @PatchMapping("/release/{ipAddress}")
     public String releaseIP(@RequestBody IPManagement ipAddressManagement, @PathVariable String ipAddress){
         return myIPManagementService.releaseIP(ipAddressManagement, ipAddress);
     }



}
