package com.example.njxfuture.API.DataModels.MoneyControlDataModels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item {
    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description.replaceAll("<img[^>]+>", "");
    }

    public String getImageSrc() {
        if (description != null) {
            int startIndex = description.indexOf("src=\"") + 5;
            int endIndex = description.indexOf("\"", startIndex);
            if (startIndex >= 0 && endIndex >= 0) {
                return description.substring(startIndex, endIndex);
            }
        }
        return null;
    }
}
