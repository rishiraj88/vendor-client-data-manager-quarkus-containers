package de.anbieterundkunden.web.graphql;

import de.anbieterundkunden.data.entity.Vendor;
import de.anbieterundkunden.service.VendorService;
import de.anbieterundkunden.web.graphql.input.VendorInput;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class VendorResource {

    private final VendorService vendorService;

    public VendorResource(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Query("allVendors")
    @Description("Gets the data of all the vendors")
    public List<Vendor> getAllVendors(){
        return this.vendorService.getAllVendors();
    }

    @Query("vendorsByEmail")
    @Description("Lists all vendors, matched by email address")
    public List<Vendor> getVendorsByEmail(@Name("email")String email){
        return this.vendorService.getVendorsByEmail(email);
    }

    @Query("vendorsByName")
    @Description("Lists all vendors, matched by name")
    public List<Vendor> getVendorsByName(@Name("name")String name){
        return this.vendorService.getVendorsByName(name);
    }

    @Query("vendorsByNameAndEmail")
    @Description("Lists all vendors, matched by name and email address both")
    public List<Vendor> getVendorsByNameAndEmail(@Name("name")String name, @Name("email")String email){
        return this.vendorService.getVendorsByEmailAndName(email, name);
    }

    @Mutation("addVendor")
    @Description("Save the details of a vendor")
    public Vendor addVendor(VendorInput vendorInput){
        return this.vendorService.addVendor(vendorInput.getEntity());
    }

    @Query("getVendor")
    @Description("Gets the details of a vendor, matched by vendor ID")
    public Vendor getVendor(@Name("id")long id){
        return this.vendorService.getVendor(id);
    }

    @Mutation("updateVendor")
    @Description("Updates the details of a vendor")
    public Vendor updateVendor(Vendor vendor){
        return this.vendorService.updateVendor(vendor);
    }

    @Mutation("deleteVendor")
    @Description("Removes a vendor record")
    public Vendor deleteVendor(@Name("id")long id){
        Vendor vendor = this.vendorService.getVendor(id);
        this.vendorService.deleteVendor(id);
        return vendor;
    }
}