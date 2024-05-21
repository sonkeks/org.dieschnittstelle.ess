package org.dieschnittstelle.ess.basics;


import org.dieschnittstelle.ess.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.ess.basics.annotations.DisplayAs;
import org.dieschnittstelle.ess.basics.annotations.StockItemProxyImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static org.dieschnittstelle.ess.utils.Utils.*;

public class ShowAnnotations {

	public static void main(String[] args) {
		// we initialise the collection
		StockItemCollection collection = new StockItemCollection(
				"stockitems_annotations.xml", new AnnotatedStockItemBuilder());
		// we load the contents into the collection
		collection.load();

		for (IStockItem consumable : collection.getStockItems()) {
			showAttributes(((StockItemProxyImpl)consumable).getProxiedObject());
		}

		// we initialise a consumer
		Consumer consumer = new Consumer();
		// ... and let them consume
		consumer.doShopping(collection.getStockItems());
	}

	/*
	 * TODO BAS2
	 */
	private static void showAttributes(Object instance) {
		show("class is: " + instance.getClass());

		try {
			// TODO BAS2: create a string representation of instance by iterating
			//  over the object's attributes / fields as provided by its class
			//  and reading out the attribute values. The string representation
			//  will then be built from the field names and field values.
			//  Note that only read-access to fields via getters or direct access
			//  is required here.

			// TODO BAS3: if the new @DisplayAs annotation is present on a field,
			//  the string representation will not use the field's name, but the name
			//  specified in the the annotation. Regardless of @DisplayAs being present
			//  or not, the field's value will be included in the string representation.

			//BAS2:
			Class klass = instance.getClass();
			Field[] fields = klass.getDeclaredFields();
			StringBuilder fieldString = new StringBuilder();

			// iterate over fields of the instance
			for(int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				// allow access to private fields
				field.setAccessible(true);
				// extract name and value of field
				String name = field.getName();
				Object value = field.get(instance);

				//BAS3:
				//check for existence of DisplayAs annotation
				if (field.isAnnotationPresent(DisplayAs.class)) {
					// replace name with annotation value
					name = field.getAnnotation(DisplayAs.class).value();
				}

				// format key value pair
				fieldString.append(String.format("%s:%s", name, value));
				// concatenate with ','
				if (i < fields.length - 1) {
					fieldString.append(", ");
				}
			}

			show("{" + klass.getSimpleName() + " " + fieldString + "}");

		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("showAnnotations(): exception occurred: " + e,e);
		}

	}

}
