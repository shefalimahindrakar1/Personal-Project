package com.personalproject.core.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Testimonial.class,
        resourceType = TestimonialImpl.RESOURCE_TYPE,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson",extensions = "json",selector = "personal")
public class TestimonialImpl implements Testimonial{
    private static final Logger LOG = LoggerFactory.getLogger(TestimonialImpl.class);
    static final String RESOURCE_TYPE="personalproject/components/content/testimonial";
    @ChildResource
    Resource testimonialdetailswithmap;
    @JsonProperty(value = "Details - Testimonial")
    @Override
    public List<Map<String, String>> getTestimonialDetails() {
        List<Map<String, String>> testimonialDetailsMap= new ArrayList<>();
        try {
          //  Resource testimonialDetail=componentResource.getChild("testimonialdetailswithmap");
            if(testimonialdetailswithmap!=null){
                for (Resource testimonial : testimonialdetailswithmap.getChildren()) {
                    Map<String,String> testimonialmap =new HashMap<>();
                    testimonialmap.put("description",testimonial.getValueMap().get("description",String.class));
                    testimonialmap.put("heading",testimonial.getValueMap().get("heading",String.class));
                    testimonialmap.put("title",testimonial.getValueMap().get("title",String.class));
                    testimonialDetailsMap.add(testimonialmap);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
        }
        LOG.info("\n SIZE {} ",testimonialDetailsMap.size());
        return testimonialDetailsMap;

    }
}

