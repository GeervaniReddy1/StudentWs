package com.student.data;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.student.exception.StudentDataAccessException;
import com.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    @Qualifier("amazonDynamoDB")
    private AmazonDynamoDB amazonDynamoDB;

    static String tableName = "STUDENT_DETAILS";

    @Override
    public Student getStudentDetailsBYRollNo(int rollNO) {
        Student st= new Student();
        st.setRollNo(rollNO);
        return getStudentByRollNo(st);
    }

    @Override
    public Student saveStudentDetails(Student student) {
       return saveStudentMock(student);
    }


    Student getStudentByRollNo(Student student){
        HashMap<String,AttributeValue> key_to_get =
                new HashMap<String,AttributeValue>();

        key_to_get.put("rollNo", new AttributeValue().withN(Integer.toString(student.getRollNo())));

        GetItemRequest request = new GetItemRequest()
                .withKey(key_to_get)
                .withTableName(tableName);
                 GetItemResult itemResult=amazonDynamoDB.getItem(request);
                Student student1= getStudentMapper(itemResult.getItem());
        System.out.println("Item Result"+itemResult.toString());
        return student1;
    }
public Student getStudentMapper(Map<String,AttributeValue> studentItem){
        Student std=new Student();
        std.setRollNo(Integer.parseInt(studentItem.get("rollNo").getN()));
        std.setFirstName(studentItem.get("firstName").getS());
        std.setLastName(studentItem.get("lastName").getS());
        std.setSection(studentItem.get("section").getS());
        std.setCourse(studentItem.get("course").getS());
        std.setPhoneNo(Long.parseLong(studentItem.get("phoneNo").getN()));
        std.setCollegeName(studentItem.get("collegeName").getS());
        std.setGender(studentItem.get("gender").getS());
  return std;
}

    Student saveStudentMock(Student student){
        createTable();
        saveStudent(student);
        return student;

    }

    void createTable(){
        try {
            CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
                    .withKeySchema(new KeySchemaElement().withAttributeName("rollNo").withKeyType(KeyType.HASH))
                    .withAttributeDefinitions(new AttributeDefinition().withAttributeName("rollNo").withAttributeType(ScalarAttributeType.N))
                    .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
            // Create table if it does not exist yet
            TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
            // wait for the table to move into ACTIVE state
            TableUtils.waitUntilActive(amazonDynamoDB, tableName);
            // Describe our new table
            DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
            TableDescription tableDescription = amazonDynamoDB.describeTable(describeTableRequest).getTable();
            System.out.println("Table Description: " + tableDescription);
        }
        catch(Exception e){
            throw new StudentDataAccessException("Exception Occured while creating Table"+e.getMessage());
        }

    }
    void saveStudent(Student student){
        try {
            Map<String, AttributeValue> item =
                    newItem(student.getRollNo(), student.getFirstName(), student.getLastName(), student.getSection(),
                            student.getCourse(), student.getPhoneNo(), student.getCollegeName(), student.getGender());
            PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
            PutItemResult putItemResult = amazonDynamoDB.putItem(putItemRequest);
            System.out.println("Result: " + putItemResult);
        }
        catch(Exception e){
            throw new StudentDataAccessException("Exception during insert item"+e.getMessage());

        }

    }
    private static Map<String, AttributeValue> newItem(int rollNo,String firstName,String lastName, String section, String course, long phoneNo,String collegeName,String gender) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("rollNo", new AttributeValue().withN(Integer.toString(rollNo)));;
        item.put("firstName", new AttributeValue(firstName));
        item.put("lastName", new AttributeValue(lastName));
        item.put("section", new AttributeValue(section));
        item.put("course", new AttributeValue(course));
        item.put("phoneNo", new AttributeValue().withN(Long.toString(phoneNo)));
        item.put("collegeName", new AttributeValue(collegeName));
        item.put("gender", new AttributeValue(gender));

        return item;
    }


}
