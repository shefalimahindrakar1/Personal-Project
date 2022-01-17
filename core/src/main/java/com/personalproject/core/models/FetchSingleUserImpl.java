package com.personalproject.core.models;

import com.personalproject.core.utils.Network;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = FetchSingleUser.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class FetchSingleUserImpl implements FetchSingleUser {


    @Inject
    String url;

    @Override
    public String getUrl(){
        return "https://reqres.in/api/users/"+url;
    }
   @Override
   public String getMessage() {

       return Network.readJson(getUrl());
   }

}
