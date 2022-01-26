package com.personalproject.core.config;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = OsgiSingle.class,immediate = true)
@Designate(ocd = OsgiSingleImpl.SingleUser.class )
public class OsgiSingleImpl implements OsgiSingle{

    @ObjectClassDefinition(name = "Single User",
            description = "Configuration Single User")

    public @interface SingleUser {

        @AttributeDefinition(
                name = "User Information",
                description = "Information of the user",
                type = AttributeType.STRING)
        public String getInfo() default "https://reqres.in/api/users/";

        @AttributeDefinition(
                name = "User Image",
                description = "Image of the user",
                type = AttributeType.STRING
        )
        String getImage() default "https://reqres.in/img/faces/";

        @AttributeDefinition(
                name = "Dam link",
                description = "Path of my dam link project",
                type = AttributeType.STRING
        )
        String getmyPath() default "/content/dam/personalproject";
    }

    protected String userImage = "";
    protected String userInfo = "";
    protected String assetpath = "";

    @Override
    public String getmyPath() {
        return assetpath;
    }

    @Override
    public String getImage() {
        return userImage;
    }

    @Override
    public String getInfo() {
        return userInfo;
    }

    @Activate
    protected void activate(SingleUser singleUser) {
        userInfo = singleUser.getInfo();
        assetpath = singleUser.getmyPath();
        userImage = singleUser.getImage();

    }


}
