package com.personalproject.core.models;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = HomeBanner.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class HomeBannerImpl implements HomeBanner{
    @ValueMapValue
    String bio;

    @ValueMapValue
    String fullname;

    @ValueMapValue
    String intro;

    @ValueMapValue
    String mybutton;

    @ValueMapValue
    String img;

    @Override
    public String getBio() {
        return bio;
    }

    @Override
    public String getFullName() {
        return fullname;
    }

    @Override
    public String getIntro() {
        return intro;
    }

    @Override
    public String getHeroImage() {
        return img;
    }

    @Override
    public String getMyButton() {
        return mybutton;
    }
}
