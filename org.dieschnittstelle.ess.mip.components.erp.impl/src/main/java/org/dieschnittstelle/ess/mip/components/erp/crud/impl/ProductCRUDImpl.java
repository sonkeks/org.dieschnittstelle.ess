package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.Campaign;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.List;

import static org.dieschnittstelle.ess.utils.Utils.show;

@ApplicationScoped
@Transactional
@Logged
public class ProductCRUDImpl implements ProductCRUD {

    @Inject
    @EntityManagerProvider.ERPDataAccessor
    private EntityManager em;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        em.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        Query qu = em.createQuery("SELECT DISTINCT prod FROM AbstractProduct prod");
        return qu.getResultList();
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        AbstractProduct existingProd = em.find(AbstractProduct.class, update.getId());
        if (existingProd != null) {
            existingProd.setName(update.getName());
            existingProd.setPrice(update.getPrice());
            AbstractProduct mergedProd = em.merge(existingProd);
            show("Updated product: " + update.getName());
            return mergedProd;
        } else {
            show("Product to update does not exist");
            return null;
        }
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return em.find(AbstractProduct.class, productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        AbstractProduct existingProd = em.find(AbstractProduct.class, productID);
        if (existingProd != null) {
            em.remove(existingProd);
            show("Deleted product: " + productID);
            return true;
        } else {
            show("Product to delete does not exist");
            return false;
        }
    }

    @Override
    public List<Campaign> getCampaignsForProduct(long productID) {
        Query qu = em.createQuery("SELECT DISTINCT camp FROM Campaign camp JOIN camp.bundles bund WHERE bund.product.id = :productID");
        qu.setParameter("productID", productID);
        return qu.getResultList();
    }
}
