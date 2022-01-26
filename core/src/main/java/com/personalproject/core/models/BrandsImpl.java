package com.personalproject.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Brands.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class BrandsImpl implements Brands {


    @ValueMapValue
    private List<String> pathfield;

    @Override
    public List<String> getPath() {
        if (pathfield != null) {
            return new ArrayList<>(pathfield);
        } else {
            return Collections.emptyList();
        }
    }
}
