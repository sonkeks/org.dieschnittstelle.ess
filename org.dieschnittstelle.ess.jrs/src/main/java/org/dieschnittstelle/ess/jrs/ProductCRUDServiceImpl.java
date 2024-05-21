package org.dieschnittstelle.ess.jrs;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Context;
import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;

import java.util.List;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDServiceImpl.class);

	private GenericCRUDExecutor<AbstractProduct> productCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		logger.info("<constructor>: " + servletContext + "/" + request);

		this.productCRUD = (GenericCRUDExecutor<AbstractProduct>) servletContext.getAttribute("productCRUD");

		logger.debug("read out the touchpointCRUD from the servlet context: " + this.productCRUD);
	}

	@Override
	public AbstractProduct createProduct(
			AbstractProduct prod) {
		return (AbstractProduct) this.productCRUD.createObject(prod);
	}

	@Override
	public List<AbstractProduct> readAllProducts()  { return (List) this.productCRUD.readAllObjects(); }

	@Override
	public AbstractProduct updateProduct(long id,
			AbstractProduct update) {
		AbstractProduct prod = this.productCRUD.readObject(id);

		if (prod != null) {
			prod = this.productCRUD.updateObject(update);
			return prod;
		} else {
			throw new NotFoundException("The product with id " + id + " does not exist!");
		}
	}

	@Override
	public boolean deleteProduct(long id)  {
		return this.productCRUD.deleteObject(id);
	}

	@Override
	public AbstractProduct readProduct(long id) {
		AbstractProduct prod = (AbstractProduct) this.productCRUD.readObject(id);

		if (prod != null) {
			return prod;
		} else {
			throw new NotFoundException("The product with id " + id + " does not exist!");
		}
	}
	
}
