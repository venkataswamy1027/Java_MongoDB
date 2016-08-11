package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

@SuppressWarnings("unchecked")
public class MongoDBJDBC {

	public void DBConnection() {
		String MONGODB_HOST = "localhost";
		int MONGODB_PORT = 27017;
		try {
			//  To connect to mongoDB server
			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient(MONGODB_HOST, MONGODB_PORT);
			/**
			 * Get Database If it doesn't exist, MongoDB will create it.
			 * Now connect to your databases
			 */
			
			MongoDatabase db = mongo.getDatabase("IPL");
			System.out.println("Connect to mongo database successfully");
			/**
			 * Get Collection If it doesn't exist, MongoDB will create it
			 */
			@SuppressWarnings("rawtypes")
			MongoCollection collection = db.getCollection("IPLTeams");
			System.out.println("Collection created successfully");
			/**
			 * Create documents
			 */
			Document doc = new Document("title", "MongoDB")
					.append("description", "database")
					.append("url", "http://localhost:8081/MavenWeb/mongodb")
					.append("by","BridgeLabz");
			/**
			 * Create List<Document>
			 */
			List<Document> documents = new ArrayList<Document>();
			documents.add(doc);
			/**
			 * Insert List<Document> to collection
			 */
			collection.insertMany(documents);
			System.out.println("Document inserted successfully");
			/**
			 * Find All Documents in a Collection
			 */
			MongoCursor<Document> cursor = collection.find().iterator();
			try {
				while (cursor.hasNext()) {
					System.out.println(cursor.next().toJson());
				}
			} finally {
				cursor.close();
			}
			System.out.println("document retrieve on console Done");
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}// end of main

}
