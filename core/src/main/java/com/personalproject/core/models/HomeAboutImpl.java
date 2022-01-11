package com.personalproject.core.models;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = HomeAbout.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HomeAboutImpl implements HomeAbout{

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

    @Override
    public String getAboutButton() {
        return mybutton;
    }

    @Override
    public String getPath() {
        return pathfield;
    }
}
