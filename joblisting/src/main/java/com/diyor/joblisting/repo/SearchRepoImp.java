package com.diyor.joblisting.repo;

import com.diyor.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SearchRepoImp implements  SearchRepo{
    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoConverter mongoConverter;
    @Override
    public List<Post> findByText(String text) {
        final List<Post>  list = new ArrayList<>();
        MongoDatabase database = mongoClient.getDatabase("store");
        MongoCollection<Document> collection = database.getCollection("jobpost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$search", new Document("text",
                new Document("query", text)
                .append("path", Arrays.asList("techs", "desc", "profile")))),
                new Document("$sort",
                new Document("exp", 1L)),
                new Document("$limit", 5L)));
       result.forEach(doc -> list.add(mongoConverter.read(Post.class, doc)));
        return list;
    }
}
