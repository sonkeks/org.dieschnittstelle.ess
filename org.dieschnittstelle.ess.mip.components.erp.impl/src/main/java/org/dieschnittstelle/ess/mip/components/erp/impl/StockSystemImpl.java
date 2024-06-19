package org.dieschnittstelle.ess.mip.components.erp.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.PointOfSaleCRUD;
import org.dieschnittstelle.ess.mip.components.erp.crud.impl.StockItemCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@Transactional
@Logged
public class StockSystemImpl implements StockSystem {

    @Inject
    private StockItemCRUD stockItemCRUD;
    @Inject
    private PointOfSaleCRUD posCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);
        if (stockItem != null) {
            stockItem.setUnits(stockItem.getUnits() + units);
            stockItemCRUD.updateStockItem(stockItem);
        } else {
            stockItem = new StockItem(product, pos, units);
            stockItemCRUD.createStockItem(stockItem);
        }
    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);
        if (stockItem != null) {
            stockItem.setUnits(stockItem.getUnits() - units);
            stockItemCRUD.updateStockItem(stockItem);
        } else {
            stockItem = new StockItem(product, pos, units);
            stockItemCRUD.createStockItem(stockItem);
        }
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List<StockItem> stockItems = stockItemCRUD.readStockItemsForPointOfSale(pos);

        List<IndividualisedProductItem> products = new ArrayList<>();
        for (StockItem stockItem : stockItems) {
            products.add(stockItem.getProduct());
        }

        return products;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        Set<IndividualisedProductItem> products = new HashSet<>();

        List<PointOfSale> pointsOfSale = posCRUD.readAllPointsOfSale();
        for(PointOfSale pos : pointsOfSale) {
            List<StockItem> stockItemsForPos = stockItemCRUD.readStockItemsForPointOfSale(pos);
            for(StockItem stockItem : stockItemsForPos) {
                products.add(stockItem.getProduct());
            }
        }

        return new ArrayList<>(products);
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);
        return stockItem != null ? stockItem.getUnits() : 0;
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<StockItem> stockItemsForProd = stockItemCRUD.readStockItemsForProduct(product);
        int totalUnits = 0;
        for(StockItem stockItem : stockItemsForProd) {
            totalUnits += stockItem.getUnits();
        }
        return totalUnits;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<StockItem> stockItemsForProd = stockItemCRUD.readStockItemsForProduct(product);
        List<Long> pointsOfSaleIds = new ArrayList<>();
        for(StockItem stockItem : stockItemsForProd) {
            pointsOfSaleIds.add(stockItem.getProduct().getId());
        }
        return pointsOfSaleIds;
    }
}
