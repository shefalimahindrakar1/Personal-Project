package com.personalproject.core.models;

import com.personalproject.core.utils.Network;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

@Model(adaptables = Resource.class,
        adapters = FetchSingleUser.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FetchSingleUserImpl implements FetchSingleUser {

    final Logger log = LoggerFactory.getLogger(FetchSingleUserImpl.class);
    @Inject
    String url;
    String fname;
    String lname;
    String email;
    String avatar;
    @Override
    public String getUrl(){
        return "https://reqres.in/api/users/"+url;
    }


    @Override
    public String getMessage() throws IOException, JSONException {

        String response = Network.readJson(getUrl());
        JSONObject jsonObject =  new JSONObject(response);

        JSONObject data = (JSONObject) jsonObject.get("data");

        email = data.getString("email");
        fname = data.getString("first_name");
        lname = data.getString("last_name");
        avatar = data.getString("avatar");
        return response;
    }


    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAvatar() {
        //String imgPath = avatar.replaceAll("https://reqres.in/img/faces/","/content/dam/personalproject/");

        String imgPath = replaceURL(avatar);
        return imgPath;

    }

    public String replaceURL(String url){
        String damURI = "/content/dam/personalproject/";
        String damPath = damURI + url.substring(28,url.length());

        return damPath;

    }

}