package com.personalproject.core.models;

import com.personalproject.core.config.OsgiMultiple;
import com.personalproject.core.utils.Network;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = Resource.class,
        adapters = MultiUserList.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiUserListImpl implements MultiUserList{
    @OSGiService
    OsgiMultiple osgiMultiple;

    @Inject
    String id;

    @Override
    public List<Map<String, String>> getData() throws JSONException, IOException {

        String response = Network.readJson(getUrl());
        JSONObject jsonObject =  new JSONObject(response);
        JSONArray jsonArray1 = jsonObject.getJSONArray("data");

        List<Map<String, String>> userList = new ArrayList<>();
        for (int i=0;i<jsonArray1.length();i++){
            Map<String,String> user =new HashMap<>();
            user.put("fname",jsonArray1.getJSONObject(i).getString("first_name"));
            user.put("lname",jsonArray1.getJSONObject(i).getString("last_name"));
            user.put("email",jsonArray1.getJSONObject(i).getString("email"));
            user.put("avatar",jsonArray1.getJSONObject(i).getString("avatar"));
            userList.add(user);
        }

        return userList;
    }

    @Override
    public String getUrl() {
        return osgiMultiple.getInfo()+id;
    }
}
