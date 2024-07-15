package org.dieschnittstelle.ess.mip.components.erp.crud.api;

import java.util.List;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/*
 * TODO MIP+JPA1/2/5:
 * this interface shall be implemented using an ApplicationScoped CDI bean with an EntityManager.
 * See TouchpointCRUDImpl for an example bean with a similar scope of functionality
 */

@RegisterRestClient
@Path("/products")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public interface ProductCRUD {

	@POST
	public AbstractProduct createProduct(AbstractProduct prod);
	@GET
	public List<AbstractProduct> readAllProducts();
	@PUT
	public AbstractProduct updateProduct(AbstractProduct update);
	@GET
	@Path("/{id}")
	public AbstractProduct readProduct(@PathParam("id") long productID);
	@DELETE
	@Path("/{id}")
	public boolean deleteProduct(@PathParam("id") long productID);
	@GET
	@Path("/{id}/campaings")
	public List<Campaign> getCampaignsForProduct(@PathParam("id") long productID);

}
