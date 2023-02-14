package org.dieschnittstelle.ess.utils.jsonb;

///*
// * this provider allows the polymorphic type handling based on JsonTypeInfo
// */
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.cfg.MapperConfig;
//import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
//import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
//import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
//
//import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.ext.Provider;
//
//import org.apache.logging.log4j.Logger;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Provider
//@Consumes({"application/json", "application/*+json", "text/json"})
//@Produces({"application/json", "application/*+json", "text/json"})
public class ResteasyLaissezFaireJacksonProvider /*extends ResteasyJackson2Provider*/ {

//    protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ResteasyLaissezFaireJacksonProvider.class);
//
//    private ObjectMapper objectMapper;
//
//    public static class MyPolymorphicTypeValidator extends BasicPolymorphicTypeValidator {
//
//        public MyPolymorphicTypeValidator(Set<Class<?>> invalidBaseTypes, TypeMatcher[] baseTypeMatchers, NameMatcher[] subTypeNameMatchers, TypeMatcher[] subClassMatchers) {
//            super(invalidBaseTypes, baseTypeMatchers, subTypeNameMatchers, subClassMatchers);
//        }
//
//        @Override
//        public Validity validateSubType(MapperConfig<?> ctxt, JavaType baseType, JavaType subType) throws JsonMappingException {
//            logger.info("validateSubType(): " + subType + " against " + baseType);
//            return Validity.ALLOWED;
//        }
//
//        @Override
//        public Validity validateBaseType(MapperConfig<?> ctxt, JavaType baseType) {
//            logger.info("validateBaseType(): " + baseType);
//            return Validity.ALLOWED;
//        }
//
//    }
//
//
//    // https://www.baeldung.com/jackson-inheritance
//    // see https://cowtowncoder.medium.com/jackson-2-10-safe-default-typing-2d018f0ce2ba
//    // https://stackoverflow.com/questions/30362446/deserialize-json-with-jackson-into-polymorphic-types-a-complete-example-is-giv
//    public ResteasyLaissezFaireJacksonProvider() {
//        this.objectMapper = new ObjectMapper();
//         BasicPolymorphicTypeValidator.Builder builder =  BasicPolymorphicTypeValidator
//                .builder();
//        builder.allowIfBaseType("org.dieschnittstelle.ess.entities.crm.");
//        builder.allowIfSubType("org.dieschnittstelle.ess.entities.crm.");
//        builder.allowIfSubType("java.util.List");
//        builder.allowIfSubType("java.util.ArrayList");
//        builder.allowIfBaseType("java.util.List");
//        builder.allowIfBaseType("java.util.ArrayList");
//        BasicPolymorphicTypeValidator btv = builder.build();
//////        logger.debug("activate customised default typeing...");
//////        objectMapper.activateDefaultTyping(new MyPolymorphicTypeValidator(new HashSet<>(),
//////                new BasicPolymorphicTypeValidator.TypeMatcher[]{},
//////                new BasicPolymorphicTypeValidator.NameMatcher[]{},
//////                new BasicPolymorphicTypeValidator.TypeMatcher[]{}
//////                ), ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
////        objectMapper.activateDefaultTyping(btv, ObjectMapper.DefaultTyping.NON_FINAL);
////        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.PROPERTY);
//    }
//
//    @Override
//    public ObjectMapper locateMapper(Class<?> type, MediaType mediaType) {
//        logger.info("locateMapper(): for mediaType " + mediaType + " and java type " + type);
//        return objectMapper;
//    }

}
