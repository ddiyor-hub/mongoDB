package com.diyor.joblisting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "jobpost")
public class Post {
    private String profile;
    private String desc;
    private int exp;
    private String[] techs;

}
