package com.personalproject.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = HomeAbout.class,
        resourceType = HomeAboutImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson",extensions = "json",selector ="personal")
public class HomeAboutImpl implements HomeAbout{
    static final String RESOURCE_TYPE="personalproject/components/content/homeabout";

    @ValueMapValue
    String about;

    @ValueMapValue
    String details;

    @ValueMapValue
    String intro;

    @ValueMapValue
    String mybutton;

    @ValueMapValue
    String img;

    @ValueMapValue
    String pathfield;

    @JsonProperty(value = "homeabout-details")
    public String details() {
        return "DETAILS:";
    }

    @Override
    public String getAbout() {
        return about;
    }

    @Override
    public String getDetails() {
        return details;
    }

    @Override
    public String getIntro() {
        return intro;
    }

    @Override
    public String getAboutImage() {
        return img;
    }
    @JsonProperty(value = "title")
    @Override
    public String getAboutButton() {
        return mybutton;
    }

    @Override
    public String getPath() {
        return pathfield;
    }
}
