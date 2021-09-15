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
        adapters = Timeline.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class TimelineImpl implements Timeline{
    private static final Logger LOG = LoggerFactory.getLogger(TimelineImpl.class);

    @Inject
    Resource componentResource;



    @Override
    public List<Map<String, String>> getTimelineDetails() {
        List<Map<String, String>> timelineDetailsMap= new ArrayList<>();
        try {
            Resource timelineDetail=componentResource.getChild("timelinedetailswithmap");
            if(timelineDetail!=null){
                for (Resource timeline : timelineDetail.getChildren()) {
                    Map<String,String> timelinemap =new HashMap<>();
                    timelinemap.put("text",timeline.getValueMap().get("text",String.class));
                    timelinemap.put("heading",timeline.getValueMap().get("heading",String.class));
                    timelinemap.put("title",timeline.getValueMap().get("title",String.class));
                    timelineDetailsMap.add(timelinemap);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
        }
        LOG.info("\n SIZE {} ",timelineDetailsMap.size());
        return timelineDetailsMap;

    }



}
