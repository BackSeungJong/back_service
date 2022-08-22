package com.back.service.util;


import org.apache.commons.collections4.map.ListOrderedMap;

public class TempleateMap extends ListOrderedMap<Object, Object> {

    private static final long serialVersionUID = 6723434363565852261L;

    @Override
    public Object put(Object key, Object value) {
        return super.put(StringUtil.convert2CamelCase((String) key), value);
    }

}
