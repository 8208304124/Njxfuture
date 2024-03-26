package com.example.njxfuture.API.DataModels;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root(name = "item", strict = false)
public class MTMDataModel {
    @Element(name = "title")
    private String title;

    // Add other fields as needed (e.g., link, description, pubDate)

    public String getTitle() {
        return title;
    }
}
