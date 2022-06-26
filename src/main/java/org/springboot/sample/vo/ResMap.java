package org.springboot.sample.vo;

import org.apache.commons.collections.map.ListOrderedMap;
//import org.springframework.util.StringUtils;

import java.io.Serializable;

public class ResMap  extends ListOrderedMap implements Serializable {

    private static final long serialVersionUID = 77777763333331111L;

    public Object put(Object key, Object value) {
        return super.put(key, value);
//        return super.put(StringUtils.lowerCase((String) key), value);
    }

}
