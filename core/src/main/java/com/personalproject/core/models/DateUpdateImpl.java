package com.personalproject.core.models;

import com.day.cq.commons.date.DateUtil;
import com.personalproject.core.config.SchedulerConfiguration;
import com.personalproject.core.utils.ResolverUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.util.Calendar;

@Component(service = DateUpdate.class,immediate = true)
@Designate(ocd= SchedulerConfiguration.class)
public class DateUpdateImpl implements DateUpdate {
    private static final Logger LOG = LoggerFactory.getLogger(DateUpdateImpl.class);

    @Reference
    ResourceResolverFactory resourceResolverFactory;


    @Override
    public String updateDate(String path) {
        try(ResourceResolver serviceResourceResolver = ResolverUtils.newResolver(resourceResolverFactory)) {
            Session session = serviceResourceResolver.adaptTo(Session.class);
            Resource resource = serviceResourceResolver.getResource(path);
            if (resource != null) {
                Node node = resource.adaptTo(Node.class);
                if(node != null) {
                    node.setProperty("DateTime", "Date and time added");
                    node.setProperty("NewTime", DateUtil.parseISO8601(DateUtil.getISO8601Date(Calendar.getInstance())));
                    if (session != null){
                        session.save();
                        session.logout();
                    }
                }
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
        }
        return path;
    }
}
