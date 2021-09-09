package com.personalproject.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Model(
        adaptables = SlingHttpServletRequest.class,
        adapters = Testimonial.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TestimonialImpl implements Testimonial{
    private static final Logger LOG = LoggerFactory.getLogger(TestimonialImpl.class);

    @Inject
    Resource componentResource;

    @Override
    public List<Map<String, String>> getTestimonialDetails() {
        List<Map<String, String>> testimonialDetailsMap= new ArrayList<>();
        try {
            Resource testimonialDetail=componentResource.getChild("testimonialdetailswithmap");
            if(testimonialDetail!=null){
                for (Resource testimonial : testimonialDetail.getChildren()) {
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

