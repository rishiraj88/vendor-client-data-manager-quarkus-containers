package de.anbieterundkunden.web.api;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import de.anbieterundkunden.data.entity.Vendor;
import de.anbieterundkunden.service.VendorService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
@Path("/rest/vendors")
public class VendorEndpoint {
    private final VendorService vendorService;
    public VendorEndpoint(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GET
    public List<Vendor> getVendors(@QueryParam("email")String email, @QueryParam("name")String name){
        if((email == null || email.isBlank()) && (name == null || name.isBlank())){
            return this.vendorService.getAllVendors();
        }else{
            List<Vendor> vendors = new ArrayList<>();
            if(!(email == null || email.isBlank()) && !(name == null || name.isBlank())){
                List<Vendor> vendorsFound = this.vendorService.getVendorsByEmailAndName(email, name);
                vendors.addAll(vendorsFound);
            }else if(!(email == null || email.isBlank())){
                List<Vendor> vendorsFound = this.vendorService.getVendorsByEmail(email);
                vendors.addAll(vendorsFound);
            }else{
                List<Vendor> vendorsFound = this.vendorService.getVendorsByName(name);
                vendors.addAll(vendorsFound);
            }
            return vendors;
        }
    }

    @Transactional
    @POST
    @ResponseStatus(201)
    public Vendor addVendor(Vendor vendor){
        this.vendorService.addVendor(vendor);
        return vendor;
    }

    @GET
    @Path("/{vendorId}")
    public Vendor getVendor(@RestPath("vendorId")long id){
        Vendor vendor = this.vendorService.getVendor(id);
        if (vendor == null){
            throw new WebApplicationException(404);
        }
        return vendor;
    }

    @PUT
    @Path("/{vendorId}")
    @ResponseStatus(204)
    @Transactional
    public void updateVendor(@RestPath("vendorId")long id, Vendor vendor){
        if (id != vendor.getId()){
            throw new WebApplicationException(400);
        }
        this.vendorService.updateVendor(vendor);
    }

    @DELETE
    @Path("/{vendorId}")
    @ResponseStatus(204)
    @Transactional
    public void deleteVendor(@RestPath("vendorId")long id){
        this.vendorService.deleteVendor(id);
    }
}