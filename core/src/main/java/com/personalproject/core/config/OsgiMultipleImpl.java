package com.personalproject.core.config;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = OsgiMultiple.class,immediate = true)
@Designate(ocd = OsgiMultipleImpl.MultipleUser.class )
public class OsgiMultipleImpl implements OsgiMultiple{
    @ObjectClassDefinition(name = "Multiple User",
            description = "Configuration for multiple user")

    public @interface MultipleUser {

        @AttributeDefinition(
                name = "User Data",
                description = "Data of multiple users",
                type = AttributeType.STRING)
        public String getInfo() default "https://reqres.in/api/users?page=";
    }
    protected String info ="";
    @Override
    public String getInfo() {
        return info;
    }
    @Activate
    protected void activate(MultipleUser multipleUser) {
        info = multipleUser.getInfo();
    }
}
