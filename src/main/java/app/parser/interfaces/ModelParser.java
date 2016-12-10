package app.parser.interfaces;

import org.modelmapper.PropertyMap;

/**
 * Created by niksun on 12/9/16.
 */
public interface ModelParser {

    <S,D> D convert(S source, Class<D> destinationClass);

    <S,D> D convert(S source, Class<D> destinationClass, PropertyMap<S,D> sdPropertyMap);
}
