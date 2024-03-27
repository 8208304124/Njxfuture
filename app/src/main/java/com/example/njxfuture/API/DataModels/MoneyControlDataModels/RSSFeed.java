package com.example.njxfuture.API.DataModels.MoneyControlDataModels;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "rss", strict = false)
public class RSSFeed {
    @ElementList(name = "channel", inline = true)
    private List<Channel> channels;

    public List<Channel> getChannels() {
        return channels;
    }
}

