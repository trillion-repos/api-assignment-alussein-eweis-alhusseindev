package com.ipaddressmanagement.ipaddressmanagementapi.Service;

import com.ipaddressmanagement.ipaddressmanagementapi.Entity.IPManagement;
import com.ipaddressmanagement.ipaddressmanagementapi.Exceptions.IPException;
import com.ipaddressmanagement.ipaddressmanagementapi.Repository.IPManagementRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class IPManagementService {

    private IPManagementRepository myIPManagementRepo;

    public IPManagementService(IPManagementRepository myIPManagementRepo){
        this.myIPManagementRepo = myIPManagementRepo;
    }


    public List<IPManagement> listIPs(){
        return myIPManagementRepo.findAll();
    }

    public String createIP(@RequestBody IPManagement ipAddress){
        try{
            String ipBlock = ipAddress.getIpAddress();

            if(ipBlock.contains("/")){
                String tokens[] = ipBlock.split("/");
                String firstToken = tokens[0];
                String[] splitted = firstToken.split("\\.");

                int secondToken = Integer.parseInt(tokens[1]);
                //String ipUntilBeforeLastBit;
                StringBuilder ipUntilBeforeLastBit = new StringBuilder();
                int count = 0;

                for(String j : splitted) {
                    //System.out.println("j: " + j);
                    int convertedToken = Integer.parseInt(j);
                    if (convertedToken > 255 || convertedToken < 0) {
                        return("IP Error: IP cannot be greater than 255 or less than 0");
                    }
                }

                //checking the host part of the CIDR block
                int secondTokenParsed = Integer.parseInt(String.valueOf(secondToken));
                if(secondTokenParsed > 255 || secondTokenParsed < 0){
                    return("IP Error: IP cannot be greater than 255  or less than 0");
                }


                for(String i : splitted){
                    count += 1;
                    ipUntilBeforeLastBit.append(i);
                    ipUntilBeforeLastBit.append(".");
                    if(count == 3){
                        break;
                    }
                }




                //System.out.println("First part of IP: " + firstToken);
                //System.out.println("First part of IP until before last bit: " + ipUntilBeforeLastBit);
                //System.out.println(secondToken);

                String firstIpPrefixLastBit = splitted[3];
                //System.out.println("firstIpPrefixLastBit: " + firstIpPrefixLastBit);
                int startFrom = Integer.parseInt(String.valueOf(firstIpPrefixLastBit));
                String ipPrefix;
                String ipHost;

                for(int i = startFrom; i <= secondToken; ++i){
                    ipPrefix = String.valueOf(i);
                    ipHost = String.valueOf(ipUntilBeforeLastBit);
                    ipHost = ipHost + ipPrefix;

                    //System.out.println(ipHost);
                    ipAddress.setIpAddress(ipHost);
                    myIPManagementRepo.save(ipAddress);

                }

                return "IP CIDR Saved Successfully!";
            }else if(!(ipBlock.matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}($|/(16|24))$"))){
                return "Please Enter a valid IP / CIDR Block!";
            } else{
                myIPManagementRepo.save(ipAddress);
                return "IP CIDR Saved Successfully!";
            }
        }catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
            return "Invalid IP Length!";
        }catch(Exception ex){
            return ex.toString();
        }

    }



    public String acquireIP(IPManagement ipAddressManagement, String ipAddress){
        return myIPManagementRepo.findById(ipAddress).map((myIpAddress) ->{
            myIpAddress.setIpStatus("acquired");
            myIPManagementRepo.save(myIpAddress);
            return "IP Acquired Successfully!";
        }).orElseGet(() ->{
            //ipAddressManagement.setIpAddress(ipAddress);
            //myIPManagementRepo.save(ipAddressManagement);
            return "IP you are trying To acquire was not found, it needs to be saved first to be acquired!";
        });
    }



    public String releaseIP(IPManagement ipAddressManagement, String ipAddress){
        return myIPManagementRepo.findById(ipAddress).map((myIpAddress) ->{
            myIpAddress.setIpStatus("available");
            myIPManagementRepo.save(myIpAddress);
            return "IP Released Successfully, IP is now available!";
        }).orElseGet(() ->{
            //ipAddressManagement.setIpAddress(ipAddress);
            //myIPManagementRepo.save(ipAddressManagement);
            return "IP you are trying To release was not found, it needs to be saved first to be released!";
        });
    }
}
