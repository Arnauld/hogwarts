package hogwarts.step;

import org.apache.commons.lang.StringUtils;

public enum NumericalAccessorKey {
    First,
    Second, 
    Third, 
    Fourth, 
    Fifth, 
    Sixth, 
    Seventh, 
    Eighth, 
    Ninth, 
    Tenth;
    
    public static NumericalAccessorKey lookupIgnoringCase(String key) {
        for(NumericalAccessorKey ekey : values()) {
            if(StringUtils.equalsIgnoreCase(key, ekey.name()))
                return ekey;
        }
        return null;
    }
}
