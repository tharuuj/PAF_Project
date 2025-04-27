package com.linhtch90.psnbackend.repository;

// Import necessary classes
import com.linhtch90.psnbackend.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// Mark this interface as a Spring Repository (for MongoDB)
@Repository
public interface CommentRepository extends MongoRepository<CommentEntity, String> {
    // Inherits basic CRUD operations from MongoRepository
    // CommentEntity is the type of document
    // String is the type of the ID field
}
